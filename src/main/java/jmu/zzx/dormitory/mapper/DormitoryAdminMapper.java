package jmu.zzx.dormitory.mapper;

import jmu.zzx.dormitory.dto.DormitoryInfoDTO;
import jmu.zzx.dormitory.dto.DormitoryStudentDTO;
import jmu.zzx.dormitory.pojo.Records;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormitoryAdminMapper {


    @Select("<script>"
            + "SELECT "
            + "b.id as buildingId, "
            + "b.name as buildingName, "
            + "b.introduction as buildingIntroduction, "
            + "d.id as dormitoryId, "
            + "d.name as dormitoryName, "
            + "d.type as dormitoryType, "
            + "d.available as dormitoryAvailable, "
            + "d.telephone as dormitoryTelephone "
            + "FROM "
            + "building b "
            + "INNER JOIN dormitory d ON b.id = d.building_id "
            + "WHERE b.admin_id = #{adminId} "
            + "<if test='name != null and name.trim() != \"\" '>"
            + "AND d.name = #{name} "
            + "</if>"
            + "<if test='available != false'>"
            + "AND d.available != 0 "
            + "</if>"
            + "ORDER BY b.name ASC"
            + "</script>")
    List<DormitoryInfoDTO> findDormitoryInfoByAdminId(@Param("adminId") int adminId,
                                                      @Param("name") String name,
                                                      @Param("available") boolean available);




    @Select("<script>"
            + "SELECT "
            + "s.id as id, "
            + "s.number as number, "
            + "s.name as name, "
            + "s.gender as gender, "
            + "d.id as dormitory_id, "
            + "d.building_id as building_id, "
            + "d.name as dormitory_name "
            + "FROM "
            + "student s "
            + "INNER JOIN dormitory d ON s.dormitory_id = d.id "
            + "WHERE d.building_id IN (SELECT id FROM building WHERE admin_id = #{adminId}) "
            + "<if test='roomName != null and roomName.trim() != \"\" '>"
            + "AND d.name = #{roomName} "
            + "</if>"
            + "ORDER BY d.building_id ASC, d.id ASC"
            + "</script>")
    List<DormitoryStudentDTO> findStudentInfoByAdminId(@Param("adminId") int adminId, @Param("roomName") String roomName);


    // 获取楼名，根据宿舍管理员的ID号
    @Select("SELECT name FROM building WHERE admin_id = #{id}")
    List<String> getBuildingNames(@Param("id") int id);


    // 查询特定楼号和宿舍号的报修记录
    @Select("SELECT * FROM information_records WHERE adminId = #{adminId}  AND audit = '待办' AND mark = #{mark}")
    List<Records> findRecordsByIdAndMark(@Param("adminId") int adminId , @Param("mark") String mark);

    @Select("SELECT building_id FROM dormitory WHERE name = #{dormitoryName}")
    int findBuildingIdByDormitoryname(@Param("dormitoryName") String dormitoryName);

    @Select("SELECT admin_id FROM building WHERE id = #{buildingId}")
    int findAdminIdByBuildingId(@Param("buildingId") int buildingId);

    // 对报修记录的审批状态进行更新
    @Update("UPDATE information_records SET time = NOW(),audit = #{audit} WHERE id = #{id}")
    void updateRepairRecord(@Param("id") int id, @Param("audit") String audit);


    // 对迁移记录的审批状态进行更新
    @Update("UPDATE information_records SET audit = #{audit}, adminId = #{adminId} WHERE id = #{id}")
    void updateMoveoutRecord(@Param("id") int id, @Param("audit") String audit, @Param("adminId") int adminId);


    // 获取当前进行更新、删除操作的信息记录
    @Select("SELECT * FROM information_records WHERE id = #{id}")
    Records findRecordById(@Param("id") int id);


    // 用于公告信息的插入
    @Insert("INSERT INTO information_records (title, content, mark,dormitoryId,adminId, building_name,dormitory_name,name) " +
            "VALUES (#{title}, #{content}, #{mark}, #{dormitoryId},#{adminId},#{building_name}, #{dormitory_name}, #{name})")
    void insertRecord(@Param("title") String title, @Param("content") String content,
                      @Param("mark") String mark,@Param("dormitoryId") int dormitoryId,@Param("adminId") int adminId, @Param("building_name") String building_name
            , @Param("dormitory_name") String dormitory_name, @Param("name") String name);


    @Update("UPDATE information_records SET time = NOW(),title = #{title} , content = #{content} WHERE id = #{id}")
    void updateNoticeRecord(@Param("id") int id, @Param("title") String title, @Param("content") String content);


    // 用于公告信息的删除
    @Delete("DELETE FROM information_records WHERE id = #{id}")
    void deleteRecord(@Param("id") int id);





}