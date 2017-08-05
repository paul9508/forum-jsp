/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ReplyAttachment;

/**
 *
 * @author Paul
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyAttachmentRepository extends JpaRepository<ReplyAttachment, Integer> {
@Query(value = "SELECT * FROM reply_file WHERE reply_file_reply= :id", nativeQuery = true)
    List<ReplyAttachment>findByThreadId(@Param("id") int id);
    
    @Query(value = "SELECT * FROM REPLY_FILE where REPLY_FILE_REPLY=:id AND REPLY_FILE_NAME =:file ", nativeQuery = true)
    ReplyAttachment findByThreadIdandFileName(@Param("id") int id , @Param("file") String file);
}

