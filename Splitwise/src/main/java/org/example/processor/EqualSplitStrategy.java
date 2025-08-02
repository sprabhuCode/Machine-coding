package org.example.processor;

import org.example.dto.expense.Split;

import java.util.List;

public class EqualSplitStrategy implements ISplitStrategy {

    @Override
    public boolean validateSplit(List<Split> splitData, double totalAmount) {
        return true;
    }

    @Override
    public List<Split> computeSplitData(List<Split> splitData, double totalAmount) {
        double amount = totalAmount/ splitData.size();
        for(Split split : splitData) {
            split.setAmountOwed(amount);
        }
        return splitData;
    }
}
