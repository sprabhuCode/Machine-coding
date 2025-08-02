package org.example.controller.user;

import org.example.dto.User;
import org.example.repository.UserRepository;
import org.example.service.ExpenseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final ExpenseService expenseService;

    public UserController(UserRepository userRepository, ExpenseService expenseService) {
        this.userRepository = userRepository;
        this.expenseService = expenseService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userRepository.addUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userRepository.deleteUser(userId);
    }

    @GetMapping("/owedTo/{userId}")
    public Map<String, Double> getUserOwedAmount(@PathVariable String userId) {
        return expenseService.getUserOwedAmount(userId);
    }

    @GetMapping("/owesTo/{userId}")
    public Map<String, Double> getUserOwesOthersBreakdown(@PathVariable String userId) {
        return expenseService.getUserOwesAmount(userId);
    }
}
