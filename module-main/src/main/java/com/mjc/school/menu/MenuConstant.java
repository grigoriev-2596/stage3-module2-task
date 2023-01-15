package com.mjc.school.menu;

public enum MenuConstant {
    CREATE_NEWS(1, "Create news"),
    GET_ALL_NEWS(2, "Get all news"),
    GET_NEWS_BY_ID(3, "Get news by id"),
    UPDATE_NEWS(4, "Update news"),
    DELETE_NEWS(5, "Remove news by id"),
    CREATE_AUTHOR(6, "Create author"),
    GET_ALL_AUTHORS(7, "Get all authors"),
    GET_AUTHOR_BY_ID(8, "Get author by id"),
    UPDATE_AUTHOR(9, "Update author"),
    DELETE_AUTHOR(10, "Remove author by id"),
    EXIT(0, "Exit");

    private final int id;
    private final String name;

    MenuConstant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
