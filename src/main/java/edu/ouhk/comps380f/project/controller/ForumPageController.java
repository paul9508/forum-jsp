/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.controller;

import edu.ouhk.comps380f.project.dao.ReplyAttachmentRepository;
import edu.ouhk.comps380f.project.dao.ThreadAttachmentRepository;
import edu.ouhk.comps380f.project.dao.ThreadReplyRepository;
import edu.ouhk.comps380f.project.dao.ThreadRepository;
import edu.ouhk.comps380f.project.model.ReplyAttachment;
import edu.ouhk.comps380f.project.model.ThreadAttachment;
import edu.ouhk.comps380f.project.model.Thread;
import edu.ouhk.comps380f.project.model.ThreadReply;
import edu.ouhk.comps380f.project.view.DownloadingView;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author s1153852
 */
@Controller
@RequestMapping("/")
public class ForumPageController {

    @Resource
    private ThreadRepository ThreadRepo;
    @Resource
    private ThreadReplyRepository ThreadReplyRepo;
    @Resource
    private ThreadAttachmentRepository ThreadAttachmentRepo;
    @Resource
    private ReplyAttachmentRepository ReplyAttachmentRepo;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String list(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "category/{categoryType}", method = RequestMethod.GET)
    public ModelAndView categoryview(@PathVariable("categoryType") String categoryType) {
        ModelAndView modelAndView = new ModelAndView("category");
        modelAndView.addObject("threads", ThreadRepo.findByCategory(categoryType));
        modelAndView.addObject("categoryType", categoryType);
        return modelAndView;
    }

    @RequestMapping(value = "category/{categoryType}/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            return new ModelAndView("addMessage", "thread", new Form());
        } else {
            return new ModelAndView(new RedirectView("/login", true));
        }
    }

    public static class Form {

        private String topic;
        private String content;
        private List<MultipartFile> attachments;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String subject) {
            this.topic = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    @RequestMapping(value = "category/{categoryType}/create", method = RequestMethod.POST)
    public View addThreadHandle(@PathVariable("categoryType") String categoryType, Form form, Principal principal, HttpServletRequest request) throws IOException {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            Thread thread = new Thread();
            thread.setTopic(form.getTopic());
            thread.setContent(form.getContent());
            thread.setType(categoryType);
            thread.setTime(new Date());
            thread.setUser(principal.getName());

            for (MultipartFile filePart : form.getAttachments()) {
                ThreadAttachment attachment = new ThreadAttachment();
                attachment.setName(filePart.getOriginalFilename());
                attachment.setMimeContentType(filePart.getContentType());
                attachment.setContents(filePart.getBytes());
                attachment.setThread(thread);
                if (attachment.getName() != null && attachment.getName().length() > 0
                        && attachment.getContents() != null && attachment.getContents().length > 0) {
                    thread.getAttachments().add(attachment);
                }
            }
            ThreadRepo.save(thread);
            return new RedirectView("/category/{categoryType}", true);
        } else {
            return new RedirectView("/login", true);
        }
    }

    @RequestMapping(value = "category/{categoryType}/{threadID}", method = RequestMethod.GET)
    public ModelAndView threadview(@PathVariable("categoryType") String categoryType, @PathVariable("threadID") int id) {
        ModelAndView modelAndView = new ModelAndView("thread");
        Thread thread = ThreadRepo.findOne(id);
        modelAndView.addObject("categoryType", categoryType);
        modelAndView.addObject("thread", thread);
        modelAndView.addObject("threadreplies", ThreadReplyRepo.findByThreadId(id));
        return modelAndView;
    }

    @RequestMapping(value = "category/{categoryType}/{threadID}/addreply", method = RequestMethod.GET)
    public ModelAndView createReply(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            return new ModelAndView("addReply", "reply", new FormReply());
        } else {
            return new ModelAndView(new RedirectView("/login", true));
        }
    }

    public static class FormReply {

        private String topic;
        private String content;
        private List<MultipartFile> attachments;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String subject) {
            this.topic = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    @RequestMapping(value = "category/{categoryType}/{threadID}/addreply", method = RequestMethod.POST)
    public View addReplyHandle(@PathVariable("categoryType") String categoryType, @PathVariable("threadID") int threadId, Form form, Principal principal, HttpServletRequest request) throws IOException {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            ThreadReply reply = new ThreadReply();
            reply.setThread(threadId);
            reply.setContent(form.getContent());
            reply.setTime(new Date());
            reply.setUser(principal.getName());

            for (MultipartFile filePart : form.getAttachments()) {
                ReplyAttachment attachment = new ReplyAttachment();
                attachment.setName(filePart.getOriginalFilename());
                attachment.setMimeContentType(filePart.getContentType());
                attachment.setContents(filePart.getBytes());
                attachment.setThreadreply(reply);
                if (attachment.getName() != null && attachment.getName().length() > 0
                        && attachment.getContents() != null && attachment.getContents().length > 0) {
                    reply.getAttachments().add(attachment);
                }
            }
            ThreadReplyRepo.save(reply);
            return new RedirectView("/category/{categoryType}/{threadID}/", true);
        } else {
            return new RedirectView("/login", true);
        }
    }

    @RequestMapping(value = "category/{categoryType}/{threadID}/delete/{id}", method = RequestMethod.GET)
    public View deleteReply(@PathVariable("id") int thread_reply_id, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return new RedirectView("/login", true);
        }
        ThreadReplyRepo.delete(thread_reply_id);
        return new RedirectView("/category/{categoryType}/{threadID}/", true);
    }

    @RequestMapping(value = "category/{categoryType}/delete/{id}", method = RequestMethod.GET)
    public View deleteThread(@PathVariable("id") int thread_id, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return new RedirectView("/login", true);
        }
        ThreadRepo.delete(thread_id);
        return new RedirectView("/category/{categoryType}", true);
    }

    @RequestMapping(
            value = "/category/{categoryType}/{threadId}/attachment/{attachment:.+}",
            method = RequestMethod.GET
    )
    public View threaddownload(@PathVariable("categoryType") String type, @PathVariable("threadId") int threadId,
            @PathVariable("attachment") String name, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            ThreadAttachment attachment = ThreadAttachmentRepo.findByThreadIdandFileName(threadId, name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }
            return new RedirectView("/category/{categoryType}/{threadId}/", true);
        } else {
            return new RedirectView("/login", true);
        }
    }

    @RequestMapping(
            value = "/category/{categoryType}/{threadId}/{replyId}/attachment/{attachment:.+}",
            method = RequestMethod.GET
    )
    public View replydownload(@PathVariable("categoryType") String type, @PathVariable("replyId") int replyId,
            @PathVariable("attachment") String name, HttpServletRequest request
    ) {
        if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
            ReplyAttachment attachment = ReplyAttachmentRepo.findByThreadIdandFileName(replyId, name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }
            return new RedirectView("/category/{categoryType}/{threadId}/", true);
        } else {
            return new RedirectView("/login", true);
        }

    }
}
