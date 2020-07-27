/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sitekick.constant.HttpContentTypeConstant;
import sitekick.dao.TechnologyDAO;
import sitekick.data.ApiResult;
import sitekick.data.TechnologySuggestion;
import sitekick.utils.HttpUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyService {

    TechnologyDAO technologyBLO;

    public TechnologyService(TechnologyDAO technologyBLO) {
        this.technologyBLO = technologyBLO;
    }

    /**
     *
     * @param techIds
     * @param baseUrl
     * @return 
     * @throws java.io.IOException
     */
    public List<TechnologySuggestion> suggestTechnology(List<Integer> techIds, String baseUrl) throws IOException {
        List<TechnologySuggestion> result = new ArrayList();
        String fullUrl = baseUrl + "/v1/recommendation/list?";
        for (Integer techId : techIds) {
            fullUrl += "&idList=" + techId;
        }
        GsonBuilder gSonBuilder = new GsonBuilder();
        gSonBuilder.setPrettyPrinting();
        Gson gson = gSonBuilder.create();
        String response = HttpUtils.getContent(fullUrl, HttpContentTypeConstant.JSON);
        ApiResult apiResult = gson.fromJson(response, ApiResult.class);
        if (apiResult != null && apiResult.getData() != null) {
            result.addAll(apiResult.getData());
        }
        return result;
    }

}
