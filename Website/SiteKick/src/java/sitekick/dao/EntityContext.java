/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.dao;

import java.io.Closeable;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class EntityContext implements Closeable {

    public static String PU_NAME = "SiteKickPU";
    protected EntityManager entityManager;
    protected EntityManagerFactory entityManagerFactory;
    protected EntityTransaction transaction;

    public static EntityContext newInstance() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(PU_NAME);
        EntityManager entitymanager = emfactory.createEntityManager();
        EntityContext context = new EntityContext();
        context.transaction = entitymanager.getTransaction();
        context.entityManager = entitymanager;
        context.entityManagerFactory = emfactory;
        return context;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }

    public void beginTransaction() {
        transaction.begin();
    }

    public void commitTransaction() {
        transaction.commit();
    }

    public void rollBackTransaction() {
        transaction.rollback();
    }

    public void setTransaction(EntityTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void close() throws IOException {
        entityManager.close();
        entityManagerFactory.close();
    }
}
