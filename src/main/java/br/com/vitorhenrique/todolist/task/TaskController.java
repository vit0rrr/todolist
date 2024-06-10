package br.com.vitorhenrique.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskMoldel taskMoldel, HttpServletRequest request){
        System.out.println("Controller");
        var idUser = request.getAttribute("idUser");
        taskMoldel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskMoldel.getStartAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("a data de inicio deve ser maior que a atual");
        } 

        var task= this.taskRepository.save(taskMoldel);
        return  ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskMoldel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID)idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public TaskMoldel update (@RequestBody TaskMoldel taskMoldel, HttpServletRequest request, @PathVariable UUID id ){
        var idUser = request.getAttribute("idUser");
        taskMoldel.setIdUser((UUID)idUser);
        taskMoldel.setId(id);
        return this.taskRepository.save(taskMoldel);
    }
}

