/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ThreadAttachment;
import edu.ouhk.comps380f.project.model.ThreadReply;
import edu.ouhk.comps380f.project.model.UserPoll;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author s1153852
 */
public interface UserPollRepository extends JpaRepository<UserPoll, Integer> {
    @Query(value = "SELECT * FROM user_poll where username=:username AND poll_id=(SELECT MAX(poll_id) FROM poll)", nativeQuery = true)
    UserPoll findUserPollByUsername(@Param("username") String username);
}