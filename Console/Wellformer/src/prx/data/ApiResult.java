/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.data;

import java.util.List;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class ApiResult {
    int code;
    String message;
    List<TechnologySuggestion> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TechnologySuggestion> getData() {
        return data;
    }

    public void setData(List<TechnologySuggestion> data) {
        this.data = data;
    }

}
