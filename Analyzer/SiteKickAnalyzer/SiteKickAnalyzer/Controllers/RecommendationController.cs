using Microsoft.AspNetCore.Mvc;

namespace SiteKickAnalyzerAPI.Controllers
{
    [Route("v1/recommendation")]
    [ApiController]
    public class RecommendationController : ControllerBase
    {
        [HttpGet]
        public IActionResult Get()
        {
            return Ok();
        }
    }
}
