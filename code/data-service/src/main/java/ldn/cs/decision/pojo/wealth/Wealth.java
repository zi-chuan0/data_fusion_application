package ldn.cs.decision.pojo.wealth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wealth {
    private int id;

    private String corporation;

    private BigDecimal research;

    private BigDecimal device;

    private BigDecimal production;

    private BigDecimal storage;

    private BigDecimal materiel;

    private BigDecimal transportation;

    private BigDecimal salary;

    private BigDecimal revenue;

    private BigDecimal profit;

    private BigDecimal fixedAssets;

    private BigDecimal cashAssets;

    private BigDecimal finance;

    private long eventTime;

    private long updateTime;
}
