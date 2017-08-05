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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Paul
 */
@Entity
@Table(name = "reply_file")
public class ReplyAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_file_id")
    private int id;
    @Column(name = "reply_file_name")
    private String name;
    @Column(name = "reply_file_mimeContentType")
    private String mimeContentType;
    @Column(name = "reply_file_content")
    private byte[] contents;
    @Column(name = "reply_file_reply", insertable = false, updatable = false)
    private int replyID;
    @ManyToOne
    @JoinColumn(name = "reply_file_reply")
    private ThreadReply threadreply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeContentType() {
        return mimeContentType;
    }

    public void setMimeContentType(String mimeContentType) {
        this.mimeContentType = mimeContentType;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    public ThreadReply getThreadreply() {
        return threadreply;
    }

    public void setThreadreply(ThreadReply threadreply) {
        this.threadreply = threadreply;
    }

    

}
