using System;

namespace ModelTrainer
{
    class Program
    {
        static void Main(string[] args)
        {
            //Console.WriteLine("Creating training data...");
            //ModelBuilder.CreateTrainingData();
            //Console.WriteLine("Building model...");
            //ModelBuilder.CreateModel();
            Console.WriteLine("Test Predict Technology...");
            var modelPath = ModelBuilder.GetFullPath(CommonConstant.MODEL_FILE_NAME);
            ModelConsumer consumer = new ModelConsumer(modelPath);
            var top5 = consumer.Predict(3688);
            foreach (var t in top5)
            {
                Console.WriteLine($"Technology Id: {t.TechnologyId} Technology Name: {t.TechnologyName}" +
                    $"Suggest Technology:  Id - {t.SuggestTechnologyId}; Name - {t.SuggestTechnologyName}" +
                    $"\t Score: {t.Score}");
            }
        }
    }
}
