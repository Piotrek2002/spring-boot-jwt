package com.techgeeknext.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    @Column
    @JsonIgnore
    private String password;

    @OneToOne(mappedBy = "administrator", cascade = CascadeType.REMOVE)
    private GroupDao administratedGroup;

    private String MacAddress;
    private String UserIP;
    private String ActualData;
    private String Description;
    @OneToMany(mappedBy = "sender")
    private List<MessageDao> MessagesReceived=new ArrayList<>();
    @OneToMany(mappedBy = "recipient")
    private List<MessageDao> messagesSent=new ArrayList<>();
    private String Position;


    private String modes;
    private String dataOfStart;
    private String dataOfUpdate;
    private String email;
    private String SecretData1;
    private String SecretData2;
    private String SecretData3;
    private String SecretData4;

    public UserDao() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupDao getAdministratedGroup() {
        return administratedGroup;
    }

    public void setAdministratedGroup(GroupDao administratedGroup) {
        this.administratedGroup = administratedGroup;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getUserIP() {
        return UserIP;
    }

    public void setUserIP(String userIP) {
        UserIP = userIP;
    }

    public String getActualData() {
        return ActualData;
    }

    public void setActualData(String actualData) {
        ActualData = actualData;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<MessageDao> getMessagesReceived() {
        return MessagesReceived;
    }

    public void setMessagesReceived(List<MessageDao> messagesReceived) {
        MessagesReceived = messagesReceived;
    }

    public List<MessageDao> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<MessageDao> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }



    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getDataOfStart() {
        return dataOfStart;
    }

    public void setDataOfStart(String dataOfStart) {
        this.dataOfStart = dataOfStart;
    }

    public String getDataOfUpdate() {
        return dataOfUpdate;
    }

    public void setDataOfUpdate(String dataOfUpdate) {
        this.dataOfUpdate = dataOfUpdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretData1() {
        return SecretData1;
    }

    public void setSecretData1(String secretData1) {
        SecretData1 = secretData1;
    }

    public String getSecretData2() {
        return SecretData2;
    }

    public void setSecretData2(String secretData2) {
        SecretData2 = secretData2;
    }

    public String getSecretData3() {
        return SecretData3;
    }

    public void setSecretData3(String secretData3) {
        SecretData3 = secretData3;
    }

    public String getSecretData4() {
        return SecretData4;
    }

    public void setSecretData4(String secretData4) {
        SecretData4 = secretData4;
    }
}

