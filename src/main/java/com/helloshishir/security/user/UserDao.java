package com.helloshishir.security.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDao {

    @Autowired
    EntityManager em;



    public void updateAuthenticationType(String username, AuthenticationType authType) {
        String sql = "UPDATE `_user` SET `auth_type` = '"+authType+"' WHERE `email` = '"+username+"'";

        System.err.println("@@@@ "+username);
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
}
