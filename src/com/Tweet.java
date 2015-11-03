/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sanji
 */
public class Tweet implements Serializable {

    private String text;
    private Date createdAt;
    private long id;

    public Tweet(Long id, Date createdAt, String text) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return text + "  [" + createdAt.toString() + "]   id: " + id;
    }

}
