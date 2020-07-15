using System;
using System.Collections.Generic;

namespace SiteKick.Data.Models
{
    public partial class Technology
    {
        public Technology()
        {
            SiteTech = new HashSet<SiteTech>();
        }

        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int? TechnologyGroupId { get; set; }

        public virtual TechnologyGroup TechnologyGroup { get; set; }
        public virtual ICollection<SiteTech> SiteTech { get; set; }
    }
}
