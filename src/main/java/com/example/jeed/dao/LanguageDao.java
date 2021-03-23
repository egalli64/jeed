package com.example.jeed.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class LanguageDao extends Dao<Language, Integer> {
    public LanguageDao() {
        super(Language.class);
    }

    public List<Language> readAllOrderByName() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT l FROM Language l ORDER BY l.name";
            return em.createQuery(jpql, Language.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
