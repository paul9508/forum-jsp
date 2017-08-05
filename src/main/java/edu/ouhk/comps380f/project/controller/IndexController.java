/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.controller;

import edu.ouhk.comps380f.project.dao.PollRepository;
import edu.ouhk.comps380f.project.dao.UserPollRepository;
import edu.ouhk.comps380f.project.model.UserPoll;
import java.security.Principal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Paul
 */
@Controller
public class IndexController {

    @Autowired
    PollRepository PollRepo;

    @Resource
    UserPollRepository UserPollRepo;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(ModelMap model, Principal principal) {
        String name;
        if (principal != null) {
            name = principal.getName();
            model.addAttribute("pollcheck", UserPollRepo.findUserPollByUsername(name));
        } else {
            model.addAttribute("pollcheck", "notreg");
        }
        model.addAttribute("poll", PollRepo.findPoll());
        model.addAttribute("answerpoll", new Form());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public View addUserPollHandle(Form form, Principal principal, ModelMap model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            int id = Integer.parseInt(form.getId());
            UserPoll poll = new UserPoll();
            poll.setUsername(principal.getName());
            poll.setPoll_id(id);
            UserPollRepo.save(poll);
            for (String answer : form.getAnswer()) {
                if (answer.equals("count1")) {
                    PollRepo.addUserPoll_1();
                } else if (answer.equals("count2")) {
                    PollRepo.addUserPoll_2();
                } else if (answer.equals("count3")) {
                    PollRepo.addUserPoll_3();
                } else {
                    PollRepo.addUserPoll_4();
                }
            }
            String name;
            if (principal != null) {
                name = principal.getName();
                model.addAttribute("pollcheck", UserPollRepo.findUserPollByUsername(name));
            } else {
                model.addAttribute("pollcheck", "notreg");
            }
            model.addAttribute("poll", PollRepo.findPoll());
            model.addAttribute("answerpoll", new Form());
            return new RedirectView("/", true);
        } else {
            return new RedirectView("/login", true);
        }
    }

    public static class Form {

        private String[] answer;
        private String id;

        public String[] getAnswer() {
            return answer;
        }

        public void setAnswer(String[] answer) {
            this.answer = answer;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
