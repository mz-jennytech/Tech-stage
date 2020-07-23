/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.model;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Olugbenga.Falodun
 */
public class User {

    private long id;
    private String username;
    private String about;
    private int submitted;
    private Date updated_at;
    private int submission_count;
    private int comment_count;
    private long created_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getSubmission_count() {
        return submission_count;
    }

    public void setSubmission_count(int submission_count) {
        this.submission_count = submission_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public static Comparator<User> commentCountComparator = (User user, User user2) -> {
        int cc1 = user.getComment_count();
        int cc2 = user2.getComment_count();
        return cc2 - cc1;
    };
    
    public static Comparator<User> createdAtComparator = (User user, User user2) -> {
        int ca1 = user.getComment_count();
        int ca2 = user2.getComment_count();
        return ca1 - ca2;
    };

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", about='" + about + '\''
                + ", submitted=" + submitted
                + ", updated_at=" + updated_at
                + ", submission_count=" + submission_count
                + ", comment_count=" + comment_count
                + ", created_at=" + created_at
                + '}';
    }
}
