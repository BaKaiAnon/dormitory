package jmu.zzx.dormitory.service;

import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.pojo.Student;

import java.util.List;
import java.util.Map;

public interface SystemAdminService {

    public void deleteRecord(int id);

    public List<Records> searchRecordsByMark(String mark);


    public List<Student> searchStudents(Map<String, String> allParams);

    public void updateStudent(Student student);

    public List<Dormitory> findAvailableDormitories();

    public void updateStudentMessage(int studentId,Integer dormitoryName,String updateName,String user_Name,String userPassword);

    public void deleteStudent(int id);

    public void addStudent(String number, String name, String username,
                           String password, String gender, int dormitory_id);
}
