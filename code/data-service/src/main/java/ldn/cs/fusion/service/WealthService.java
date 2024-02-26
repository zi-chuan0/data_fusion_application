package ldn.cs.fusion.service;

import ldn.cs.decision.utils.TimeStampUtils;
import ldn.cs.fusion.dao.WealthDao;
import ldn.cs.fusion.pojo.wealth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WealthService {
    @Autowired
    private WealthDao wealth;

    /**
     * 数据融合 -- 财务链数据新增
     *
     * @param wealths 财务链信息
     * @return 新增条数
     */
    public int addWealthInfos(List<Wealth> wealths) {
        long updateTime = System.currentTimeMillis() / 1000;
        wealths.forEach(wealth -> wealth.setUpdateTime(updateTime));
        return wealth.addWealthInfos(wealths);
    }

    /**
     * 数据融合 -- 财务链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 资财务链信息
     */
    public WealthInfo getWealthInfos(String statement, int types, int limit, int offset) {
        WealthInfo wealthInfo = new WealthInfo();
        List<Wealth> wealths = wealth.getWealthInfos(statement, types, limit, offset);
        int total = wealth.getTotalWealth(statement, types);

        wealthInfo.setWealths(wealths);
        wealthInfo.setTotal(total);
        return wealthInfo;
    }

    /**
     * 数据感知 -- 企业内部资产分布数据新增
     *
     * @param assets 企业内部资产分布信息
     * @return 新增条数
     */
    public int addAssetInfos(List<Asset> assets) {
        long updateTime = System.currentTimeMillis() / 1000;
        assets.forEach(asset -> asset.setUpdateTime(updateTime));
        return wealth.addAssetInfos(assets);
    }

    /**
     * 数据感知 -- 企业内部资产分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Asset>> getAssetInfos(long time, int granularity) {
        List<Asset> assetInfos = wealth.getAssetInfos(time, granularity);
        for (int i = 0; i < assetInfos.size();i++ ) {
            Asset asset1 = assetInfos.get(i);
            for (int j = i + 1; j < assetInfos.size(); ) {
                Asset asset2 = assetInfos.get(j);
                boolean flag;
                if (granularity == 1) flag = true;
                else if (granularity == 2)
                    flag = TimeStampUtils.isSameQuarter(asset1.getUpdateTime(), asset2.getUpdateTime());
                else flag = TimeStampUtils.isSameMonth(asset1.getUpdateTime(), asset2.getUpdateTime());
                if (asset1.getCorporation().equals(asset2.getCorporation()) && flag) {
                    asset1.setFinance(asset1.getFinance().add(asset2.getFinance()));
                    asset1.setCashAssets(asset1.getCashAssets().add(asset2.getCashAssets()));
                    asset1.setFixedAssets(asset1.getFixedAssets().add(asset2.getFixedAssets()));
                    asset1.setProfit(asset1.getProfit().add(asset2.getProfit()));
                    assetInfos.remove(j);
                }else j++;
            }
        }
        return assetInfos.stream().collect(Collectors.groupingBy(Asset::getCorporation));
    }

    /**
     * 数据感知 -- 企业财务分布数据新增
     *
     * @param finances 企业财务分布信息
     * @return 新增条数
     */
    public int addFinanceInfos(List<Finance> finances) {
        long updateTime = System.currentTimeMillis() / 1000;
        finances.forEach(finance -> finance.setUpdateTime(updateTime));
        return wealth.addFinanceInfos(finances);
    }

    /**
     * 数据感知 -- 企业财务分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Finance>> getFinanceInfos(long time, int granularity) {
        List<Finance> financeInfos = wealth.getFinanceInfos(time, granularity);
        return financeInfos.stream().collect(Collectors.groupingBy(Finance::getCorporation));
    }

}
