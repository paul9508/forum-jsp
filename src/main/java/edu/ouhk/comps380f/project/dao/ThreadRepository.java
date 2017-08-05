package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.Thread;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadRepository extends JpaRepository<Thread, Integer> {
  @Query(value = "SELECT * FROM thread where category= :type ", nativeQuery = true)
    List<Thread>findByCategory(@Param("type") String type);
    
}
