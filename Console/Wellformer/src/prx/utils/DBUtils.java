/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import prx.config.SystemConfig;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class DBUtils implements Closeable {

    private static EntityManagerFactory entityManagerFactory;
    private static final Object LOCK = new Object();

    public DBUtils() {
    }

    public static EntityManager getEnttiyManager() {
        synchronized (LOCK) {
            if (entityManagerFactory == null) {
                try {
                    entityManagerFactory = Persistence.createEntityManagerFactory(SystemConfig.PU_NAME);
                } catch (Exception e) {
                    Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, e.getMessage());
                }
            }
        }
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void close() throws IOException {
        entityManagerFactory.close();
    }

}
