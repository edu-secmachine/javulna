/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services.autodao;

import com.kalavit.javulna.model.User;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peti
 */
@Repository
public interface UserAutoDao extends JpaRepository<User, String>{
    public User findUserByName(String name);
}
