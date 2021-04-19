package com.techgeeknext.controller;

import com.techgeeknext.model.UserDao;
import com.techgeeknext.model.UserDto;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@CrossOrigin
public class GroupController {

    private final GroupService groupService;
    private final UserRepository userRepository;


    public GroupController(GroupService groupService, UserRepository userRepository) {
        this.groupService = groupService;

        this.userRepository = userRepository;
    }

    //Dodaje użytkownika do grupy
    @RequestMapping(value = "/addUserOfGroup/{userName}", method = RequestMethod.POST)
    public ResponseEntity<?> addUserOffGroup(@PathVariable String userName , @AuthenticationPrincipal UserDetails customUser){

            return ResponseEntity.ok(groupService.addUserOfGroup(userName,customUser.getUsername()));




    }
    //Usuwa użytkownika z grupy
    @RequestMapping(value = "/removeUserOfGroup/{userName}", method = RequestMethod.POST)
    public ResponseEntity<?> removeUserOffGroup(@PathVariable String userName , @AuthenticationPrincipal UserDetails customUser){

            return ResponseEntity.ok(groupService.deleteUserOfGroup(userName,customUser.getUsername()));




    }
    //Zwraca listę użytkowników grupy, której jesteś administratorem
    @RequestMapping(value = "/usersOfGroup", method = RequestMethod.GET)
    public ResponseEntity<?> usersOffGroup(@AuthenticationPrincipal UserDetails customUser){
        Set<UserDto> usersOfGroup=groupService.UsersOfGroup(customUser.getUsername());
            return ResponseEntity.ok(usersOfGroup);


    }
    //Zwraca własne dane
    @RequestMapping(value = "/userData", method = RequestMethod.GET)
    public ResponseEntity<?> userData(@AuthenticationPrincipal UserDetails customUser){

        UserDto userDto=new UserDto();
        UserDao userDao=userRepository.findByUsername(customUser.getUsername());
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

        return ResponseEntity.ok(userDto);


    }


}
