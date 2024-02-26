package ldn.cs.fusion.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 子川
 * @create 2024/1/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryVO {
    private String corporation;

    private List<Integer> types;

    private List<BigDecimal> quantity;

    private List<BigDecimal> inventory;
}
