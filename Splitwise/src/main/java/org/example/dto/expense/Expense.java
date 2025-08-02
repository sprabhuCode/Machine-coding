package org.example.dto.expense;

import lombok.Data;
import org.example.dto.ExpenseType;
import org.example.dto.User;

import java.util.List;

@Data
public abstract class Expense {
    private String expenseId;
    private double totalAmount;
    private User paidBy;
    private List<Split> split;
    private ExpenseType expenseType;
    private String description;

     Expense(String expenseId, double totalAmount, User paidBy, List<Split> split,
             ExpenseType expenseType, String description) {
        this.expenseId = expenseId;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.split = split;
        this.expenseType = expenseType;
        this.description = description;
    }

}
