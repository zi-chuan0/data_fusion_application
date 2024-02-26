package ldn.cs.optimize.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 子川
 * @create 2024/1/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostInfo {
    private BigDecimal research;
    private BigDecimal device;
    private BigDecimal production;
    private BigDecimal storage;
    private BigDecimal materiel;
    private BigDecimal transportation;
    private BigDecimal salary;
}
