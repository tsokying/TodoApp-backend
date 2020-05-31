package com.tsokying.todoapp_spring.service;

import com.tsokying.todoapp_spring.domain.Task;
import com.tsokying.todoapp_spring.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Task saveOrUpdateTask(Task task){

        if(task.getStatus()==null || task.getStatus()==""){
            task.setStatus("TO_DO");
        }

        return taskRepo.save(task);
    }

    public Iterable<Task> findAll(){
        return taskRepo.findAll();
    }

    public Task findById(Long id){
        return taskRepo.getById(id);
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepo.delete(task);
    }
}
