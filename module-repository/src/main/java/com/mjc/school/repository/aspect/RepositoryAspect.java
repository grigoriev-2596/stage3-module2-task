package com.mjc.school.repository.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Aspect
@Component
public class RepositoryAspect {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public RepositoryAspect(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Around("@annotation(OnDelete) && args(authorId)")
    public boolean deleteNewsOnCascade(ProceedingJoinPoint joinPoint, Long authorId) throws Throwable {
        if (!(boolean) joinPoint.proceed()) {
            return false;
        }
        List<Long> newsIdToDelete = newsRepository.readAll().stream()
                .filter(news -> news.getAuthorId().equals(authorId))
                .map(NewsModel::getId).toList();
        for (Long id : newsIdToDelete) {
            newsRepository.deleteById(id);
        }
        return true;
    }
}
