package com.example.myapplication.domain.Mapper;

import com.example.myapplication.domain.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserMapper {
    public static User eventFromJson(JSONObject jsonObject) {
        User user = null;
        try {
            user = new User(jsonObject.getInt("id"), jsonObject.getString
                    ("password"), jsonObject.getString("mail")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }


}
