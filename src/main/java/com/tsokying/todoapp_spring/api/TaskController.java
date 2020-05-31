package com.tsokying.todoapp_spring.api;

import com.tsokying.todoapp_spring.domain.Task;
import com.tsokying.todoapp_spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<?> addTaskToBoard(@Valid @RequestBody Task task, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        Task newTask = taskService.saveOrUpdateTask((task));
        return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Task> getAllTask(){
        return taskService.findAll();
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<?> getById(@PathVariable Long task_id) {
        Task task = taskService.findById(task_id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<?> deleteTaskFromBoard(@PathVariable Long task_id) {
        taskService.delete(task_id);
        return new ResponseEntity<String>("Task deleted", HttpStatus.OK);
    }
}

