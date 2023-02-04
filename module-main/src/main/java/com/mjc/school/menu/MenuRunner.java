package com.mjc.school.menu;

import com.mjc.school.controller.commands.handler.CommandConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.exit;

@Component
public class MenuRunner {
    private final NewsMenu newsMenu;
    private final AuthorMenu authorMenu;

    private Scanner scanner;

    @Autowired
    public MenuRunner(NewsMenu newsMenu, AuthorMenu authorMenu) {
        this.newsMenu = newsMenu;
        this.authorMenu = authorMenu;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
        authorMenu.setScanner(scanner);
        newsMenu.setScanner(scanner);
    }

    private void printMenu() {
        CommandConstants[] commands = CommandConstants.values();
        for (CommandConstants c : commands) {
            System.out.println(c.getId() + " - " + c.getName());
        }
    }

    public void runMenu() {
        while (true) {
            try {
                printMenu();
                System.out.print("Enter the number of operation \n>>");
                int commandId = readCommandId();
                CommandConstants commandConstant = CommandConstants.valueOf(commandId);
                System.out.println("Operation: " + commandConstant.getName());
                switch (commandConstant) {
                    case EXIT -> exit(0);
                    case CREATE_NEWS -> newsMenu.create();
                    case GET_ALL_NEWS -> newsMenu.getAll();
                    case GET_NEWS_BY_ID -> newsMenu.getById();
                    case UPDATE_NEWS -> newsMenu.update();
                    case DELETE_NEWS -> newsMenu.delete();
                    case CREATE_AUTHOR -> authorMenu.create();
                    case GET_ALL_AUTHORS -> authorMenu.getAll();
                    case GET_AUTHOR_BY_ID -> authorMenu.getById();
                    case UPDATE_AUTHOR -> authorMenu.update();
                    case DELETE_AUTHOR -> authorMenu.delete();
                    default -> System.out.println("Command not found");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private int readCommandId() {
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new IllegalArgumentException("Operation number must be an integer");
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

}
