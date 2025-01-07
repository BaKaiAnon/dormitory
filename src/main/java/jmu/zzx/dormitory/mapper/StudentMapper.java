package jmu.zzx.dormitory.mapper;

import jmu.zzx.dormitory.dto.StudentDTO;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 声明Mapper接口
@Mapper
public interface StudentMapper {

    // 查询学生信息及宿舍和楼信息，也就是学生混合类的信息
    @Select("SELECT " +
            "s.id as id, " +
            "s.number as number, " +
            "s.name as name, " +
            "s.gender as gender, " +
            "d.id as dormitory_id, " +
            "d.building_id as dormitory_buildingId, " +
            "d.name as dormitory_name, " +
            "d.type as dormitory_type, " +
            "d.available as dormitory_available, " +
            "b.id as building_id, " +
            "b.name as building_name, " +
            "b.introduction as building_introduction, " +
            "b.admin_id as building_adminId, " +
            "c.username as adminName, " + // Added space here
            "c.mail as adminMail, " +
            "c.telephone as adminPhone,  " +
            "c.id as adminId  " +
            "FROM Student s " +
            "JOIN Dormitory d ON s.dormitory_Id = d.id " +
            "JOIN Building b ON d.building_id = b.id " +
            "JOIN dormitory_admin c ON b.admin_id = c.id " +
            "WHERE s.id = #{studentId}")
    StudentDTO findStudentWithDormitoryAndBuildingById(@Param("studentId") Integer studentId);

    // 插入报修、迁出记录
    @Insert("INSERT INTO information_records (title, content, mark,dormitoryId,adminId, building_name,dormitory_name,name) " +
            "VALUES (#{title}, #{content}, #{mark}, #{dormitoryId},#{adminId},#{building_name}, #{dormitory_name}, #{name})")
    void insertRecord(@Param("title") String title, @Param("content") String content,
                      @Param("mark") String mark,@Param("dormitoryId") int dormitoryId,@Param("adminId") int adminId, @Param("building_name") String building_name
            , @Param("dormitory_name") String dormitory_name, @Param("name") String name);

    // 查询可用宿舍
    @Select("SELECT id, name FROM dormitory WHERE available > 0")
    List<Dormitory> findAvailableDormitories();

    // 根据楼号和宿舍号查询记录
    @Select("SELECT * FROM information_records WHERE building_name = #{buildingName} AND dormitory_name = #{dormitoryName} AND audit = '待办' AND mark = #{mark} ORDER by time DESC")
    List<Records> findRecordsByBuildingAndDormitory(@Param("buildingName") String buildingName, @Param("dormitoryName") String dormitoryName,@Param("mark") String mark);

    // 根据楼号和标记查询公告
    @Select("SELECT * FROM information_records WHERE building_name = #{building_name} AND audit = '待办' AND mark = #{mark} ORDER by time DESC")
    List<Records> findNoticeByBuildnameAndMark(@Param("building_name") String building_name, @Param("mark") String mark);

    // 更新报修记录
    @Update("UPDATE information_records SET title = #{title}, content = #{content}, time = NOW() WHERE id = #{id}")
    void updateRecord(@Param("id") int id, @Param("title") String title, @Param("content") String content);

    // 删除报修记录
    @Delete("DELETE FROM information_records WHERE id = #{id}")
    void deleteRecord(@Param("id") int id);

    // 根据姓名查询总记录
    @Select("SELECT * FROM information_records WHERE name = #{name} AND audit != '待办' ORDER by time DESC")
    List<Records> findTotalRecordByname(@Param("name") String name);
}