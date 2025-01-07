package jmu.zzx.dormitory.mapper;

import jmu.zzx.dormitory.pojo.DormitoryAdmin;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.pojo.SystemAdmin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM student WHERE username = #{username} AND password = #{password}")
    Student getStudentByCredentials(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM dormitory_admin WHERE username = #{username} AND password = #{password}")
    DormitoryAdmin getDormitoryAdminByCredentials(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM system_admin WHERE username = #{username} AND password = #{password}")
    SystemAdmin getSystemAdminByCredentials(@Param("username") String username, @Param("password") String password);
}
