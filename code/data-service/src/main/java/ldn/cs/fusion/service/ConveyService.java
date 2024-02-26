package ldn.cs.fusion.service;

import ldn.cs.decision.utils.TimeStampUtils;
import ldn.cs.fusion.bean.InventoryVO;
import ldn.cs.fusion.bean.TrafficVO;
import ldn.cs.fusion.dao.ConveyDao;
import ldn.cs.fusion.pojo.convey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConveyService {
    @Autowired
    private ConveyDao conveyDao;

    /**
     * 数据融合 -- 物流链数据新增
     *
     * @param conveys 物流链信息
     * @return 新增条数
     */
    public int addConveyInfos(List<Convey> conveys) {
        long updateTime = System.currentTimeMillis() / 1000;
        conveys.forEach(convey -> convey.setUpdateTime(updateTime));
        return conveyDao.addConveyInfos(conveys);
    }

    /**
     * 数据融合 -- 物流链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 物流链信息
     */
    public ConveyInfo getConveyInfos(String statement, int types, int limit, int offset) {
        ConveyInfo conveyInfo = new ConveyInfo();
        List<Convey> conveys = conveyDao.getConveyInfos(statement, types, limit, offset);
        int total = conveyDao.getTotalConvey(statement, types);
        conveyInfo.setConveys(conveys);
        conveyInfo.setTotal(total);
        return conveyInfo;
    }

    /**
     * 数据感知 -- 企业运输类型分布数据新增
     *
     * @param traffics 企业运输类型分布信息
     * @return 新增条数
     */
    public int addTrafficInfos(List<Traffic> traffics) {
        long updateTime = System.currentTimeMillis() / 1000;
        traffics.forEach(traffic -> traffic.setUpdateTime(updateTime));
        return conveyDao.addTrafficInfos(traffics);
    }

    /**
     * 数据感知 -- 企业运输类型分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public List<TrafficVO> getTrafficInfos(long time, int granularity) {
        Map<String, List<Traffic>> collect = conveyDao.getTrafficInfos(time,granularity).stream().collect(Collectors.groupingBy(Traffic::getCorporation));
        ArrayList<TrafficVO> trafficVOS = new ArrayList<>();
        for(Map.Entry<String, List<Traffic>> entry : collect.entrySet()){
            TrafficVO trafficVO = new TrafficVO(entry.getKey(), new BigDecimal[5], new BigDecimal[5]);
                entry.getValue().stream().forEach(traffic->{
                    trafficVO.getCost()[traffic.getCategories()-1] = traffic.getCost();
                    trafficVO.getMileages()[traffic.getCategories()-1] = traffic.getMileage();
                });
            trafficVOS.add(trafficVO);
        }
        return trafficVOS;

    }

    /**
     * 数据感知 -- 企业货物库存分布数据新增
     *
     * @param inventories 企业货物库存分布信息
     * @return 新增条数
     */
    public int addInventoryInfos(List<Inventory> inventories) {
        long updateTime = System.currentTimeMillis() / 1000;
        inventories.forEach(inventory -> inventory.setUpdateTime(updateTime));
        return conveyDao.addInventoryInfos(inventories);
    }

    /**
     * 数据感知 -- 企业货物库存分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public List<InventoryVO> getInventoryInfos(long time, int granularity) {
        Map<String, List<Inventory>> collect = conveyDao.getInventoryInfos(time, granularity).stream().collect(Collectors.groupingBy(Inventory::getCorporation));
        List<InventoryVO> inventoryVOS = new ArrayList<>();
        for (Map.Entry<String, List<Inventory>> entry : collect.entrySet()) {
            InventoryVO inventoryVO = new InventoryVO(entry.getKey(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            entry.getValue().stream().forEach(inventory -> {
                inventoryVO.getTypes().add(inventory.getTypes());
                inventoryVO.getQuantity().add(inventory.getQuantity());
                inventoryVO.getInventory().add(inventory.getInventory());
            });
            inventoryVOS.add(inventoryVO);
        }
        return inventoryVOS;
    }
}
