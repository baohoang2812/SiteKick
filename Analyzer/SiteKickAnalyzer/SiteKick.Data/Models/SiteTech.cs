using System;
using System.Collections.Generic;

namespace SiteKick.Data.Models
{
    public partial class SiteTech
    {
        public int TechnologyId { get; set; }
        public int SiteId { get; set; }

        public virtual Site Site { get; set; }
        public virtual Technology Technology { get; set; }
    }
}
