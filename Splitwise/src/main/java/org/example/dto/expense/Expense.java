package org.example.dto.expense;

import lombok.Data;
import org.example.dto.ExpenseType;
import org.example.dto.User;
import org.example.processor.ISplitStrategy;

import java.util.List;

@Data
public abstract class Expense {
    private String expenseId;
    private double totalAmount;
    private User paidBy;
    private List<Split> split;
    private ExpenseType expenseType;
    private String description;

    private ISplitStrategy splitStrategy;

     Expense(String expenseId, double totalAmount, User paidBy, List<Split> split,
             ExpenseType expenseType, String description, ISplitStrategy splitStrategy) {
        this.expenseId = expenseId;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.split = split;
        this.expenseType = expenseType;
        this.description = description;
        this.splitStrategy = splitStrategy;
    }

}
