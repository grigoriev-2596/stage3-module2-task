package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsMapper;
import com.mjc.school.service.validation.NewsManagementValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final NewsManagementValidator newsValidator;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> repository, NewsManagementValidator validator) {
        this.newsRepository = repository;
        this.newsValidator = validator;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsMapper.listOfModelsToListOfResponses(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        newsValidator.validateNewsId(id);
        Optional<NewsModel> maybeNullModel = newsRepository.readById(id);
        if (maybeNullModel.isEmpty()) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return newsMapper.modelToDTOResponse(maybeNullModel.get());
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        newsValidator.validateNewsRequest(createRequest);
        NewsModel model = newsMapper.DtoRequestToModel(createRequest);
        LocalDateTime creationDate = LocalDateTime.now();
        model.setCreationDate(creationDate);
        model.setLastUpdateDate(creationDate);
        model = newsRepository.create(model);
        return newsMapper.modelToDTOResponse(model);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        newsValidator.validateNewsRequest(updateRequest);
        newsValidator.validateNewsId(updateRequest.getId());
        NewsModel requestModel = newsMapper.DtoRequestToModel(updateRequest);
        requestModel.setLastUpdateDate(LocalDateTime.now());
        NewsModel updateModel = newsRepository.update(requestModel);
        if (updateModel == null) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), updateRequest.getId()));
        }
        return newsMapper.modelToDTOResponse(updateModel);
    }

    @Override
    public boolean deleteById(Long id) {
        newsValidator.validateNewsId(id);
        if (!newsRepository.deleteById(id)) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return true;
    }
}
