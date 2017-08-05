package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ThreadReply;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadReplyRepository extends JpaRepository<ThreadReply, Integer> {
    @Query(value = "SELECT * FROM thread_reply where thread_reply_thread= :id ", nativeQuery = true)
    List<ThreadReply>findByThreadId(@Param("id") int id);
    
}
