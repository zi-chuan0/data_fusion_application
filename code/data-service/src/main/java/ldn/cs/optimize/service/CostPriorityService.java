package ldn.cs.optimize.service;

import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.fusion.pojo.sale.Sale;
import ldn.cs.optimize.bean.*;
import ldn.cs.optimize.pojo.ProductionInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 子川
 * @create 2024/1/12
 */
public interface CostPriorityService {
    ServiceInfo getServiceInfo(String corporationName);
    QualityInfo getQualityInfo(String corporationName);
    List<String> getCorporationNames();
    List<SaleInfo> getSaleInfo(String corporationName);
    List<BirthInfo> getBirthInfo(String corporationName);
    List<ConveyInfo> getConveyInfoByCorporation(String corporationName);
    CostInfo getCostInfoByCorporation(String corporationName);
    List<StaffInfo> getStaffInfoByCorporation(String corporationName);
    List<Production> getTypeAndCostByCorporation(String corporationName);
}
