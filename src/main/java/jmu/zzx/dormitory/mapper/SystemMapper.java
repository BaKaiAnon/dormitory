package jmu.zzx.dormitory.mapper;

import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemMapper {

    // 删除信息记录
    @Delete("DELETE FROM information_records WHERE id = #{id}")
    int deleteRecord(@Param("id") int id);

    // 根据类型查询信息记录
    @Select("<script>" +
            "SELECT * FROM information_records" +
            "<where>" +
            "<if test='mark != null and mark != \"\"'>" +
            " WHERE mark = #{mark}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Records> searchRecordsByMark(@Param("mark") String mark);



    // 根据条件查询学生记录
    @Select("<script>" +
            "SELECT * FROM student" +
            "<where>" +
            "<if test='allParams.number != null and allParams.number != \"\"'>" +
            " AND number = #{allParams.number}" +
            "</if>" +
            "<if test='allParams.name != null and allParams.name != \"\"'>" +
            " AND name = #{allParams.name}" +
            "</if>" +
            "<if test='allParams.gender != null and allParams.gender != \"\"'>" +
            " AND gender = #{allParams.gender}" +
            "</if>" +
            "<if test='allParams.dormitoryId != null and allParams.dormitoryId != \"\"'>" +
            " AND dormitory_id = #{allParams.dormitoryId}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Student> searchStudents(@Param("allParams") Map<String, String> allParams);




    @Select("SELECT dormitory_id from student where id = #{id}")
    int searchDormitoryIdFromStudent(@Param("id") int id);


    @Update({
            "<script>",
            "UPDATE student",
            "<set>",
            "<if test='dormitory_id != null and dormitory_id != 0'>",
            "dormitory_id = #{dormitory_id},",
            "</if>",
            "<if test='updateName != null and updateName != \"\"'>",
            "name = #{updateName},",
            "</if>",
            "<if test='user_Name != null and user_Name != \"\"'>",
            "username = #{user_Name},",
            "</if>",
            "<if test='userPassword != null and userPassword != \"\"'>",
            "password = #{userPassword},",
            "</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    void updateStudentMessage(@Param("id") int id, @Param("dormitory_id") int dormitory_id,
                              @Param("updateName") String updateName, @Param("user_Name") String user_Name,
                              @Param("userPassword") String userPassword);


    @Insert("INSERT INTO student(number,name,username,password,gender,dormitory_id) VALUES " +
            "(#{number},#{name},#{username},#{password},#{gender},#{dormitory_id})")
    void insertStudent(@Param("number") String number, @Param("name") String name, @Param("username") String username,
                       @Param("password") String password, @Param("gender") String gender, @Param("dormitory_id") int dormitory_id);


    @Delete("DELETE FROM student where id = #{id}")
    void deleteStudent(@Param("id") int id);



    @Update("UPDATE dormitory SET available = available + #{change} WHERE id = #{dormitoryId}")
    int updateDormitoryAvailable(@Param("dormitoryId") int dormitoryId, @Param("change") int change);



    @Update("UPDATE student SET number=#{number}, name=#{name}, username=#{username}, password=#{password}, gender=#{gender}, dormitory_id=#{dormitory_Id}, state=#{state} WHERE id=#{id}")
    int updateStudent(Student student);


    @Select("SELECT id, name FROM dormitory WHERE available > 0")
    List<Dormitory> findAvailableDormitories();
}
