package ldn.cs.fusion.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 子川
 * @create 2024/1/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendVO {
    private String corporation;

    private List<Integer> categories;

    private List<Integer> types;

    private List<BigDecimal> quantity;


}
