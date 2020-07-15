using Microsoft.ML;
using SiteKick.Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ModelTrainer
{
    public class ModelConsumer
    {
        private PredictionEngine<TechEntry, TechnologyPrediction> _predictionEngine;
        private MLContext _mlContext;
        public string ModelPath { get; set; }
        private DataViewSchema ModelSchema { get; set; }
        public ITransformer Model { get; set; }

        public ModelConsumer(string modelPath)
        {
            _mlContext = new MLContext();
            ModelPath = modelPath;
            this.Model = LoadModel();
        }

        private ITransformer LoadModel()
        {
            DataViewSchema modelSchema;
            var model = _mlContext.Model.Load(ModelPath, out modelSchema);
            this.ModelSchema = modelSchema;
            return model;
        }

        /// <summary>
        /// Find the top 5 combined technology for the technology id
        /// </summary>
        public List<TechnologyPrediction> Predict(uint techId, int maxResult = 5)
        {
            _predictionEngine = _mlContext.Model.CreatePredictionEngine<TechEntry, TechnologyPrediction>(Model);
            Console.WriteLine($"Calculating the top 5 technology for technology {techId} ...");
            var context = new SiteKickContext();
            var top5 = (from m in Enumerable.Range(1, (int)CommonConstant.KEYCOUNT)
                        let predictions = _predictionEngine.Predict(new TechEntry
                        {
                            TechnologyId = techId,
                            CombinedTechnologyId = (uint)m
                        })
                        orderby predictions.Score descending
                        select (TechnologyId: m, Score: predictions.Score)).Select(x => new TechnologyPrediction
                        {
                            TechnologyId = (uint)x.TechnologyId,
                            TechnologyName = context.Technology.Find(x.TechnologyId).Name,
                            Score = x.Score
                        }).Take(maxResult).ToList();
            return top5;
        }

    }
}
