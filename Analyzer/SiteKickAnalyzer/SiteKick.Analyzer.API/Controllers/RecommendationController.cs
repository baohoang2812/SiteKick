using Microsoft.AspNetCore.Mvc;
using ModelTrainer;
using SiteKick.Analyzer.API.Extension;
using SiteKick.Analyzer.API.ViewModel;
using System;
using System.Collections.Generic;

namespace SiteKick.Analyzer.API.Controllers
{
    [Route("v1/recommendation")]
    [ApiController]
    public class RecommendationController : BaseController
    {
        [HttpGet]
        public IActionResult Get(uint id)
        {
            try
            {
                ModelConsumer modelConsumer = new ModelConsumer(ModelBuilder.GetFullPath(ModelTrainer.CommonConstant.MODEL_FILE_NAME));
                var top5 = modelConsumer.Predict(id);
                return Ok(new ApiResult()
                {
                    Code = ResultCode.Success,
                    Message = ResultCode.Success.DisplayName(),
                    Data = top5
                });
            }
            catch (Exception e)
            {
                return Error(new ApiResult()
                {
                    Code = ResultCode.UnknownError,
                    Message = ResultCode.UnknownError.DisplayName() + e.Message
                });
            }
        }

        [HttpGet("list")]
        public IActionResult Get([FromQuery]List<uint> idList, int maxPrediction = 1)
        {
            try
            {
                ModelConsumer modelConsumer = new ModelConsumer(ModelBuilder.GetFullPath(ModelTrainer.CommonConstant.MODEL_FILE_NAME));
                var result = modelConsumer.Predict(idList, maxPrediction);
                return Ok(new ApiResult()
                {
                    Code = ResultCode.Success,
                    Data = result,
                    Message = ResultCode.Success.DisplayName()
                });
            }
            catch (Exception e)
            {
                return Error(new ApiResult()
                {
                    Code = ResultCode.UnknownError,
                    Message = ResultCode.UnknownError.DisplayName() + e.Message
                });
            }
        }
    }
}
