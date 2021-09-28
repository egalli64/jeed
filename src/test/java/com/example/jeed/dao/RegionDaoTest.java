package com.example.jeed.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hthurow.tomcatjndi.TomcatJNDI;

/**
 * Tester for RegionDao
 * 
 * Assumes the database contains the REGION table as Oracle HR schema
 */
class RegionDaoTest {
    static private TomcatJNDI tomcatJNDI;
    private RegionDao dao;

    @BeforeAll
    static void setup() {
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/test/resources/context.xml"));
        tomcatJNDI.start();
    }

    @BeforeEach
    void init() {
        dao = new RegionDao();
    }

    @Test
    void readAll() {
        List<Region> regions = dao.readAll();
        assertThat(regions.size()).isEqualTo(4);
    }

    @Test
    void readPositive() {
        Optional<Region> region = dao.read(1);
        assertTrue(region.isPresent());
    }

    @Test
    void readNegative() {
        Optional<Region> region = dao.read(42);
        assertFalse(region.isPresent());
    }

    @Test
    void update() {
        Region region = new Region("Test Region");
        dao.create(region);
        region.setName("Another name");
        assertTrue(dao.update(region));
        dao.delete(region.getId());
    }

    @Test
    void createDelete() {
        Region region = new Region("Test Region");
        assertTrue(dao.create(region));

        int id = region.getId();
        assertThat(id).isNotEqualTo(0);
        assertThat(dao.readAll().size()).isEqualTo(5);
        assertTrue(dao.delete(id));
        assertThat(dao.readAll().size()).isEqualTo(4);
    }

    @Test
    void deleteNegative() {
        assertFalse(dao.delete(42));
    }
}
