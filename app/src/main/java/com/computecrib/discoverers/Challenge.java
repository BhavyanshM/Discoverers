package com.computecrib.discoverers;

/**
 * Created by bhavy on 4/7/2018.
 */

public class Challenge {

    private int reward;
    private String title;
    private String description;
    private String hint;
    private String loc_name;
    private String loc_address;

    public Challenge(String title, String description,
                     String hint, String loc_name,
                     String loc_address, int reward) {

        this.reward = reward;
        this.description = description;
        this.title = title;
        this.hint = hint;
        this.loc_name = loc_name;
        this.loc_address = loc_address;
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

    public String getHint() {
        return hint;
    }

    public String getName() {
        return loc_name;
    }

    public String getAddress() {
        return loc_address;
    }
}
