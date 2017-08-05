/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ThreadAttachment;

/**
 *
 * @author Paul
 */
import edu.ouhk.comps380f.project.model.ThreadAttachment;
import edu.ouhk.comps380f.project.model.ThreadReply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadAttachmentRepository extends JpaRepository<ThreadAttachment, Integer> {
@Query(value = "SELECT * FROM thread_file WHERE thread_file_thread= :id", nativeQuery = true)
    List<ThreadAttachment>findByThreadId(@Param("id") int id);
    
    @Query(value = "SELECT * FROM THREAD_FILE where THREAD_FILE_THREAD=:id AND THREAD_FILE_NAME =:file ", nativeQuery = true)
    ThreadAttachment findByThreadIdandFileName(@Param("id") int id , @Param("file") String file);
    
}

