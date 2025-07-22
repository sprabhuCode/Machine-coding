package org.example.dto.expense;

import lombok.Data;

@Data
public class Split {
    private String userId;
    private double percentageSplit;
    private double exactSplit;
    private double amountOwed;


}
