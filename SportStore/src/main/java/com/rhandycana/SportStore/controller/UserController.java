package com.rhandycana.SportStore.controller;

import com.rhandycana.SportStore.model.User;
import com.rhandycana.SportStore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getALLUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
        User result = userService.saveUser(user);
        if ("ERROR_NOMBRE_REPETIDO".equals(result.getFirstName())) {
            return "ERROR:El nombre ya fue agregado en la base de datos, usa uno nuevo";
        }
        if ("ERROR_APELLIDO_REPETIDO".equals(result.getLastName())) {
            return "ERROR: El apellido ya fue agregado en la base de datos, usa uno nuevo";
        }
        if ("ERROR_EMAIL_REPETIDO".equals(result.getEmail())) {
            return "ERROR: El email ya fue agregado ne la base de datos, usa uno nuevo";
        }
        return "Usuario agregado correctamente";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user){
        User result = userService.updateUser(id, user);
        if (result == null) {
            return "Usuario no encontrado";
        }
        if ("ERROR_NOMBRE_REPETIDO".equals(result.getFirstName())) {
            return "ERROR:El nombre ya fue agregado en la base de datos, usa uno nuevo";
        }
        if ("ERROR_APELLIDO_REPETIDO".equals(result.getLastName())) {
            return "ERROR: El apellido ya fue agregado en la base de datos, usa uno nuevo";
        }
        if ("ERROR_EMAIL_REPETIDO".equals(result.getEmail())) {
            return "ERROR: El email ya fue agregado ne la base de datos, usa uno nuevo";
        }
        return "Usuario actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if (user == null) {
            return "ERROR: El usuario no existe o ya fue eliminado";
        }
        userService.deleteUser(id);
        return "Usuario eliminado correctamente";
    }
}
