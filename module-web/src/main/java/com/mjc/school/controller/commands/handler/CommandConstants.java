package com.mjc.school.controller.commands.handler;

import java.util.Arrays;
import java.util.Optional;

public enum CommandConstants {
    EXIT(0, "Exit"),
    CREATE_NEWS(1, "Create news"),
    GET_ALL_NEWS(2, "Get all news"),
    GET_NEWS_BY_ID(3, "Get news by id"),
    UPDATE_NEWS(4, "Update news"),
    DELETE_NEWS(5, "Remove news by id"),
    CREATE_AUTHOR(6, "Create author"),
    GET_ALL_AUTHORS(7, "Get all authors"),
    GET_AUTHOR_BY_ID(8, "Get author by id"),
    UPDATE_AUTHOR(9, "Update author"),
    DELETE_AUTHOR(10, "Remove author by id");

    private final int id;
    private final String name;


    CommandConstants(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CommandConstants valueOf(int id) {
        Optional<CommandConstants> command = Arrays.stream(values()).filter(c -> c.getId() == id).findFirst();
        if (command.isEmpty()) {
            throw new IllegalArgumentException("No such command exists");
        }
        return command.get();
    }
}