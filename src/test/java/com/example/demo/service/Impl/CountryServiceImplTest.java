package com.example.demo.service.Impl;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CountryServiceImplTest {
@Autowired
    CountryService countryService;
    @Test
    void batchSave() {countryService.batchSave();
    }
    @Test
    void pop(){
        List<Country> list= countryService.popCountries();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ",");
        }

    }
}