package ldn.cs.decision.pojo.wealth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthWarningInfo extends Wealth {
    private int alarmType; // 告警类型 0->低于预警 1-->高于预警

    private String causeType; // 根因

    private int level; // 紧急程度
}
