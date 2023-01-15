package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class NewsManagementValidator {
    private static final int NEWS_TITLE_MIN_LENGTH = 5;
    private static final int NEWS_TITLE_MAX_LENGTH = 30;
    private static final int NEWS_CONTENT_MIN_LENGTH = 5;
    private static final int NEWS_CONTENT_MAX_LENGTH = 255;
    private static final int AUTHOR_NAME_MIN_LENGTH = 3;
    private static final int AUTHOR_NAME_MAX_LENGTH = 15;


    private void validateStringLength(String str, String fieldName, int min, int max) {
        if (str == null) {
            throw new ValidatorException(String.format(ErrorCode.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
        if (str.length() < min || str.length() > max) {
            throw new ValidatorException(String.format(ErrorCode.STRING_LENGTH_IS_INVALID.toString(), fieldName, min, max));
        }
    }

    public void validateNewsId(Long id) {
        validateNullId(id, "news id");
        if (id < 1) {
            throw new ValidatorException(ErrorCode.NEWS_ID_IS_INVALID.toString());
        }
    }

    public void validateAuthorId(Long id) {
        validateNullId(id, "author id");
        if (id < 1) {
            throw new ValidatorException(ErrorCode.AUTHOR_ID_IS_INVALID.toString());
        }
    }

    private void validateNullId(Long id, String fieldName) {
        if (id == null) {
            throw new ValidatorException(String.format(ErrorCode.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
    }

    public void validateNewsRequest(NewsDtoRequest request) {
        validateStringLength(request.getTitle(), "news title", NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateStringLength(request.getContent(), "news content", NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateAuthorId(request.getAuthorId());
    }

    public void validateAuthorRequest(AuthorDtoRequest request) {
        validateStringLength(request.getName(), "author name", AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
    }
}
