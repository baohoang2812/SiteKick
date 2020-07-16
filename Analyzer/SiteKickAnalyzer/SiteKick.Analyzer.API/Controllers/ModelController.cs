using Microsoft.AspNetCore.Mvc;
using ModelTrainer;
using SiteKick.Analyzer.API.Extension;
using SiteKick.Analyzer.API.ViewModel;
using System;

namespace SiteKick.Analyzer.API.Controllers
{
    [Route("v1/model")]
    [ApiController]
    public class ModelController : BaseController
    {
        [HttpGet]
        public IActionResult Get()
        {
            try
            {
                ModelBuilder.CreateTrainingData();
                ModelBuilder.CreateModel();
                return Ok(new ApiResult()
                {
                    Code = ResultCode.Success,
                    Message = ResultCode.Success.DisplayName(),
                });
            }
            catch (Exception)
            {
                return Error(new ApiResult()
                {
                    Code = ResultCode.UnknownError,
                    Message = ResultCode.UnknownError.DisplayName()
                });
            }
        }
    }
}
