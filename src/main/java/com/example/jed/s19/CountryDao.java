package com.example.jed.s19;

import com.example.jed.dao.Dao;

public class CountryDao extends Dao<Country, Integer> {
    public CountryDao() {
        super(Country.class);
    }
}
