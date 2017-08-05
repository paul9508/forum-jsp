/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.ForumUser;
import edu.ouhk.comps380f.project.model.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gary
 */
@Service
public class UserService implements UserDetailsService {

  @Resource
  UserRepository UserRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ForumUser user = UserRepo.findOne(username);
    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (UserRole role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getRole()));
    }
    return new User(user.getUsername(), user.getPassword(), authorities);
  }
}
