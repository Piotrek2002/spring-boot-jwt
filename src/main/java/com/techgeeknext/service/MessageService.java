package com.techgeeknext.service;

import com.techgeeknext.model.*;
import com.techgeeknext.repository.MessageRepository;
import com.techgeeknext.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public MessageDao addMessageToUser(String userName, String administrator, MessageDto messageDto) {
        MessageDao messageDao=new MessageDao();
        messageDao.setContents(messageDto.getContents());
        messageDao.setDate(String.valueOf(LocalDateTime.now()));
        messageDao.setMessageTitle(messageDto.getMessageTitle());
        messageDao.setRecipient(userRepository.findByUsername(administrator));
        messageDao.setSender(userRepository.findByUsername(userName));

        return messageRepository.save(messageDao);
    }

}
