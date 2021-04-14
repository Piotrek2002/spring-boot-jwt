package com.techgeeknext.repository;

import com.techgeeknext.model.GroupDao;
import com.techgeeknext.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupDao, Integer> {
}
