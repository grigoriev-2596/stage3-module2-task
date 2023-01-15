package com.mjc.school.repository.model;

import com.mjc.school.repository.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuthorModel implements BaseEntity<Long> {
    private Long id;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public AuthorModel(Long id, String name, LocalDateTime creationDate, LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorModel that = (AuthorModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
