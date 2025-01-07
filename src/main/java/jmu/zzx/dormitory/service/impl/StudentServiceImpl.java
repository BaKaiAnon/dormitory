package jmu.zzx.dormitory.service.impl;


import jmu.zzx.dormitory.dto.StudentDTO;
import jmu.zzx.dormitory.mapper.StudentMapper;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    // 自动注入StudentMapper
    @Autowired
    StudentMapper studentMapper;

    // 实现获取学生相关信息的方法
    @Override
    public StudentDTO authenticateStudent(Integer studentId) {
        // 通过mapper查询学生信息
        StudentDTO studentDTO = studentMapper.findStudentWithDormitoryAndBuildingById(studentId);
        return studentDTO;
    }

    // 实现保存记录的方法
    @Override
    public void saveRecord(String title, String content, String mark, int dormitoryId, int adminId, String buildingName, String dormitoryName, String name) {
        // 通过mapper插入记录
        studentMapper.insertRecord(title, content, mark, dormitoryId, adminId, buildingName, dormitoryName, name);
    }

    // 实现查询可用宿舍的方法
    @Override
    public List<Dormitory> findAvailableDormitories() {
        // 通过mapper查询可用宿舍
        return studentMapper.findAvailableDormitories();
    }

    // 实现根据楼号和宿舍号查询记录的方法
    @Override
    public List<Records> findRecordsByBuildingAndDormitory(String buildingName, String dormitoryName, String mark) {
        // 通过mapper查询记录
        return studentMapper.findRecordsByBuildingAndDormitory(buildingName, dormitoryName, mark);
    }

    // 实现根据楼号和标记查询公告的方法
    @Override
    public List<Records> findNoticeByBuildnameAndMark(String build_name, String mark) {
        // 通过mapper查询公告
        return studentMapper.findNoticeByBuildnameAndMark(build_name, mark);
    }


    // 实现更新记录的方法
    @Override
    public void updateRecord(int id, String title, String content) {
        // 通过mapper更新记录
        studentMapper.updateRecord(id, title, content);
    }

    // 实现删除记录的方法
    @Override
    public void deleteRecord(int id) {
        // 通过mapper删除记录
        studentMapper.deleteRecord(id);
    }

    // 实现根据姓名查询总记录的方法
    @Override
    public List<Records> findTotalRecordByname(String name) {
        // 通过mapper查询总记录
        return studentMapper.findTotalRecordByname(name);
    }


}
