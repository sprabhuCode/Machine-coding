package org.example.processor;

import org.example.dto.expense.Split;

import java.util.List;

public class ExactSplitStrategy implements ISplitStrategy {

    @Override
    public boolean validateSplit(List<Split> splitData, double totalAmount) {
       double sum = splitData.stream().mapToDouble(Split::getExactSplit).sum();
       return totalAmount-sum <0.01;
    }

    @Override
    public List<Split> computeSplitData(List<Split> splitData, double totalAmount) {
        for(Split split : splitData) {
            split.setAmountOwed(split.getExactSplit());
        }
        return splitData;
    }
}
