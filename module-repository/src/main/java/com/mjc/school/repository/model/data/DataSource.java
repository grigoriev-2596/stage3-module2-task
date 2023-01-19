package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.LinesReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("singleton")
public class DataSource {
    private static final String NEWS_TITLE_FILE_NAME = "news";
    private static final String NEWS_CONTENT_FILE_NAME = "content";
    private static final String AUTHORS_FILE_NAME = "authors";
    private static final int AMOUNT_OF_LINES_TO_READ = 20;
    private final List<NewsModel> news = new ArrayList<>();
    private final List<AuthorModel> authors = new ArrayList<>();

    public DataSource() {
        init();
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    private void init() {
        readAuthors();
        List<AuthorModel> shuffledAuthors = new ArrayList<>(this.authors);
        Collections.shuffle(shuffledAuthors);
        createNews(shuffledAuthors);
    }

    private void readAuthors() {
        List<String> authorsLines = LinesReader.readLines(AUTHORS_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        LocalDateTime randomDate;
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            randomDate = LinesReader.getRandomDate();
            authors.add(new AuthorModel((long) i + 1, authorsLines.get(i), randomDate, randomDate));
        }
    }

    private void createNews(List<AuthorModel> authors) {
        List<String> titles = LinesReader.readLines(NEWS_TITLE_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        List<String> contents = LinesReader.readLines(NEWS_CONTENT_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        LocalDateTime randomDate;
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            randomDate = LinesReader.getRandomDate();
            news.add(new NewsModel((long) i + 1, titles.get(i), contents.get(i), randomDate, randomDate, authors.get(i).getId()));
        }
    }


}
