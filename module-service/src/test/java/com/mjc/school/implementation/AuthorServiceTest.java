package com.mjc.school.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.AuthorMapper;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(NewsServiceTest.Config.class)
class AuthorServiceTest {
    @Autowired
    private BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;
    @Autowired
    private BaseRepository<AuthorModel, Long> authorRepository;
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Test
    void getById() {
        final long id = 15;
        AuthorDtoResponse serviceResponse = authorService.readById(id);
        assertEquals(id, serviceResponse.getId());
    }

    @Test
    void create() {
        AuthorDtoRequest request = new AuthorDtoRequest(null, "Test name");
        AuthorDtoResponse serviceResponse = authorService.create(request);
        List<AuthorModel> authors = authorRepository.readAll();

        AuthorModel modelFromRepo = authors.get(authors.size() - 1);
        AuthorModel modelFromService = authorMapper.DTOResponseToModel(serviceResponse);

        assertNotNull(serviceResponse.getLastUpdateDate());
        assertNotNull(serviceResponse.getCreationDate());
        assertNotNull(serviceResponse.getId());

        assertEquals(modelFromService, modelFromRepo);
    }

    @Test
    void getAll() {
        List<AuthorModel> serviceAuthors = authorMapper.listOfResponsesToListOfModel(authorService.readAll());
        List<AuthorModel> repoAuthors = authorRepository.readAll();

        assertEquals(repoAuthors, serviceAuthors);
    }

    @Test
    void update() {
        final long id = 5;
        String name = "Updated name";
        AuthorDtoRequest authorForUpdate = new AuthorDtoRequest(id, name);

        assertNotNull(authorService.update(authorForUpdate));
        Optional<AuthorModel> maybeNullAuthor = authorRepository.readById(id);
        assertTrue(maybeNullAuthor.isPresent());
        AuthorModel updatedAuthor = maybeNullAuthor.get();
        assertEquals(name, updatedAuthor.getName());
        assertNotEquals(updatedAuthor.getLastUpdateDate(), updatedAuthor.getCreationDate());
    }

    @Test
    void delete() {
        long id = 7;
        assertTrue(authorService.deleteById(id));
        assertFalse(authorRepository.existById(id));
    }

}