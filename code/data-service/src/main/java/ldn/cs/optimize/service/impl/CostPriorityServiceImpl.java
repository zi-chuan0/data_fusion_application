package ldn.cs.optimize.service.impl;

import ldn.cs.fusion.dao.ConveyDao;
import ldn.cs.fusion.dao.ProductionDao;
import ldn.cs.fusion.dao.StaffDao;
import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.optimize.bean.*;
import ldn.cs.optimize.dao.OptimizedConveyDao;
import ldn.cs.optimize.service.CostPriorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 子川
 * @create 2024/1/12
 */
@Service
public class CostPriorityServiceImpl implements CostPriorityService {
    @Resource
    private OptimizedConveyDao optimizedConveyDao;
    @Resource
    private ProductionDao productionDao;

    @Resource
    private ConveyDao conveyDao;
    @Resource
    private StaffDao staffDao;

    @Override
    public ServiceInfo getServiceInfo(String corporationName) {
        return optimizedConveyDao.getServiceInfo(corporationName);
    }

    @Override
    public QualityInfo getQualityInfo(String corporationName) {
        return optimizedConveyDao.getQualityInfo(corporationName);
    }

    @Override
    public List<String> getCorporationNames() {
        return conveyDao.getCorporationNames().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<SaleInfo> getSaleInfo(String corporationName) {
        return productionDao.getSaleInfo(corporationName);
    }

    @Override
    public List<BirthInfo> getBirthInfo(String corporationName) {
        return productionDao.getBirthInfo(corporationName);
    }

    @Override
    public List<ConveyInfo> getConveyInfoByCorporation(String corporationName) {
        return conveyDao.getConveyInfo(corporationName);
    }

    @Override
    public CostInfo getCostInfoByCorporation(String corporationName) {
        return conveyDao.getCostInfo(corporationName);
    }

    @Override
    public List<StaffInfo> getStaffInfoByCorporation(String corporationName) {
        return staffDao.getStaffInfoByCorporation(corporationName);
    }

    @Override
    public List<Production> getTypeAndCostByCorporation(String corporationName) {
        return productionDao.selectTypeAndCostByCorporation(corporationName);
    }
}
