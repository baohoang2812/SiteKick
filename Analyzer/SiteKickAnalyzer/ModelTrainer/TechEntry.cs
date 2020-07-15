using Microsoft.ML.Data;

namespace ModelTrainer
{
    public class TechEntry
    {
        [KeyType(count: 369928)]
        public uint TechnologyId { get; set; }
        [KeyType(count: 369928)]
        public uint CombinedTechnologyId { get; set; }
    }
}
