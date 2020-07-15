using System;
using System.Collections.Generic;

namespace SiteKick.Data.Models
{
    public partial class TechnologyGroup
    {
        public TechnologyGroup()
        {
            Technology = new HashSet<Technology>();
        }

        public int Id { get; set; }
        public string Name { get; set; }

        public virtual ICollection<Technology> Technology { get; set; }
    }
}
