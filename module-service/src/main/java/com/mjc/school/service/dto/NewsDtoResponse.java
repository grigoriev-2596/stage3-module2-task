package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public class NewsDtoResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime creationDate;
    private final LocalDateTime lastUpdateDate;
    private final Long authorId;

    public NewsDtoResponse(Long id, String title, String content, LocalDateTime creationDate, LocalDateTime lastUpdateDate, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "NewsDTOResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorId=" + authorId +
                '}';
    }
}
