package jmu.zzx.dormitory.service.impl;

import jmu.zzx.dormitory.dto.DormitoryInfoDTO;
import jmu.zzx.dormitory.dto.DormitoryStudentDTO;
import jmu.zzx.dormitory.mapper.DormitoryAdminMapper;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.service.DormitoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DormitoryAdminServiceimpl implements DormitoryAdminService {
    @Autowired
    DormitoryAdminMapper dormitoryAdminMapper;


    @Override
    public List<DormitoryInfoDTO> getDormitoryInfoByAdminId(int adminId,String name,boolean available) {

        return dormitoryAdminMapper.findDormitoryInfoByAdminId(adminId,name,available);
    }


    @Override
    public List<DormitoryStudentDTO> getStudentInfoByAdminId(int adminId,String roomName){
        return dormitoryAdminMapper.findStudentInfoByAdminId(adminId,roomName);
    }


    @Override
    public List<Records> findRecordsByIdAndMark(int adminId,String mark){
        return dormitoryAdminMapper.findRecordsByIdAndMark(adminId,mark);
    };



    @Override
    public void updateRepairRecord(int id, String audit){
        dormitoryAdminMapper.updateRepairRecord(id, audit);
    };



    @Override
    public void updateMoveoutRecord(int id, String audit, String targetDormitoryName) {
        Records record = dormitoryAdminMapper.findRecordById(id);
        int currentAdminId = record.getAdminId();
        int targetBuildingId = dormitoryAdminMapper.findBuildingIdByDormitoryname(targetDormitoryName);
        int targetAdminId = dormitoryAdminMapper.findAdminIdByBuildingId(targetBuildingId);

        if (audit.equals("否决")){
            dormitoryAdminMapper.updateMoveoutRecord(id, audit, currentAdminId);
        }
        else if (currentAdminId != targetAdminId) {
            // 如果管理员ID不同，更新adminId，但不改变审核状态
            record.setAdminId(targetAdminId);
            dormitoryAdminMapper.updateMoveoutRecord(id, "待办", targetAdminId);
        } else {
            // 如果管理员ID相同，更新审核状态和adminId
            dormitoryAdminMapper.updateMoveoutRecord(id, audit, currentAdminId);
        }
    }




    // 用于公告记录的保存，报修、迁移记录值能更新
    @Override
    public void saveRecord(String title, String content,
                           String mark, Integer dormitoryId, int adminId,
                           String buildingName, String dormitoryName,
                           String name) {
        dormitoryAdminMapper.insertRecord(title, content, mark, dormitoryId, adminId, buildingName, dormitoryName, name);
    }



    // 更新公告信息
    @Override
    public void updateNoticeRecord(int id,String title, String content) {
        dormitoryAdminMapper.updateNoticeRecord(id, title, content);
    }


    // 实际上，只有公告记录会用到删除操作
    @Override
    public void deleteRecord(int id){
        dormitoryAdminMapper.deleteRecord(id);
    };


    // 根据管理员的ID，获取他管理的楼名
    @Override
    public List<String> getBuildingNames(int id) {
        // 查询数据库，返回楼名列表
        return dormitoryAdminMapper.getBuildingNames(id);
    }

}
