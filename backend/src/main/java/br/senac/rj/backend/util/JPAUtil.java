package br.senac.rj.backend.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Centraliza o acesso ao EntityManagerFactory.
 */
public class JPAUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("backendPU");

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}

