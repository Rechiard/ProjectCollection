package com.shida.labchecksys.mapper;

import com.shida.labchecksys.pojo.DayCheck;
import com.shida.labchecksys.pojo.HiddenDanger;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;



@Repository
@Mapper
public interface HiddenDangerMapper {

    @Select("select reformPrincipal from day_check where id = #{id}")
    String getReformPrincipal(long id);

    @Select("select * from day_check where is_danger = #{isDanger}")
    List<DayCheck> showDanger(int isDanger);

    @Update("update day_check set reform_principal = #{reformPrincipal} where id = #{id}")
    int updateDayCheckByLeader(long id, String reformPrincipal, int stage);

    @Update("update day_check set reform_measure = #{reformMeasure},reform_time = #{reformTime} where id = #{id}")
    int updateDayCheckByPrincipal(long id, String reformMeasure, Date reform_time, int stage);

    @Update("update day_check set accepter = #{accepter},acc_results = #{accResults},acc_time = #{accTime} where id = #{id}")
    int updateDayCheckByAccepter(long id, String accepter, String accResults, Date accTime, int stage);

    @Update("update day_check set school_result = #{schoolResult},school_time = #{schoolTime} where id = #{id}")
    int updateDayCheckBySchool(long id, String schoolResult, Date schoolTime, int stage);

    @Insert("insert into lab_hidden_danger_rectification values(null,#{userName},#{checkTime},#{labName},#{existingDanger},#{rectificationMeasures},#{rectificationPerson},#{rectificationFinishTime},#{testPerson},#{testTime},#{isFinish})")
    void insert(HiddenDanger hiddenDanger);

    @Select("select * from lab_hidden_danger_rectification")
    List<HiddenDanger> selectAll();

    @Update("update lab_hidden_danger_rectification set " +
            "user_name = #{userName}," +
            "check_time = #{checkTime}," +
            "lab_name = #{labName}," +
            "existing_danger = #{existingDanger}," +
            "rectification_measures = #{rectificationMeasures}" +
            "rectification_person = #{rectificationPerson}" +
            "rectification_finish_time = #{rectificationFinishTime}" +
            "test_person = #{testPerson}" +
            "test_time = #{testTime}" +
            " where id = #{id}")
    void update(HiddenDanger hiddenDanger);

    @Delete("delete from lab_hidden_danger_rectification where id = #{id}")
    void delete(int id);

    @Select("select * from day_check where is_danger=1")
    List<DayCheck> selectIsDanger();

    @Delete("delete from lab_hidden_danger_rectification")
    void deleteAll();

    @Select("select * from lab_hidden_danger_rectification where test_person = #{testPerson}")
    List<HiddenDanger> selectAllByTestPerson(String testPerson);

    @Select("select * from lab_hidden_danger_rectification where department = #{department}")
    List<HiddenDanger> selectAllByDepartment(String testPerson);

    @Update("update lab_hidden_danger_rectification set is_finish = #{isFinish} where id = #{id}")
    void examineIsFinish(int isFinish,int id);

    @Select("select * from day_check where department = #{department} and stage = #{stage}")
    List<DayCheck> showAllByDepartment(String department,int stage);

    @Select("select * from day_check where check_object = #{labName} and stage = #{stage}")
    List<DayCheck> showAllByLab(String labName,int stage);

    @Select("select * from day_check stage = #{stage}")
    List<DayCheck> showAll(int stage);

}
