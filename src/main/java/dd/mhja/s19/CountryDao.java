package dd.mhja.s19;

import dd.mhja.dao.Dao;

public class CountryDao extends Dao<Country, Integer> {
    public CountryDao() {
        super(Country.class);
    }
}
