/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.dto;

import com.decagon.stageone.model.User;
import java.util.List;

/**
 *
 * @author Olugbenga.Falodun
 */
public class PageResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" + "page=" + page + ", per_page=" + per_page + ", total=" + total + ", total_pages=" + total_pages + ", data=" + data + '}';
    }
}
