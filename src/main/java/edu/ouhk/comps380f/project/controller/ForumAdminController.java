/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.controller;

import edu.ouhk.comps380f.project.dao.ThreadReplyRepository;
import edu.ouhk.comps380f.project.dao.ThreadRepository;
import edu.ouhk.comps380f.project.dao.UserRepository;
import edu.ouhk.comps380f.project.model.ForumUser;
import edu.ouhk.comps380f.project.model.UserRole;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/user")
public class ForumAdminController {

    @Resource
    UserRepository UserRepo;

    @Resource
    ThreadRepository ThreadRepo;

    @Resource
    ThreadReplyRepository ThreadReplyRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("forumUsers", UserRepo.findAll());
        return "listUser";
    }

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

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView admincreate() {
        return new ModelAndView("addUser", "forumUser", new Form());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View admincreate(Form form) throws IOException {
        ForumUser user = new ForumUser(form.getUsername(),
                passwordEncoder.encode(form.getPassword()),
                form.getRoles()
        );
        UserRepo.save(user);
        return new RedirectView("/user/list", true);
    }

    @RequestMapping(value = "delete/{username}", method = RequestMethod.GET)
    public View deleteUser(@PathVariable("username") String username) {
        UserRepo.delete(username);
        return new RedirectView("/user/list", true);
    }

    public static class editForm {

        private String username;
        private String password;
        private ArrayList<String> roles;

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

        public ArrayList<String> getRoles() {
            return roles;
        }

        public void setRoles(ArrayList<String> roles) {
            this.roles = roles;
        }

    }

    @RequestMapping(value = "edit/{username}", method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable("username") String username) {

        ForumUser user = UserRepo.findOne(username);

        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("username", user);

        editForm forumUser = new editForm();
        forumUser.setUsername(user.getUsername());
        ArrayList<String> roles = new ArrayList<String>();
        for (UserRole role : user.getRoles()) {
            String userrole = role.getRole();
            roles.add(userrole);
        }
        forumUser.setRoles(roles);
        modelAndView.addObject("forumUser", forumUser);

        return modelAndView;
    }

    @RequestMapping(value = "edit/{username}", method = RequestMethod.POST)
    public View edit(@PathVariable("username") String username, Form form) throws IOException {
        String password;
        ForumUser user = UserRepo.findOne(form.getUsername());
        List<UserRole> addroles = new ArrayList<>();
        if (form.getPassword() == null && form.getPassword().isEmpty()) {
            for (String addrole : form.getRoles()) {
                addroles.add(new UserRole(user, addrole));
            }
            user.setRoles(addroles);
        } else {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
            for (String addrole : form.getRoles()) {
                addroles.add(new UserRole(user, addrole));
            }
            user.setRoles(addroles);
        }
        UserRepo.save(user);
        return new RedirectView("/user/list", true);
    }

}
