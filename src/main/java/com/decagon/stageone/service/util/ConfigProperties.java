/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package com.decagon.stageone.service.util;



import java.io.IOException;

import java.util.Properties;

import java.util.logging.Level;

import java.util.logging.Logger;



/**

 *

 * @author olugbenga.falodun

 */

public enum ConfigProperties {

    INSTANCE;



    private final Properties properties;



    ConfigProperties() {

        properties = new Properties();

        try {

            properties.load(getClass().getClassLoader().getResourceAsStream("techstage1.properties"));

        } catch (IOException | IllegalArgumentException | NullPointerException e) {

            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

        }

    }



    /**

     *

     * @return string

     */

    public String getSearchUrl() {

        return properties.getProperty("url.search");

    }



}