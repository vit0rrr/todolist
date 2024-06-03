package br.com.vitorhenrique.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    @PostMapping("/")
    public TaskMoldel create(@RequestBody TaskMoldel taskMoldel, HttpServletRequest request){
        System.out.println("Controller");
        Object idUser = request.getAttribute("idUser");
        taskMoldel.setIdUser((UUID) idUser);
        var task= this.taskRepository.save(taskMoldel);
        return task;
    }
}


