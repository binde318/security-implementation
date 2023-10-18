package net.nannimguides.todo.service.impl;

import lombok.AllArgsConstructor;
import net.nannimguides.todo.dto.TodoDto;
import net.nannimguides.todo.entity.Todo;
import net.nannimguides.todo.exception.ResourceNotFoundException;
import net.nannimguides.todo.repository.TodoRepository;
import net.nannimguides.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto to Todo jpa entity
        Todo todo = modelMapper.map(todoDto,Todo.class);
//Todo todo = new Todo();
//todo.setTitle(todoDto.getTitle());
//todo.setDescription(todoDto.getDescription());
//todo.setCompleted(todoDto.isCompleted());

        //save the Todo into database

        Todo saveTodo=todoRepository.save(todo);

        //convert save Todo jpa entity object into TodoDto object
        TodoDto savedTodoDto = modelMapper.map(saveTodo,TodoDto.class);
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(saveTodo.getId());
//        savedTodoDto.setTitle(saveTodo.getTitle());
//        savedTodoDto.setDescription(saveTodo.getDescription());
//        savedTodoDto.setCompleted(saveTodo.isCompleted());
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo = todoRepository.findById(id).orElseThrow(
               () ->new ResourceNotFoundException("Todo with the id is not found " + id));

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAll() {
     List<Todo> todos= todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
       Todo todo= todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with the id" +id));
       todo.setTitle(todoDto.getTitle());
       todo.setDescription(todoDto.getDescription());
       todo.setCompleted(todoDto.isCompleted());
       Todo updatedTodo=todoRepository.save(todo);


        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with id is not found :"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
       Todo todo= todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with the id is not found :"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inComplete(Long id) {
        Todo todo= todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with the id is not found :"+id));
todo.setCompleted(Boolean.FALSE);
Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }


}
