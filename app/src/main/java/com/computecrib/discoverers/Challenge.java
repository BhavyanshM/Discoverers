package com.computecrib.discoverers;

/**
 * Created by bhavy on 4/7/2018.
 */

public class Challenge {

    private int reward;
    private String title;
    private String description;

    public Challenge(String title, String description, int reward){
        this.reward = reward;
        this.description = description;
        this.title = title;
    }

    public int getReward() {
        return reward;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
