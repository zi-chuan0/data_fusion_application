package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.staff.Person;
import ldn.cs.fusion.pojo.staff.Position;
import ldn.cs.fusion.pojo.staff.Skill;
import ldn.cs.fusion.pojo.staff.Staff;
import ldn.cs.optimize.bean.StaffInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StaffDao {
    @Select("SELECT corporation,categories,amount FROM tbl_staff_info WHERE corporation=#{corporationName}")
    List<StaffInfo> getStaffInfoByCorporation(String corporationName);

    int addStaffInfos(@Param("staffs") List<Staff> staffs);

    int addPersonInfos(@Param("persons") List<Person> persons);

    int addPositionInfos(@Param("positions") List<Position> positions);

    int addSkillInfos(@Param("skills") List<Skill> skills);

    List<Staff> getStaffInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalStaff(@Param("statement") String statement, @Param("types") int types);

    //granularity: 1、年 2、季度 3、月
    List<Person> getPersonInfos(@Param("time") long time, @Param("granularity") int granularity);

    List<Position> getPositionInfos(@Param("time") long time, @Param("granularity") int granularity);

    List<Skill> getSkillInfos(@Param("time") long time, @Param("granularity") int granularity);
}
