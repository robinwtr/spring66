package com.atguigu.spring6.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

public class FileSystemResourceDemo {


    public static void main(String[] args) {
        loadFileResource("D:\\JobHuntingJourney\\projectlll\\projectll\\spring66\\atguigu.txt");
        loadFileResource("atguigu.txt");
    }
    public static void loadFileResource(String path) {
        FileSystemResource  fileSystemResource = new FileSystemResource(path);

        System.out.println(fileSystemResource.getFilename());
        System.out.println(fileSystemResource.getDescription());
        try {
            InputStream in = fileSystemResource.getInputStream();
            byte[] b = new byte[16];
            while(in.read(b) != -1){
                System.out.println(new String(b));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
