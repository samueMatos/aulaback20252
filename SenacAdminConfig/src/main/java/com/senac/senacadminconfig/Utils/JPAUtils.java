package com.senac.senacadminconfig.Utils;

import jakarta.persistence.EntityManager;

public class JPAUtils {
    public static EntityManager getEntityManager() {

        return new EntityManager();
    }
}
