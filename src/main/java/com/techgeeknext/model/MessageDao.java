package com.techgeeknext.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private UserDao recipient;
    @ManyToOne
    private UserDao sender;
    private String date;
    private String messageTitle;

    private String contents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDao getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDao recipient) {
        this.recipient = recipient;
    }

    public UserDao getSender() {
        return sender;
    }

    public void setSender(UserDao sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
