package ldn.cs.optimize.dao;

import ldn.cs.optimize.bean.QualityInfo;
import ldn.cs.optimize.bean.ServiceInfo;
import ldn.cs.optimize.pojo.OptimizedConvey;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OptimizedConveyDao {

    @Select("select SUM(score) score,SUM(income) income,SUM(quantity) quantity FROM tbl_sale_info WHERE corporation = #{corporationName}")
    ServiceInfo getServiceInfo(String corporationName);

    @Select("select sum(w.revenue) revenue,SUM(w.profit) profit,SUM(p.quality) quality from tbl_wealth_info w JOIN (SELECT corporation,quality FROM tbl_production_info) p WHERE w.corporation = p.corporation AND w.corporation = #{corporationName} ")
    QualityInfo getQualityInfo(String corporationName);

    @Insert("INSERT INTO tbl_optimized_convey_info (corporation, types, categories, cost, transportVolume, updateTime) VALUES (#{corporation}, #{types}, #{categories}, #{cost}, #{transportVolume}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OptimizedConvey record);

    @Select("SELECT * FROM tbl_optimized_convey_info")
    List<OptimizedConvey> selectAll();

    // 刷新提取表格
    @Update("REPLACE INTO tbl_optimized_convey_info (corporation, types, categories, cost, transportVolume, updateTime) " +
            "SELECT corporation, types, categories, SUM(cost) as cost, SUM(quantity) as transportVolume, MAX(updateTime) " +
            "as updateTime FROM tbl_convey_info GROUP BY corporation, types, categories")
    void refreshConveyTable();

    //算法更新
    @Update("UPDATE tbl_optimized_convey_info SET categories = #{categories}, cost = cost * #{costFactor} * 0.5 WHERE corporation = #{corporationName}")
    void updateConveyMethodAndCost(byte categories, BigDecimal costFactor, String corporationName);
}
