package com.techgeeknext.service;

import com.techgeeknext.model.GroupDao;
import com.techgeeknext.model.GroupDto;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.model.UserDto;
import com.techgeeknext.repository.GroupRepository;
import com.techgeeknext.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public GroupDto addUserOfGroup(String userName, String administrator) {
        GroupDto groupDto = new GroupDto();
        UserDao user=userRepository.findByUsername(administrator);
        UserDao userToAdd=userRepository.findByUsername(userName);
        Set<UserDao> users=user.getAdministratedGroup().getUsersOfGroup();
        users.add(userToAdd);
        GroupDao groupDao=user.getAdministratedGroup();
        groupDao.setUsersOfGroup(users);
        groupRepository.save(groupDao);
        groupDto.setAdministratorName(user.getUsername());
        groupDto.setGroupDescription(user.getAdministratedGroup().getGroupDescription());
        groupDto.setName(user.getAdministratedGroup().getName());
        groupDto.setId(user.getAdministratedGroup().getId());
        return groupDto;
    }
    public GroupDto deleteUserOfGroup(String userName, String administrator) {
        GroupDto groupDto = new GroupDto();
        UserDao user=userRepository.findByUsername(administrator);
        UserDao userToRemove=userRepository.findByUsername(userName);
        Set<UserDao> users=user.getAdministratedGroup().getUsersOfGroup();
        users.remove(userToRemove);
        GroupDao groupDao=user.getAdministratedGroup();
        groupDao.setUsersOfGroup(users);
        groupRepository.save(groupDao);
        groupDto.setAdministratorName(user.getUsername());
        groupDto.setGroupDescription(user.getAdministratedGroup().getGroupDescription());
        groupDto.setName(user.getAdministratedGroup().getName());
        groupDto.setId(user.getAdministratedGroup().getId());
        return groupDto;
    }
    public Set<UserDto> UsersOfGroup(String administrator){
        Set<UserDao> userDaoList=userRepository.findByUsername(administrator).getAdministratedGroup().getUsersOfGroup();
        Iterator<UserDao> it=userDaoList.iterator();
        Set<UserDto> userDtoSet=new HashSet<>();
        while(it.hasNext()){
            UserDto userDto=new UserDto();
            UserDao userDao=it.next();
            userDto.setUsername(userDao.getUsername());
            userDto.setDescription(userDao.getDescription());
            userDto.setEmail(userDao.getDescription());
            userDto.setActualData(String.valueOf(userDao.getActualData()));
            userDto.setDataOfStart(String.valueOf(userDao.getDataOfStart()));
            userDto.setDataOfUpdate(String.valueOf(userDao.getDataOfUpdate()));
            userDto.setMacAddress(userDao.getMacAddress());
            userDto.setGroupDescription(userDao.getAdministratedGroup().getGroupDescription());
            userDto.setGroupName(userDao.getAdministratedGroup().getName());
            userDto.setModes(userDao.getModes());
            userDto.setPosition(userDao.getPosition());
            userDto.setSecretData1(userDao.getSecretData1());
            userDto.setSecretData2(userDao.getSecretData2());
            userDto.setSecretData3(userDao.getSecretData3());
            userDto.setSecretData4(userDao.getSecretData4());
            userDtoSet.add(userDto);
        }
        return userDtoSet;
    }
}
