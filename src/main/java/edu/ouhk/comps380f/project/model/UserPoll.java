

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author s1153852
 */
@Entity
@Table(name = "user_poll")
public class UserPoll {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_poll_id;
  @Column(updatable = false)
  private String username;
  @Column(updatable = false)
  private int poll_id;

  public int getUser_poll_id() {
    return user_poll_id;
  }

  public void setUser_poll_id(int user_poll_id) {
    this.user_poll_id = user_poll_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getPoll_id() {
    return poll_id;
  }

  public void setPoll_id(int poll_id) {
    this.poll_id = poll_id;
  }
  
  
  
  
}
