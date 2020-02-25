package com.example.demo.util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void txt2String() {
        File file = new File("C:\\Users\\11437\\IdeaProjects\\demo\\src\\main\\resources\\static\\REGIONS.txt");
        System.out.println(ReadFile.txt2String(file));
    }

}