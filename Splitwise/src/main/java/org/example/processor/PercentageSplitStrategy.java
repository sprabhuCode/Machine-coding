package org.example.processor;

import org.example.dto.expense.Split;

import java.util.List;

public class PercentageSplitStrategy implements ISplitStrategy {

    @Override
    public boolean validateSplit(List<Split> splitData, double totalAmount) {
       double totalPercent = splitData.stream().mapToDouble(Split::getPercentageSplit).sum();

       return Math.abs(totalAmount - totalPercent) < 0.01;
    }

    @Override
    public List<Split> computeSplitData(List<Split> splitData, double totalAmount) {
        double totalAssigned = 0.0;
        int n = splitData.size();

        // Compute raw splits with 2 decimal rounding
        for (int i = 0; i < n; i++) {
            Split split = splitData.get(i);
            double computedAmount = roundToTwoDecimals((split.getPercentageSplit() / 100.0) * totalAmount);

            // Assign and track total
            split.setAmountOwed(computedAmount);
            totalAssigned += computedAmount;
        }

        // Fix rounding discrepancy on first user
        double roundingError = roundToTwoDecimals(totalAmount - totalAssigned);
        if (Math.abs(roundingError) > 0.0) {
            Split firstSplit = splitData.get(0);
            firstSplit.setAmountOwed(roundToTwoDecimals(firstSplit.getAmountOwed() + roundingError));
        }

        return splitData;
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
