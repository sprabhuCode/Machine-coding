package org.example.repository;

import org.example.dto.expense.Expense;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExpenseRepository {

    Map<String, Expense> expenseMap = new HashMap<>();

    public void addExpense(Expense expense) {
        expenseMap.put(expense.getExpenseId(), expense);
        System.out.println("Expense Added expenseId : " + expense.getExpenseId() + " Expense : " + expense);
    }

    public Expense getExpense(String expenseId) {
        if(expenseMap.containsKey(expenseId)) {
            return expenseMap.get(expenseId);
        } else {
            System.out.println("expenseId: " + expenseId + " not present in the system");
            return null;
        }
    }

    public void deleteExpense(String expenseId){
        expenseMap.remove(expenseId);
    }

    public void updateExpense(String expenseId, Expense updatedExpense) {
        expenseMap.put(expenseId, updatedExpense);
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenseMap.values());
    }

    public List<Expense> getExpenseByUserId(String userId) {
        List<Expense> expenses = new ArrayList<>();

        for(Expense expense : expenseMap.values()) {
            if(expense.getPaidBy().getUserId().equals(userId) ||
            expense.getSplit().stream().anyMatch(split -> split.getUserId().equals(userId))) {
                expenses.add(expense);
            }
        }
        return expenses;
    }
}
