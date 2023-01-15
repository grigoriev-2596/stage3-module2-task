package com.mjc.school.menu;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.MenuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@Component
public class NewsManagementMenu {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public NewsManagementMenu(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    private void printMenu() {
        MenuConstant[] menuItems = MenuConstant.values();
        for (MenuConstant i : menuItems) {
            System.out.println(i.getId() + " - " + i.getName());
        }
    }

    public void runMenu(Scanner scanner) {
        String input = "not defined";
        while (true) {
            printMenu();
            System.out.print("Enter the number of operation \n>>");
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    exit(0);
                    break;
                case "1":
                    createNews(scanner);
                    break;
                case "2":
                    getAllNews(scanner);
                    break;
                case "3":
                    getNewsById(scanner);
                    break;
                case "4":
                    updateNews(scanner);
                    break;
                case "5":
                    deleteNews(scanner);
                    break;
                case "6":
                    createAuthor(scanner);
                    break;
                case "7":
                    getAllAuthors(scanner);
                    break;
                case "8":
                    getAuthorById(scanner);
                    break;
                case "9":
                    updateAuthor(scanner);
                    break;
                case "10":
                    deleteAuthor(scanner);
                    break;
                default:
                    System.out.println("Command not found");
            }
        }
    }

    private void createNews(Scanner scanner) {
        try {
            String title, content;
            long authorId;
            System.out.println("Operation: " + MenuConstant.CREATE_NEWS.getName());
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);
            NewsDtoResponse response = newsController.create(new NewsDtoRequest(null, title, content, authorId));
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllNews(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_ALL_NEWS.getName());
            List<NewsDtoResponse> news = newsController.readAll();

            for (NewsDtoResponse response : news) {
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void getNewsById(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_NEWS_BY_ID.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);
            NewsDtoResponse response = newsController.readById(newsId);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateNews(Scanner scanner) {
        try {
            String title, content;
            long authorId, newsId;
            System.out.println("Operation: " + MenuConstant.UPDATE_NEWS.getName());
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);
            NewsDtoResponse response = newsController.update(new NewsDtoRequest(newsId, title, content, authorId));
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteNews(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.DELETE_NEWS.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);
            System.out.println(newsController.deleteById(newsId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private long readId(Scanner scanner) {
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            throw new MenuException(ErrorCode.ID_MUST_BE_AN_INTEGER.toString());
        }
        long id = scanner.nextLong();
        scanner.nextLine();
        return id;
    }

    private void createAuthor(Scanner scanner) {
        try {
            String name;
            long authorId;
            System.out.println("Operation: " + MenuConstant.CREATE_AUTHOR.getName());
            System.out.print("Enter author name:\n>>");
            name = scanner.nextLine();
            AuthorDtoResponse response = authorController.create(new AuthorDtoRequest(null, name));
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllAuthors(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_ALL_AUTHORS.getName());
            List<AuthorDtoResponse> authors = authorController.readAll();

            for (AuthorDtoResponse response : authors) {
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAuthorById(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_AUTHOR_BY_ID.getName());
            long authorId;
            System.out.print("Enter news id:\n>>");
            authorId = readId(scanner);
            AuthorDtoResponse response = authorController.readById(authorId);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateAuthor(Scanner scanner) {
        try {
            String authorName;
            long authorId;
            System.out.println("Operation: " + MenuConstant.UPDATE_AUTHOR.getName());
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);
            System.out.print("Enter author name:\n>>");
            authorName = scanner.nextLine();
            AuthorDtoResponse response = authorController.update(new AuthorDtoRequest(authorId, authorName));
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAuthor(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuConstant.DELETE_AUTHOR.getName());
            long authorId;
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);
            System.out.println(authorController.deleteById(authorId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
