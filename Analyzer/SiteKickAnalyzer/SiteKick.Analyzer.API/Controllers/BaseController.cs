using Microsoft.AspNetCore.Mvc;
using System.Net;

namespace SiteKick.Analyzer.API.Controllers
{
    [Route("v1/[controller]")]
    [ApiController]
    public class BaseController : ControllerBase
    {
        protected IActionResult Error<T>(T obj)
        {
            return StatusCode((int)HttpStatusCode.InternalServerError, obj);
        }
    }
}
