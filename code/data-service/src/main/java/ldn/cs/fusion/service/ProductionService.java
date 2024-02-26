package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.ProductionDao;
import ldn.cs.fusion.pojo.production.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductionService {
    @Autowired
    private ProductionDao productionDao;

    /**
     * 数据融合 -- 生产链数据新增
     *
     * @param productions 生产链信息
     * @return 新增条数
     */
    public int addProductionInfos(List<Production> productions) {
        long updateTime = System.currentTimeMillis() / 1000;
        productions.forEach(production -> production.setUpdateTime(updateTime));
        return productionDao.addProductionInfos(productions);
    }

    /**
     * 数据融合 -- 生产链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 生产链信息
     */
    public ProductionInfo getProductionInfos(String statement, int types, int limit, int offset) {
        ProductionInfo productionInfo = new ProductionInfo();
        List<Production> productions = productionDao.getProductionInfos(statement, types, limit, offset);
        int total = productionDao.getTotalProduction(statement, types);

        productionInfo.setProductions(productions);
        productionInfo.setTotal(total);
        return productionInfo;
    }

    /**
     * 数据感知 -- 企业整体产量趋势数据新增
     *
     * @param trends 企业整体产量信息
     * @return 新增条数
     */
    public int addTrendInfos(List<Trend> trends) {
        long updateTime = System.currentTimeMillis() / 1000;
        trends.forEach(trend -> trend.setUpdateTime(updateTime));
        return productionDao.addTrendInfos(trends);
    }

    /**
     * 数据感知 -- 企业整体产量趋势可视化查询功能
     *
     * @param time 查询条件年份
     * @return key-企业，value-对象列表
     */
    public Map<String, Integer[]> getTrendInfos(int time) {
        Map<String,Integer[]> res = new HashMap<>();
        Map<String, List<Trend>> collect = productionDao.getTrendInfos(time).stream().collect(Collectors.groupingBy(Trend::getCorporation));
        collect.forEach((k,v)->{
            Integer[] integers = new Integer[12];
               for (int i = 0; i < 12; i++) {
                    integers[i] = 0;
                }
                v.forEach(trend -> {
                    integers[trend.getMonths()-1] = trend.getQuantity().intValue() + integers[trend.getMonths()-1];
                });
                res.put(k,integers);
            });
        return res;
    }

    /**
     * 数据感知 -- 企业产地分布数据新增
     *
     * @param births 企业产地分布信息
     * @return 新增条数
     */
    public int addBirthInfos(List<Birth> births) {
        long updateTime = System.currentTimeMillis() / 1000;
        births.forEach(birth -> birth.setUpdateTime(updateTime));
        return productionDao.addBirthInfos(births);
    }

    /**
     * 数据感知 -- 企业员产地分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Birth>> getBirthInfos(long time, int granularity) {
        return productionDao.getBirthInfos(time, granularity).stream().collect(Collectors.groupingBy(Birth::getCorporation));
    }

    /**
     * 数据感知 -- 企业员产量分布数据新增
     *
     * @param yields 企业产量分布信息
     * @return 新增条数
     */
    public int addYieldInfos(List<Yield> yields) {
        long updateTime = System.currentTimeMillis() / 1000;
        yields.forEach(yield -> yield.setUpdateTime(updateTime));
        return productionDao.addYieldInfos(yields);
    }

    /**
     * 数据感知 -- 企业产量分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Yield>> getYieldInfos(long time, int granularity) {
          return productionDao.getYieldInfos(time, granularity).stream().collect(Collectors.groupingBy(Yield::getCorporation));
//        Map<String, List<Yield>> collect = productionDao.getYieldInfos(time, granularity).stream().collect(Collectors.groupingBy(Yield::getCorporation));
//        ArrayList<TrendVO> trendVOS = new ArrayList<>();
//        for(Map.Entry<String, List<Yield>> entry : collect.entrySet()){
//            TrendVO trendVO = new TrendVO(entry.getKey(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//            entry.getValue().forEach(yield->{
//                trendVO.getCategories().add(yield.getCategories());
//                trendVO.getTypes().add(yield.getTypes());
//                trendVO.getQuantity().add(yield.getQuantity());
//            });
//            trendVOS.add(trendVO);
//        }
//        return trendVOS;
    }
}
