package com.mjc.school.menu;

import com.mjc.school.controller.commands.handler.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NewsMenu extends Menu {
    private final CommandFactory commandFactory;

    @Autowired
    public NewsMenu(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void create() {
        String title, content;
        long authorId;
        List<Long> tagIds;
        System.out.print("Enter news title:\n>>");
        title = scanner.nextLine();
        System.out.print("Enter news content:\n>>");
        content = scanner.nextLine();
        System.out.print("Enter author id:\n>>");
        authorId = readId();
        System.out.println(commandFactory
                .getCreateNewsCommand(title, content, authorId)
                .execute());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAll() {
        List<Object> newsList = Collections.unmodifiableList((List<Object>) commandFactory
                .getReadAllNewsCommand()
                .execute());
        newsList.forEach(System.out::println);
    }

    @Override
    public void getById() {
        System.out.print("Enter news id:\n>>");
        long newsId = readId();

        System.out.println(commandFactory
                .getReadNewsByIdCommand(newsId)
                .execute());
    }

    @Override
    public void update() {
        String title, content;
        long authorId, newsId;
        List<Long> tagIds;
        System.out.print("Enter news id:\n>>");
        newsId = readId();
        System.out.print("Enter news title:\n>>");
        title = scanner.nextLine();
        System.out.print("Enter news content:\n>>");
        content = scanner.nextLine();
        System.out.print("Enter author id:\n>>");
        authorId = readId();
        System.out.println(commandFactory
                .getUpdateNewsCommand(newsId, title, content, authorId)
                .execute());
    }

    @Override
    public void delete() {
        System.out.print("Enter news id:\n>>");
        long newsId = readId();

        System.out.println(commandFactory
                .getDeleteNewsCommand(newsId)
                .execute());
    }
}
