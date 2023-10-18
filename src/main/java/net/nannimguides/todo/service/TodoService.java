package net.nannimguides.todo.service;

import net.nannimguides.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
     List<TodoDto> getAll();
     TodoDto updateTodo(TodoDto todoDto,Long id);
     void deleteTodo(Long id);
     TodoDto completeTodo(Long id);
     TodoDto inComplete(Long id);

}
