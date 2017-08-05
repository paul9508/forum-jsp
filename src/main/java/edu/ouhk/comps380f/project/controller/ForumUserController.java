/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.controller;

import edu.ouhk.comps380f.project.dao.UserRepository;
import edu.ouhk.comps380f.project.model.ForumUser;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Paul
 */
@Controller
@RequestMapping("/")
public class ForumUserController {

    @Resource
    UserRepository UserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static class Form {

        private String username;
        private String password;
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView addUserForm() {
        return new ModelAndView("register", "user", new ForumUser());
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public View addUserHandle(Form form) throws IOException {
        ForumUser user = new ForumUser(form.getUsername(),
                passwordEncoder.encode(form.getPassword()),
                form.getRoles()
        );
        UserRepo.save(user);
        return new RedirectView("/", true);
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

}
