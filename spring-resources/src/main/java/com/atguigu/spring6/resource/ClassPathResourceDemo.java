package com.atguigu.spring6.resource;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResourceDemo {

    public static void loadClassPathResource(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        try {
            InputStream in = resource.getInputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                System.out.println(new String(b));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        loadClassPathResource("atguigu.txt");
    }
}
