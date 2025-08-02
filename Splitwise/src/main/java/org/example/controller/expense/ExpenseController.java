package org.example.controller.expense;

import org.example.dto.expense.Expense;
import org.example.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense created successfully");
    }

    @PutMapping("/{expenseId}")
    public void updateExpense(@PathVariable String expenseId, @RequestBody Expense expense){
        expenseService.updateExpense(expenseId,expense);
    }

    public void deleteExpense(@PathVariable String expenseId){
        expenseService.deleteExpense(expenseId);
    }

    public void getExpense() {
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpenseByUserId(@PathVariable String userId) {
        return expenseService.getExpenseByUserId(userId);
    }


    @PostMapping("/settle")
    public void settleExpense(@RequestParam String expenseId,
                              @RequestParam String fromUserId,
                              @RequestParam String toUserId,
                              @RequestParam double amount) {
        expenseService.settleExpense(expenseId, fromUserId, toUserId, amount);
    }

}
