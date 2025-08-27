package org.example.factory;

import org.example.dto.ExpenseType;
import org.example.processor.EqualSplitStrategy;
import org.example.processor.ExactSplitStrategy;
import org.example.processor.ISplitStrategy;
import org.example.processor.PercentageSplitStrategy;

public class SplitStrategyFactory {
    public static ISplitStrategy getSplitStrategy(ExpenseType expenseType) {
        switch (expenseType) {
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy();
            case PERCENTAGE:
                return new PercentageSplitStrategy();
            default:
                throw new IllegalArgumentException("Unknown expense type: " + expenseType);
        }
    }
}
