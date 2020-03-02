package com.example.demo.mapper;

import com.example.demo.model.Country;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CountryMapperTest {
@Autowired
CountryMapper countryMapper;
    @Test
    public void testSave(){
        countryMapper.insert2(new Country(null,"test3","test3",null));
    }
}