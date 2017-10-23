/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services.autodao;

import com.kalavit.javulna.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peti
 */
@Repository
public interface MessageAutoDao extends JpaRepository<Message, String>{
    
}
