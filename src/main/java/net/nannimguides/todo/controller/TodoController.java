package net.nannimguides.todo.controller;

import lombok.AllArgsConstructor;
import net.nannimguides.todo.dto.TodoDto;
import net.nannimguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
private TodoService todoService;
//Build add todo

    //method level security
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping
public ResponseEntity<TodoDto>addTod(@RequestBody TodoDto todoDto){
    TodoDto savedTodo=todoService.addTodo(todoDto);
    return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);

}
//Build get todo
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
@GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
    TodoDto todoDto=todoService.getTodo(todoId);

    return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

// Build getAll todo rest Api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
@GetMapping
    public ResponseEntity<List<TodoDto>> getAll(){
    List<TodoDto> todos=todoService.getAll();
    return new ResponseEntity<>(todos,HttpStatus.FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
@PutMapping("{id}")
    public ResponseEntity<TodoDto>updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id){
    TodoDto updatedTodo=todoService.updateTodo(todoDto,id);
    return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }

    //build delete Todo Rest Api
    @PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
    todoService.deleteTodo(id);
    return ResponseEntity.ok("Todo deleted successfully!");
    }
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
@PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
    TodoDto updatedTodo=todoService.completeTodo(todoId);
    return ResponseEntity.ok(updatedTodo);
    }

    //build incomplete todo api
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
@PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){
    TodoDto updatedTodo=todoService.inComplete(todoId);
    return ResponseEntity.ok(updatedTodo);
    }
}
