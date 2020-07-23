/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.service;

import java.util.ArrayList;
import java.util.List;
import com.decagon.stageone.service.util.HttpUtil;
import com.decagon.stageone.service.util.ConfigProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.decagon.stageone.dto.PageResponse;
import com.decagon.stageone.model.User;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Olugbenga.Falodun
 */
public class UserService implements Runnable {

    private String searchUrl = "";

    public UserService() {
        this.searchUrl = ConfigProperties.INSTANCE.getSearchUrl();
    }

    public List<String> getUsernames(int threshold) throws InterruptedException {
        System.out.println("threshold :: " + this.searchUrl);

        String[] response = {};
        JsonObject conProperties = this.getConnectionProps(1);
        try {
            response = HttpUtil.sendGet(conProperties);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String> usernames = new ArrayList<>();
        if (response.length > 0 && response[0].equals("200")) {
            usernames = this.filterUsernames(this.compileUsernames(response[1]), threshold);
        }
        return usernames;
    }

    private JsonObject getConnectionProps(int pageNumber) {
        JsonObject conProperties = new JsonObject();
        conProperties.addProperty("url", this.searchUrl + pageNumber);
        return conProperties;
    }

    private List<User> compileUsernames(String pageData) throws InterruptedException {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PageResponse pageResponse = gson.fromJson(pageData, PageResponse.class);
         
        List<User> users = pageResponse.getData();
        int totalPages = pageResponse.getTotal_pages();
        
        Thread t;      
       t = new Thread(this, pageData);
       System.out.println("New thread: " + t);
         t.start();
       

        if (totalPages > 1) {
            String[] response = {};
            //TODO: With more time, I will add each each iteration to a different non-blocking 
            // asynchronous JAVA thread and then consolidate the result together. This is 
            for (int i = 2; i <= totalPages; i++) 
                 System.out.println(pageData + ": " + i);
           
            {
                try {
                    Thread.sleep(1000); 
                    int i = 0;
                    JsonObject conProperties = this.getConnectionProps(i);
                    response = HttpUtil.sendGet(conProperties);
                    if (response[0].equals("200")) {
                        pageResponse = gson.fromJson(response[1], PageResponse.class);
                        users.addAll(pageResponse.getData());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return users;
    }

    private List<String> filterUsernames(List<User> pageData, int threshold) {
        List<String> usernames = new ArrayList<>();

        pageData.stream().filter((user) -> (user.getSubmission_count() >= threshold)).forEachOrdered((user) -> {
            usernames.add(user.getUsername());
        });

        return usernames;
    }

    public String getUsernameWithHighestCommentCount() throws InterruptedException {

        String[] response = {};
        JsonObject conProperties = this.getConnectionProps(1);
        try {
            response = HttpUtil.sendGet(conProperties);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        User author = new User();
        if (response.length > 0 && response[0].equals("200")) {
            author = this.getAuthorWithHighestCommentCount(this.compileUsernames(response[1]));
        }

        return author.getUsername();
    }

    private User getAuthorWithHighestCommentCount(List<User> users) {
        Collections.sort(users, User.commentCountComparator);
        return users.get(0);
    }

    public List<String> getUsernamesSortedByRecordDate(int threshold) throws InterruptedException {

        String[] response = {};
        JsonObject conProperties = this.getConnectionProps(1);
        try {
            response = HttpUtil.sendGet(conProperties);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String> usernames = new ArrayList<>();
        if (response.length > 0 && response[0].equals("200")) {
            usernames = this.sortUserNames(this.compileUsernames(response[1]), threshold);
        }

        return usernames;
    }
    
    private List<String> sortUserNames(List<User> users, int threshold) {
        Collections.sort(users, User.createdAtComparator);
        List<String> usernames = new ArrayList<>();
        
        users.stream().filter((user) -> (user.getCreated_at() <= threshold)).forEachOrdered((user) -> {
            usernames.add(user.getUsername());
        });
        
        return usernames;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
