package com.epam.cdp.maksim.katuranau.module8.task3.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Friendship {

    private Integer userId1;
    private Integer userId2;
    private LocalDateTime timestamp;

    public Friendship() {
    }

    public Friendship(Integer userId1, Integer userId2, LocalDateTime timestamp) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.timestamp = timestamp;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
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
        Friendship that = (Friendship) o;
        return Objects.equals(userId1, that.userId1) &&
                Objects.equals(userId2, that.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", timestamp=" + timestamp +
                '}';
    }
}

