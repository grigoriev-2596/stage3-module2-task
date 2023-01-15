package com.mjc.school.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsMapper;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class NewsServiceTest {

    @Configuration
    @ComponentScan(basePackages = {"com.mjc.school.service",
            "com.mjc.school.repository",
            "com.mjc.school.controller",
            "com.mjc.school"})
    static class Config {

    }

    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    private BaseRepository<NewsModel, Long> newsRepository;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Test
    void getById() {
        final long id = 15;
        NewsDtoResponse serviceResponse = newsService.readById(id);
        assertEquals(id, serviceResponse.getId());
    }

    @Test
    void create() {
        NewsDtoRequest request = new NewsDtoRequest(null, "Test title", "Test content", (long) 14);
        NewsDtoResponse serviceResponse = newsService.create(request);
        List<NewsModel> news = newsRepository.readAll();

        NewsModel modelFromRepo = news.get(news.size() - 1);
        NewsModel modelFromService = newsMapper.DTOResponseToModel(serviceResponse);

        assertNotNull(serviceResponse.getLastUpdateDate());
        assertNotNull(serviceResponse.getCreationDate());
        assertNotNull(serviceResponse.getId());

        assertEquals(modelFromService, modelFromRepo);
    }

    @Test
    void getAll() {
        List<NewsModel> serviceNews = newsMapper.listOfResponsesToListOfModel(newsService.readAll());
        List<NewsModel> repoNews = newsRepository.readAll();

        assertEquals(repoNews, serviceNews);
    }

    @Test
    void update() {
        long id = 5, authorId = 7;
        String title = "Updated title", content = "Updated content";
        NewsDtoRequest newsForUpdate = new NewsDtoRequest(id, title, content, authorId);

        assertNotNull(newsService.update(newsForUpdate));
        Optional<NewsModel> maybeNullNews = newsRepository.readById(id);
        assertTrue(maybeNullNews.isPresent());
        NewsModel updatedNews = maybeNullNews.get();
        assertEquals(title, updatedNews.getTitle());
        assertEquals(content, updatedNews.getContent());
        assertEquals(authorId, updatedNews.getAuthorId());
        assertNotEquals(updatedNews.getLastUpdateDate(), updatedNews.getCreationDate());

    }

    @Test
    void delete() {
        long id = 7;
        assertTrue(newsService.deleteById(id));
        assertFalse(newsRepository.existById(id));
    }

}