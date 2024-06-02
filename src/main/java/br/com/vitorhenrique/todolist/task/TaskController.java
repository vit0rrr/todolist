package br.com.vitorhenrique.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    @PostMapping("/")
    public TaskMoldel create(@RequestBody TaskMoldel taskMoldel){
        System.out.println("Controller");
        var task= this.taskRepository.save(taskMoldel);
        return task;
    }
}


