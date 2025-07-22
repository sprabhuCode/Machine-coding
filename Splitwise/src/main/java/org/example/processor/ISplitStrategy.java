package org.example.processor;

import org.example.dto.expense.Split;

import java.util.List;

public interface ISplitStrategy {
    boolean validateSplit(List<Split> splitData, double totalAmount);

    List<Split> computeSplitData(List<Split> splitData, double totalAmount);

}
