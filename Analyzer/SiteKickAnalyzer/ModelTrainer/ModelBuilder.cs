using Microsoft.EntityFrameworkCore;
using Microsoft.ML;
using Microsoft.ML.Data;
using Microsoft.ML.Trainers;
using SiteKick.Data.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using static Microsoft.ML.DataOperationsCatalog;

namespace ModelTrainer
{
    public class ModelBuilder
    {
        private static MLContext mlContext = new MLContext();
        public static void CreateTrainingData()
        {
            var entryList = new List<TechEntry>();
            var context = new SiteKickContext();
            var site = context.Site.Include(s => s.SiteTech).ThenInclude(st => st.Technology).ToList();
            site.ForEach(s =>
            {
                var techList = s.SiteTech.Select(st => st.Technology).OrderBy(x => x.TechnologyGroupId).ToList();
                var groupList = techList.Select(x => x.TechnologyGroupId).Distinct().ToList();
                groupList.ForEach(g =>
                {
                    var allTechInGroup = techList.Where(x => x.TechnologyGroupId == g).Select(x => x.Id).ToList();
                    allTechInGroup.ForEach(t =>
                    {
                        var techInGroupNotCurrent = allTechInGroup.Where(y => y != t).ToList();
                        techInGroupNotCurrent.ForEach(z =>
                        {
                            entryList.Add(new TechEntry
                            {
                                TechnologyId = (uint)t,
                                CombinedTechnologyId = (uint)z
                            });
                        });

                    });
                });
            });
            CreateCSVFromList(entryList);
        }

        public static string GetFullPath(string relativeDatasetPath)
        {
            FileInfo _dataRoot = new FileInfo(typeof(Program).Assembly.Location);
            string assemblyFolderPath = _dataRoot.Directory.FullName;
            return Path.Combine(assemblyFolderPath, relativeDatasetPath);
        }

        private static void CreateCSVFromList(List<TechEntry> data)
        {
            StringBuilder csv = new StringBuilder();
            csv.Append("TechnologyId CombinedTechnologyId");
            csv.AppendLine();
            foreach (var row in data)
            {
                var newLine = $"{row.TechnologyId},{row.CombinedTechnologyId}";
                csv.Append(newLine);
                csv.AppendLine();
            }
            File.WriteAllText(GetFullPath(CommonConstant.TRAINING_FILE_NAME), csv.ToString());
        }

        public static void CreateModel()
        {
            var context = new SiteKickContext();
            var totalUniqueTechnology = (ulong)context.Technology.Select(x => x.Id).Count();
            Console.WriteLine("Creating model...");
            // load data from database
            Console.WriteLine("Loading data...");
            IDataView trainingDataView = mlContext.Data.LoadFromTextFile(
                GetFullPath(CommonConstant.TRAINING_FILE_NAME),
                columns: new[] {
                  new TextLoader.Column("Label", DataKind.Single, 0),
                    new TextLoader.Column(nameof(TechEntry.TechnologyId), DataKind.UInt32, keyCount: new KeyCount(CommonConstant.KEYCOUNT),
                                    source: new [] { new TextLoader.Range(0)}),
                    new TextLoader.Column(nameof(TechEntry.CombinedTechnologyId), DataKind.UInt32, keyCount: new KeyCount(CommonConstant.KEYCOUNT),
                                    source: new [] { new TextLoader.Range(1)}),
                },
                separatorChar: ',',
                hasHeader: true);

            var partitions = SplitData(trainingDataView, 0.2);
            var pipeline = BuildTrainingPipeline();
            var model = TrainModel(pipeline, partitions);
            SaveModel(model, trainingDataView.Schema, GetFullPath(CommonConstant.MODEL_FILE_NAME));
            EvaluateModel(model, partitions);
            Console.WriteLine("Model created!");
        }

        /// <summary>
        /// Save trained model to ZIP
        /// </summary>
        public static void SaveModel(ITransformer model, DataViewSchema dataSchema, string fileName)
        {
            Console.WriteLine("Saving Model...");
            var modelPath = GetFullPath(fileName);
            mlContext.Model.Save(model, dataSchema, modelPath);
            Console.WriteLine($"Model saved at {modelPath}");
        }

        private static ITransformer TrainModel(IEstimator<ITransformer> pipeline, IDataView data)
        {
            Console.WriteLine("Training the model...");
            var model = pipeline.Fit(data);
            Console.WriteLine("Finish training model");
            return model;
        }


        private static ITransformer TrainModel(IEstimator<ITransformer> pipeline, TrainTestData partitions)
        {
            Console.WriteLine("Training the model...");
            var model = pipeline.Fit(partitions.TrainSet);
            Console.WriteLine("Finish training model");
            return model;
        }

        /// <summary>
        /// Evaluate model performance
        /// </summary>
        /// <param name="model"></param>
        private static void EvaluateModel(ITransformer model, TrainTestData partitions)
        {
            Console.WriteLine("Evaluating the model...");
            var predictions = model.Transform(partitions.TestSet);
            var metrics = mlContext.Regression.Evaluate(predictions);
            Console.WriteLine($"  Root Mean Square Error: {metrics.RootMeanSquaredError:#.##}");
            Console.WriteLine($"  Mean Absolute Error:   {metrics.MeanAbsoluteError:#.##}");
            Console.WriteLine($"  Mean Squared Error:   {metrics.MeanSquaredError:#.##}");
            Console.WriteLine();
        }

        private static IEstimator<ITransformer> BuildTrainingPipeline()
        {
            // specify options with hyperparameter
            var options = new MatrixFactorizationTrainer.Options()
            {
                MatrixColumnIndexColumnName = nameof(TechEntry.TechnologyId),
                MatrixRowIndexColumnName = nameof(TechEntry.CombinedTechnologyId),
                LabelColumnName = "Label",
                LossFunction = MatrixFactorizationTrainer.LossFunctionType.SquareLossOneClass,
                Alpha = 0.01,
                Lambda = 0.025,
            };
            return mlContext.Recommendation().Trainers.MatrixFactorization(options);
        }

        private static TrainTestData SplitData(IDataView dataView, double fraction)
        {
            // split data into 80% training and 20% testing partitions
            return mlContext.Data.TrainTestSplit(dataView, testFraction: fraction);
        }
    }
}
