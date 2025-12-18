package com.atguigu.spring6.resource;

import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

public class UrlResourceDemo {

    public static void main(String[] args) {
        loadUrlResource("http://www.baidu.com");
        loadUrlResource("file:atguigu.txt");
    }

    //访问http
    public static void loadUrlResource(String path) {
        try {
            UrlResource  urlResource = new UrlResource(path);
            System.out.println(urlResource.getFilename());
            System.out.println(urlResource.getURL());
            System.out.println(urlResource.getDescription());
            System.out.println(urlResource.getInputStream().read());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
