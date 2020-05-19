package dd.mhja.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hthurow.tomcatjndi.TomcatJNDI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tester for RegionDao
 * 
 * Assumes the database contains the usual REGION table example from Oracle HR
 * schema
 * 
 * @author manny
 */
class RegionDaoTest {
    private RegionDao dao;

    @BeforeAll
    static void setup() {
        TomcatJNDI tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/test/resources/context.xml"));
        tomcatJNDI.start();
    }

    @BeforeEach
    void init() {
        dao = new RegionDao();
    }

    @Test
    void readAllRegions() {
        List<Region> regions = dao.readAll();
        assertThat(regions.size(), is(4));
    }

    @Test
    void createDelete() {
        Region region = new Region("Test Region");
        assertTrue(dao.create(region));

        int id = region.getId();
        assertThat(id, is(not(0)));
        assertThat(dao.readAll().size(), is(5));
        assertTrue(dao.delete(id));
        assertThat(dao.readAll().size(), is(4));
    }

    @Test
    void deleteNegative() {
        assertFalse(dao.delete(42));
    }

}
