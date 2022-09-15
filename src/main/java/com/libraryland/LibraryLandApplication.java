package com.libraryland;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LibraryLandApplication {

    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("librarylandPU");
        EntityManager em = enf.createEntityManager();

        try {
            em.getTransaction().begin();
            
            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        enf.close();
    }

}
