/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.Account;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class AccountDAO extends BaseDAO<Account, Integer> {

    public AccountDAO(EntityManager entityManager) {
        super(entityManager, Account.class);
    }

    public Account checkLogin(String username, String password) {
        List<Account> result = entityManager.createNamedQuery("Account.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password).getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
