package jmu.zzx.dormitory.service;

import jmu.zzx.dormitory.dto.StudentDTO;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import java.util.List;

// 声明学生服务接口
public interface StudentService {

    // 验证学生信息
    public StudentDTO authenticateStudent(Integer studentId);
    // 保存记录
    public void saveRecord(String title, String content, String mark, int dormitoryId, int adminId, String building_name, String dormitory_name, String name);
    // 根据楼号和宿舍号查询报修、迁出记录
    public List<Records> findRecordsByBuildingAndDormitory(String buildingName, String dormitoryName, String mark);
    // 根据楼号和标记查询公告
    public List<Records> findNoticeByBuildnameAndMark(String build_name, String mark);
    // 根据姓名查询总记录，接收受理信息
    public List<Records> findTotalRecordByname(String name);
    // 查询可用宿舍
    public List<Dormitory> findAvailableDormitories();
    // 更新报修、迁出记录
    public void updateRecord(int id, String title, String content);
    // 删除报修、迁出记录
    public void deleteRecord(int id);
}
