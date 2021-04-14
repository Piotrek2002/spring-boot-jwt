package com.techgeeknext.model;

import java.util.List;
import java.util.PrimitiveIterator;

public class UserDto {
    private long id;
    private String username;
    private String password;
    private String groupName;
    private String MacAddress;
    private String UserIP;
    private String ActualData;
    private String Description;
    private List<String> MessagesTitle;
    private String Position;
    private String GroupDescription;
    private List<String> sharedFor;
    private String modes;
    private String dataOfStart;
    private String dataOfUpdate;
    private String email;
    private String SecretData1;
    private String SecretData2;
    private String SecretData3;
    private String SecretData4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<String> getMessagesTitle() {
        return MessagesTitle;
    }

    public void setMessagesTitle(List<String> messagesTitle) {
        MessagesTitle = messagesTitle;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getGroupDescription() {
        return GroupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        GroupDescription = groupDescription;
    }

    public List<String> getSharedFor() {
        return sharedFor;
    }

    public void setSharedFor(List<String> sharedFor) {
        this.sharedFor = sharedFor;
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
