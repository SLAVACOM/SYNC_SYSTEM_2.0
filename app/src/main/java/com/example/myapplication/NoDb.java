package com.example.myapplication;

import com.example.myapplication.domain.Event;
import com.example.myapplication.domain.User;

import java.util.ArrayList;
import java.util.List;

public class NoDb {
    private NoDb(){}

    public static final List<User> ALL_USER_LIST = new ArrayList<>();

    public static final List<Event> ALL_EVENT_LIST = new ArrayList<>();

    public static final List<Event> EVENT_LIST = new ArrayList<>();
    public static final List<Event> USER_LIST = new ArrayList<>();

    public static int user = -1;
}
