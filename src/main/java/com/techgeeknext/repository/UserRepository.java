package com.techgeeknext.repository;
import com.techgeeknext.model.MessageDao;
import com.techgeeknext.model.UserDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);



}