package com.epam.cdp.maksim.katuranau.module8.task3.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Like {

    private Integer postId;
    private Integer userId;
    private LocalDateTime timestamp;

    public Like() {
    }

    public Like(Integer postId, Integer userId, LocalDateTime timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(postId, like.postId) &&
                Objects.equals(userId, like.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }

    @Override
    public String toString() {
        return "Like{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                '}';
    }
}

