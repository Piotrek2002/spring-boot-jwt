package com.techgeeknext.repository;

import com.techgeeknext.model.GroupDao;

import com.techgeeknext.model.MessageDao;
import com.techgeeknext.model.UserDao;
import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageDao, Integer> {
    @Query("select m from MessageDao m where m.sender.username=?1")
    List<MessageDao> findAllSent(String username);
    @Query("select m from MessageDao m where m.recipient.username=?1")
    List<MessageDao> findAllReceived(String username);
}
