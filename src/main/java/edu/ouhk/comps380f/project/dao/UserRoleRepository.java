/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author s1158854
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
  
}
