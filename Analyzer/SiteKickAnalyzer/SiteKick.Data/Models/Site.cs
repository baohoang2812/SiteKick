using System;
using System.Collections.Generic;

namespace SiteKick.Data.Models
{
    public partial class Site
    {
        public Site()
        {
            SiteTech = new HashSet<SiteTech>();
        }

        public int Id { get; set; }
        public string Url { get; set; }
        public int? GlobalRank { get; set; }
        public string Country { get; set; }
        public int? CountryRank { get; set; }
        public int? CategoryId { get; set; }

        public virtual Category Category { get; set; }
        public virtual ICollection<SiteTech> SiteTech { get; set; }
    }
}
