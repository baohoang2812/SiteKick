/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.data;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologySuggestion {
    public int technologyId;
    public String technologyName;
    public String suggestTechnologyName;
    public int suggestTechnologyId;
    public float score;

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getSuggestTechnologyName() {
        return suggestTechnologyName;
    }

    public void setSuggestTechnologyName(String suggestTechnologyName) {
        this.suggestTechnologyName = suggestTechnologyName;
    }

    public int getSuggestTechnologyId() {
        return suggestTechnologyId;
    }

    public void setSuggestTechnologyId(int suggestTechnologyId) {
        this.suggestTechnologyId = suggestTechnologyId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    
    
}
