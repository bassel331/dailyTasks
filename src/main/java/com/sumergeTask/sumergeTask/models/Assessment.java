package com.sumergeTask.sumergeTask.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Assessment {
    @Id
    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
