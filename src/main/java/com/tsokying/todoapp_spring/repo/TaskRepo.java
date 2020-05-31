package com.tsokying.todoapp_spring.repo;

import com.tsokying.todoapp_spring.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
    Task getById(Long id);

}
