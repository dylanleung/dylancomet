package com.gdxh.web;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * User: dylan
 * Date: 12-12-26
 * Time: 下午8:29
 *
 */
@XmlRootElement
public class Response {
    public String text;
    public String author;
    public long time;

    public Response(String author, String text) {
        this.author = author;
        this.text = text;
        this.time = new Date().getTime();
    }

    public Response() {
    }
}
