package br.unipar.trabalho1B.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private EntityManagerUtil() {}

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("PizzariaPU");
            System.out.println("Conexão aberta!");
        }
        return emf;
    }

    public static EntityManager getEm() {
        if (em == null) {
            getEmf();
        }
        if (em == null || em.isOpen()) {
            em = emf.createEntityManager();
            System.out.println("Entity Manager criado!");
        }
        return em;
    }
}
