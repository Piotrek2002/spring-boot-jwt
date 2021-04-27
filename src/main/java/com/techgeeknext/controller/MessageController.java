package com.techgeeknext.controller;

import com.techgeeknext.model.MessageDao;
import com.techgeeknext.model.MessageDto;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.repository.MessageRepository;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.MessageService;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageService messageService;

    public MessageController(MessageRepository messageRepository, UserRepository userRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageService = messageService;
    }


    //Wysyła wiadomość do użytkownika
    @RequestMapping(value = "/addMessageToUser/{administrator}", method = RequestMethod.POST)
    public ResponseEntity<?> addMessageToUser(@PathVariable String administrator , @RequestBody MessageDto messageDto, @AuthenticationPrincipal UserDetails customUser){
        UserDao userDao=userRepository.findByUsername(administrator);
        Set<UserDao> users = userDao.getAdministratedGroup().getUsersOfGroup();
        UserDao user =userRepository.findByUsername(customUser.getUsername());
        if (users.contains(user)){
            return ResponseEntity.ok(messageService.addMessageToUser(customUser.getUsername(),administrator,messageDto));
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Brak dostępu");
        }

    }
    //zwraca listę otrzymanych wiadomości
    @RequestMapping(value = "/messageReceivedList", method = RequestMethod.GET)
    public ResponseEntity<?> MessageReceivedList( @AuthenticationPrincipal UserDetails customUser){

        List<MessageDao> messageDaoList=messageRepository.findAllReceived(customUser.getUsername());
        List<MessageDto> messageDtoList=new ArrayList<>();
        Iterator<MessageDao> iterator=messageDaoList.iterator();
        while (iterator.hasNext()){
            MessageDto messageDto=new MessageDto();
            MessageDao messageDao=iterator.next();
            messageDto.setContents(messageDao.getContents());
            messageDto.setDate(String.valueOf(messageDao.getDate()));
            messageDto.setMessageTitle(messageDao.getMessageTitle());
            messageDto.setRecipient(messageDao.getRecipient().getUsername());
            messageDto.setSender(messageDao.getSender().getUsername());
            messageDto.setId(messageDao.getId());
            messageDtoList.add(messageDto);
        }
        return ResponseEntity.ok(messageDtoList);
    }
    //Zwraca listę wysłanych wiadomości
    @RequestMapping(value = "/messageSentList", method = RequestMethod.GET)
    public ResponseEntity<?> MessageSentList( @AuthenticationPrincipal UserDetails customUser){

        List<MessageDao> messageDaoList=messageRepository.findAllSent(customUser.getUsername());
        List<MessageDto> messageDtoList=new ArrayList<>();
        Iterator<MessageDao> iterator=messageDaoList.iterator();
        while (iterator.hasNext()){
            MessageDto messageDto=new MessageDto();
            MessageDao messageDao=iterator.next();
            messageDto.setContents(messageDao.getContents());
            messageDto.setDate(messageDao.getDate());
            messageDto.setMessageTitle(messageDao.getMessageTitle());
            messageDto.setRecipient(messageDao.getRecipient().getUsername());
            messageDto.setSender(messageDao.getSender().getUsername());
            messageDto.setId(messageDao.getId());
            messageDtoList.add(messageDto);
        }
        return ResponseEntity.ok(messageDtoList);
    }
    @RequestMapping(value = "/deleteReceivedMessage/{messageId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteReceivedMessage(@PathVariable long messageId , @AuthenticationPrincipal UserDetails customUser){



        UserDao user =userRepository.findByUsername(customUser.getUsername());
        List<MessageDao> messageDaoList=user.getMessagesSent();
        MessageDao messageDao=messageRepository.findById(messageId);
        if (messageDaoList.contains(messageDao)){
            messageRepository.delete(messageDao);
            return ResponseEntity.ok("usunięto");
            }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Brak dostępu");
        }



    }


}
