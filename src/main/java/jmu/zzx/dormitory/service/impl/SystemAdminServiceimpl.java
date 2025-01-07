package jmu.zzx.dormitory.service.impl;

import jmu.zzx.dormitory.mapper.SystemMapper;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class SystemAdminServiceimpl implements SystemAdminService {

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public void deleteRecord(int id) {
        systemMapper.deleteRecord(id);
    }

    // 根据类型查询信息记录
    @Override
    public List<Records> searchRecordsByMark(String mark) {
        return systemMapper.searchRecordsByMark(mark);
    }


    // 根据条件查询学生记录
    @Override
    public List<Student> searchStudents(Map<String, String> allParams) {
        return systemMapper.searchStudents(allParams);
    }

    @Override
    public void addStudent(String number, String name, String username,
                    String password, String gender, int dormitory_id){

//        System.out.println(dormitory_id);

        systemMapper.insertStudent(number, name, username,
                password, gender, dormitory_id);
        systemMapper.updateDormitoryAvailable(dormitory_id,-1);
    }


    @Override
    public void updateStudentMessage(int studentId,Integer dormitoryName,String updateName, String user_Name,String userPassword){
        int oldDormitoryId = systemMapper.searchDormitoryIdFromStudent(studentId);
        systemMapper.updateStudentMessage(studentId,dormitoryName,updateName,user_Name,userPassword);
        int newDormitoryId = systemMapper.searchDormitoryIdFromStudent(studentId);
        systemMapper.updateDormitoryAvailable(oldDormitoryId, 1);
        systemMapper.updateDormitoryAvailable(newDormitoryId, -1);

    }


    @Override
    @Transactional
    public void updateStudent(Student student) {
        // 先获取原宿舍ID和新宿舍ID
        Integer oldDormitoryId = student.getOldDormitory_Id();
        Integer newDormitoryId = student.getDormitory_Id();

        // 更新学生信息
        systemMapper.updateStudent(student);

        if (!(oldDormitoryId.equals(newDormitoryId))) {
            // 更新原宿舍床位数量
            if (oldDormitoryId != null) {
                systemMapper.updateDormitoryAvailable(oldDormitoryId, -1);
            }

            // 更新新宿舍床位数量
            if (newDormitoryId != null) {
                systemMapper.updateDormitoryAvailable(newDormitoryId, 1);
            }
        }
    }


    public void deleteStudent(int id){
        systemMapper.deleteStudent(id);
    }

    @Override
    public List<Dormitory> findAvailableDormitories() {
        return systemMapper.findAvailableDormitories();
    }
}