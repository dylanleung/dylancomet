package com.gdxh.web;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: dylan
 * Date: 12-12-26
 * Time: 下午8:33
 */
@XmlRootElement
public class Message {
    public String author = "";
    public String message = "";

    public Message() {
    }

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

}
