package jmu.zzx.dormitory.service;

import jmu.zzx.dormitory.pojo.DormitoryAdmin;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.pojo.SystemAdmin;

public interface LoginService {

    public Student authenticateAsStudent(String username, String password) ;


    public DormitoryAdmin authenticateAsDormitoryAdmin(String username, String password);


    public SystemAdmin authenticateAsSystemAdmin(String username, String password) ;
}