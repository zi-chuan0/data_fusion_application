package ldn.cs.optimize.controller;

import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.fusion.pojo.sale.Sale;
import ldn.cs.optimize.bean.*;
import ldn.cs.optimize.service.CostPriorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 子川
 * @create 2024/1/12
 */
@RestController
@RequestMapping("optimize")
public class CostPriorityController {
    @Resource
    private CostPriorityService costPriorityService;

    @GetMapping("costPriority/getService")
    public ResponseEntity<ServiceInfo> getService(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getServiceInfo(corporationName));
    }

    @GetMapping("costPriority/getQuality")
    public ResponseEntity<QualityInfo> getQuality(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getQualityInfo(corporationName));
    }
    @GetMapping("costPriority/getCorporations")
    public ResponseEntity<List<String>> getCorporations() {
        return ResponseEntity.ok(costPriorityService.getCorporationNames());
    }

    @GetMapping("costPriority/getSaleInfo")
    public ResponseEntity<List<SaleInfo>> getSaleInfo(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getSaleInfo(corporationName));
    }
    @GetMapping("costPriority/getBirthInfo")
    public ResponseEntity<List<BirthInfo>> getProductionInfo(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getBirthInfo(corporationName));
    }

    @GetMapping("costPriority/getConvey")
    public ResponseEntity<List<ConveyInfo>> getConvey(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getConveyInfoByCorporation(corporationName));
    }
    @GetMapping("costPriority/getCost")
    public ResponseEntity<CostInfo> getCost(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getCostInfoByCorporation(corporationName));
    }

    @GetMapping("costPriority/getStaff")
    public ResponseEntity<List<StaffInfo>> getStaff(String corporationName) {
        return ResponseEntity.ok(costPriorityService.getStaffInfoByCorporation(corporationName));
    }

//    @GetMapping("costPriority/getProduction")
//    public ResponseEntity<List<Production>> getProduction(String corporationName) {
//        return ResponseEntity.ok(costPriorityService.getTypeAndCostByCorporation(corporationName));
//    }
}
