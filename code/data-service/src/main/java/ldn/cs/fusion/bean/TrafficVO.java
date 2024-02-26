package ldn.cs.fusion.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author 子川
 * @create 2024/1/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficVO {
    private String corporation;

    private BigDecimal[] mileages;

    private BigDecimal[] cost;


}
