/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.controller;

import java.util.List;
import com.decagon.stageone.service.UserService;

/**
 *
 * @author Olugbenga.Falodun
 */
public class UserController {

    private static UserService getUserService() {
        return new UserService();
    }

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Size :: " + getUsernames(500).size());
        
        System.out.println("Author Username :: " + getUsernameWithHighestCommentCount());
        
        System.out.println("Author Username :: " + getUsernamesSortedByRecordDate(1333104319));
    }

    public static List<String> getUsernames(int threshold) throws InterruptedException {
        UserService userService = getUserService();
        return userService.getUsernames(threshold);
    }

    public static String getUsernameWithHighestCommentCount() throws InterruptedException {
        UserService userService = getUserService();
        return userService.getUsernameWithHighestCommentCount();
    }

    public static List<String> getUsernamesSortedByRecordDate(int threshold) throws InterruptedException {
        UserService userService = getUserService();
        return userService.getUsernamesSortedByRecordDate(threshold);
    }
}
