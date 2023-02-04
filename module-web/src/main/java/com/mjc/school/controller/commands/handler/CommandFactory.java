package com.mjc.school.controller.commands.handler;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.commands.*;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandFactory {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public CommandFactory(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                          BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    //news commands
    public Command getCreateNewsCommand(String title, String content, Long authorId) {
        return new CreateCommand<>(newsController, new NewsDtoRequest(null, title, content, authorId));
    }

    public Command getReadAllNewsCommand() {
        return new ReadAllCommand<>(newsController);
    }

    public Command getReadNewsByIdCommand(Long id) {
        return new GetByIdCommand<>(newsController, id);
    }

    public Command getUpdateNewsCommand(Long id, String name, String content, Long authorId) {
        return new UpdateCommand<>(newsController, new NewsDtoRequest(id, name, content, authorId));
    }

    public Command getDeleteNewsCommand(Long id) {
        return new DeleteCommand<>(newsController, id);
    }


    //author commands
    public Command getCreateAuthorCommand(String name) {
        return new CreateCommand<>(authorController, new AuthorDtoRequest(null, name));
    }

    public Command getReadAllAuthorsCommand() {
        return new ReadAllCommand<>(authorController);
    }

    public Command getReadAuthorByIdCommand(Long id) {
        return new GetByIdCommand<>(authorController, id);
    }

    public Command getUpdateAuthorCommand(Long id, String name) {
        return new UpdateCommand<>(authorController, new AuthorDtoRequest(id, name));
    }

    public Command getDeleteAuthorCommand(Long id) {
        return new DeleteCommand<>(authorController, id);
    }

}
