package ldn.cs.optimize.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 子川
 * @create 2024/1/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {
    private Integer score;
    private Double quantity;
    private Double income;
}
