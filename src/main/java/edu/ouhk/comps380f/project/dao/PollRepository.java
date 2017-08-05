/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.Poll;
import java.util.List;

/**
 *
 * @author s1158854
 */
public interface PollRepository {
  
  public void adminCreatePoll(Poll poll);
  
  public List<Poll> findPoll();
  
  public List<Poll> findAllPoll();
  public void addUserPoll_1();
  public void addUserPoll_2();
  public void addUserPoll_3();
  public void addUserPoll_4();
  public Poll findByPollId(int id);
}
