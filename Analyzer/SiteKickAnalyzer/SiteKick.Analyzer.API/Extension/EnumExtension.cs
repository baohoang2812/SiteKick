using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SiteKick.Analyzer.API.Extension
{
    public static partial class EnumExtension
    {
        public static string DisplayName(this Enum enumValue)
        {
            var enumType = enumValue.GetType();
            var name = Enum.GetName(enumType, enumValue);
            var displayName = enumType.GetField(name).GetCustomAttributes(false).OfType<DisplayAttribute>().SingleOrDefault();
            if (displayName != null)
            {
                return displayName.Name;
            }
            return null;
        }
    }
}
