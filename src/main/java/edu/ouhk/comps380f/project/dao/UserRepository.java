/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author s1153307
 */
public interface UserRepository extends JpaRepository<ForumUser, String> {

}