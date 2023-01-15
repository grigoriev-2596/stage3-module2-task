package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.data.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream()
                .filter(news -> Objects.equals(news.getId(), id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        newsModel.setId((long) dataSource.getNews().size() + 1);
        dataSource.getNews().add(newsModel);
        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        Optional<NewsModel> maybeNullModel = readById(newsModel.getId());
        if (maybeNullModel.isEmpty()) {
            return null;
        }
        NewsModel modelToUpdate = maybeNullModel.get();
        modelToUpdate.setAuthorId(newsModel.getAuthorId());
        modelToUpdate.setContent(newsModel.getContent());
        modelToUpdate.setTitle(newsModel.getTitle());
        modelToUpdate.setLastUpdateDate(newsModel.getLastUpdateDate());
        return modelToUpdate;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> maybeNullModel = readById(id);
        if (maybeNullModel.isEmpty()) return false;
        dataSource.getNews().remove(maybeNullModel.get());
        return true;
    }

    @Override
    public boolean existById(Long id) {
        return this.dataSource.getNews().stream().anyMatch((news) -> id.equals(news.getId()));
    }
}
