package com.computecrib.discoverers;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

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

    public byte[] persist(){
        JSONObject returnVal = new JSONObject();
        try {
            returnVal.put("Title", title);
            returnVal.put("Description", description);
            returnVal.put("Hint", hint);
            returnVal.put("Reward", reward);
            returnVal.put("LocationName", loc_name);
            returnVal.put("LocationAddress", loc_address);
        }catch (JSONException e){

        }
        String st = returnVal.toString();
        return st.getBytes(Charset.forName("UTF-8"));
    }

    static public Challenge unpersist(byte[] byteArray){
        if(byteArray==null){
            return new Challenge("", "", "", "", "",0);
        }
        String st = null;
        try {
            st = new String(byteArray, "UTF-8");
        }catch (Exception e){

        }
        Challenge ch = null;
        try {
            JSONObject obj = new JSONObject(st);
            if(obj.has("Title")
                    && obj.has("Description")
                    && obj.has("Hint")
                    && obj.has("LocationName")
                    && obj.has("LocationAddress")
                    && obj.has("Reward")){
                ch = new Challenge(obj.getString("Title"),
                        obj.getString("Description"),
                        obj.getString("Hint"),
                        obj.getString("LocationName"),
                        obj.getString("LocationAddress"),
                        obj.getInt("Reward"));
            }
        }catch (Exception e){

        }
        return ch;
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
