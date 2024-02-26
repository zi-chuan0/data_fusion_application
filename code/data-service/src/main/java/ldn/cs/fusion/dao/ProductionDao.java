package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.production.Birth;
import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.production.Yield;
import ldn.cs.optimize.bean.BirthInfo;
import ldn.cs.optimize.bean.SaleInfo;
import ldn.cs.optimize.pojo.ProductionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProductionDao {
    @Select("SELECT sum(quantity) quantity,country FROM tbl_production_birth_info WHERE corporation = #{corporationName} GROUP BY country")
    List<BirthInfo> getBirthInfo(String corporationName);

    @Select("SELECT sum(quantity) quantity,country FROM tbl_sale_export_info WHERE corporation = #{corporationName} GROUP BY country")
    List<SaleInfo> getSaleInfo(String corporationName);
    @Select("SELECT types, cost FROM tbl_production_info WHERE corporation = #{corporationName}")
    List<Production> selectTypeAndCostByCorporation(String corporationName);

    int addBirthInfos(@Param("births") List<Birth> births);

    int addYieldInfos(@Param("yields") List<Yield> yields);

    int addTrendInfos(@Param("trends") List<Trend> trends);

    int addProductionInfos(@Param("productions") List<Production> productions);

    List<Production> getProductionInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalProduction(@Param("statement") String statement, @Param("types") int types);

    List<Trend> getTrendInfos(@Param("time")int time);

    List<Birth> getBirthInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Yield> getYieldInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月
}
