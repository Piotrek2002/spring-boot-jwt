package com.techgeeknext.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GroupDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToOne
    private UserDao administrator;
    @ManyToMany
    private Set<UserDao> usersOfGroup=new HashSet<>();
    private String GroupDescription;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDao getAdministrator() {
        return administrator;
    }

    public void setAdministrator(UserDao administrator) {
        this.administrator = administrator;
    }

    public Set<UserDao> getUsersOfGroup() {
        return usersOfGroup;
    }

    public void setUsersOfGroup(Set<UserDao> usersOfGroup) {
        this.usersOfGroup = usersOfGroup;
    }

    public String getGroupDescription() {
        return GroupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        GroupDescription = groupDescription;
    }
}
