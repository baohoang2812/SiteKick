using System;
using System.Collections.Generic;

namespace SiteKick.Data.Models
{
    public partial class Category
    {
        public Category()
        {
            Site = new HashSet<Site>();
        }

        public int Id { get; set; }
        public string Name { get; set; }

        public virtual ICollection<Site> Site { get; set; }
    }
}
