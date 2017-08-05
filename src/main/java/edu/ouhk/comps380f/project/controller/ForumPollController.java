/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.controller;

import edu.ouhk.comps380f.project.dao.PollRepository;
import edu.ouhk.comps380f.project.dao.UserPollRepository;
import edu.ouhk.comps380f.project.model.Poll;
import edu.ouhk.comps380f.project.model.UserPoll;
import java.security.Principal;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Gary
 */
@Controller
@RequestMapping("/poll")
public class ForumPollController {

    @Autowired
    PollRepository PollRepo;

    @Resource
    UserPollRepository UserPollRepo;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView adminCreatePoll() {
        return new ModelAndView("addPoll", "poll", new Poll());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View addUserHandle(Poll entry) {
        PollRepo.adminCreatePoll(entry);
        return new RedirectView("/", true);
    }

    @RequestMapping(value = {"history"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("poll", PollRepo.findAllPoll());
        return "history";
    }

    public static class Form {

        private String[] answer;
        private int id;

        public String[] getAnswer() {
            return answer;
        }

        public void setAnswer(String[] answer) {
            this.answer = answer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
