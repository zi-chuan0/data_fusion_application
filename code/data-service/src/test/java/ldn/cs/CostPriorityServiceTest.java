package ldn.cs;

import ldn.cs.decision.utils.TimeStampUtils;
import ldn.cs.fusion.dao.ConveyDao;
import ldn.cs.fusion.dao.ProductionDao;
import ldn.cs.fusion.pojo.convey.Traffic;
import ldn.cs.fusion.bean.TrafficVO;
import ldn.cs.fusion.pojo.production.Yield;
import ldn.cs.fusion.service.ConveyService;
import ldn.cs.optimize.service.CostPriorityService;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 子川
 * @create 2024/1/12
 */
public class CostPriorityServiceTest extends BaseTest{
    @Resource
    private CostPriorityService costPriorityService;
    @Resource
    private ConveyDao conveyDao;
    @Resource
    private ConveyService conveyService;
    @Resource
    private ProductionDao productionDao;

    @Test
    public void test01(){
        TimeStampUtils.isSameQuarter(1697328000,1697328000);
    }

    @Test
    public void test1(){
        Map<String, List<Yield>> collect = productionDao.getYieldInfos(1666945270, 1).stream().collect(Collectors.groupingBy(Yield::getCorporation));
        for(Map.Entry<String, List<Yield>> entry : collect.entrySet()){
            System.out.println(entry.getValue().stream().collect(Collectors.groupingBy(Yield::getCategories)));

        }
    }

    @Test
    public void testGetTypeAndCostByCorporation() {
        System.out.println(11);
        System.out.println(costPriorityService.getTypeAndCostByCorporation("小丫家电"));
    }
}
