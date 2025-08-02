package org.example.service;

import org.example.dto.expense.Expense;
import org.example.dto.expense.Split;
import org.example.factory.SplitStrategyFactory;
import org.example.processor.ISplitStrategy;
import org.example.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public boolean addExpense(Expense expense) {
        ISplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(expense.getExpenseType());

        if (!splitStrategy.validateSplit(expense.getSplit(), expense.getTotalAmount())) {
            throw new IllegalArgumentException("Split is not valid for type: " + expense.getExpenseType());
        }

        List<Split> splits = splitStrategy.computeSplitData(expense.getSplit(), expense.getTotalAmount());
        expense.setSplit(splits);

        expenseRepository.addExpense(expense);
        return true;
    }

    public boolean updateExpense(String expenseId, Expense expense) {
        ISplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(expense.getExpenseType());

        if (!splitStrategy.validateSplit(expense.getSplit(), expense.getTotalAmount())) {
            throw new IllegalArgumentException("Updated split is not valid for type: " + expense.getExpenseType());
        }

        List<Split> updatedSplits = splitStrategy.computeSplitData(expense.getSplit(), expense.getTotalAmount());
        expense.setSplit(updatedSplits);

        expenseRepository.addExpense(expense);
        return true;
    }

    public void deleteExpense(String expenseId) {
        expenseRepository.deleteExpense(expenseId);
    }

    public List<Expense> getExpenseByUserId(String userId) {
        return expenseRepository.getExpenseByUserId(userId);
    }

    public void settleExpense(String expenseId, String toUserId, String fromUserId, double amount) {
        Expense expense = expenseRepository.getExpense(expenseId);
        if (Objects.isNull(expense)) {
            throw new IllegalArgumentException("Expense not found, Invalid expenseId: " + expenseId);
        }

        for( Split split : expense.getSplit()){
            if(split.getUserId().equals(fromUserId)) {
                split.setAmountOwed(split.getAmountOwed()-amount);
            }
        }
    }

    public Map<String, Double> getUserOwedAmount(String userId) {

        return expenseRepository.getAllExpenses().stream()
                .filter(expense -> expense.getPaidBy().equals(userId))
                .flatMap(expense -> expense.getSplit()
                        .stream().filter(split -> !split.getUserId().equals(userId) && split.getAmountOwed()<=0))
                        .collect(Collectors.groupingBy(Split::getUserId, Collectors.summingDouble(Split::getAmountOwed)));
    }

    public Map<String, Double> getUserOwesAmount(String userId) {

        return expenseRepository.getAllExpenses().stream()
                .filter(expense -> !expense.getPaidBy().equals(userId))
                .flatMap(expense -> expense.getSplit()
                        .stream().filter( split -> !split.getUserId().equals(userId) && split.getAmountOwed()>0))
                .collect(Collectors.groupingBy(Split::getUserId, Collectors.summingDouble(Split::getAmountOwed)));

    }





}
