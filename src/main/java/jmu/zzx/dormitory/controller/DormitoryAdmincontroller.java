package jmu.zzx.dormitory.controller;


import jakarta.servlet.http.HttpSession;
import jmu.zzx.dormitory.dto.DormitoryInfoDTO;
import jmu.zzx.dormitory.dto.DormitoryStudentDTO;
import jmu.zzx.dormitory.pojo.DormitoryAdmin;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.service.DormitoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DormitoryAdmincontroller {

    // 宿舍管理员信息
    DormitoryAdmin dormitoryAdmin;

    // 用于提取宿舍管理员相关的报修记录
    List<Records> repairRecords;

    // 用于提取宿舍管理员相关的迁移记录
    List<Records> moveoutRecords;

    // 用于提取宿舍管理员相关的公告记录
    List<Records> noticeRecords;

    // 自动注入逻辑处理层
    @Autowired
    DormitoryAdminService dormitoryAdminService;



    // 展示宿舍管理员信息页面
    @GetMapping("/dormitoryAdmin")
    // session用于在会话获得登录的管理员信息
    public String DormitoryAdmin(Model model, HttpSession session) {

        dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
        if (dormitoryAdmin != null) {
            // 当宿舍管理员对象不为空时，存入视图
            model.addAttribute("dormitoryAdmin", dormitoryAdmin);
        }else {
            // 否则回到登录页面
            return "login";
        }


        // DormitoryInfoDTO是宿舍楼层混合类，主要是关于宿舍以及其所属楼层的相关信息
        List<DormitoryInfoDTO> dormitoryInfoDTO = (List<DormitoryInfoDTO>) session.getAttribute("dormitoryInfoDTO");
        if (dormitoryInfoDTO != null) {
            model.addAttribute("dormitoryInfoDTO", dormitoryInfoDTO);
        }


        // DormitoryStudentDTO是学生宿舍混合类，主要是关于学生以及所属宿舍的相关信息
        List<DormitoryStudentDTO> dormitoryStudentDTO = (List<DormitoryStudentDTO>) session.getAttribute("dormitoryStudentDTO");
        if (dormitoryStudentDTO != null) {
            model.addAttribute("dormitoryStudentDTO", dormitoryStudentDTO);
        }


        repairRecords = dormitoryAdminService.findRecordsByIdAndMark(dormitoryAdmin.getId(),"repair");
        model.addAttribute("repairRecords", repairRecords);
        moveoutRecords = dormitoryAdminService.findRecordsByIdAndMark(dormitoryAdmin.getId(),"moveout");
        model.addAttribute("moveoutRecords", moveoutRecords);
        noticeRecords = dormitoryAdminService.findRecordsByIdAndMark(dormitoryAdmin.getId(),"notice");
        model.addAttribute("noticeRecords", noticeRecords);



        // 根据管理员的ID，获取他管理的楼名
        List<String> buildingNames = dormitoryAdminService.getBuildingNames(dormitoryAdmin.getId());
        model.addAttribute("buildingNames", buildingNames);
        // 返回登录页面的视图名称
        return "dormitoryAdmin";
    }


    // 查询宿舍及楼层信息操作
    @PostMapping("/dormitoryAdmin/searchdormitory")
    public String search(@RequestParam(value = "roomNumber", required = false) String roomNumber,
                         @RequestParam(value = "availableOnly", required = false) Boolean availableOnly,
                         HttpSession session) {
        if (availableOnly == null) availableOnly = false;
        List<DormitoryInfoDTO> dormitoryInfoDTO = dormitoryAdminService.getDormitoryInfoByAdminId(dormitoryAdmin.getId(),roomNumber, availableOnly);
        session.setAttribute("dormitoryInfoDTO", dormitoryInfoDTO);
        System.out.println(dormitoryInfoDTO);
        return "redirect:/dormitoryAdmin";
    }



    // 查询学生及宿舍信息
    @PostMapping("/dormitoryAdmin/searchStudents")
    public String searchStudents(@RequestParam(value = "roomName", required = false) String roomName,
                                 HttpSession session) {
        List<DormitoryStudentDTO> dormitoryStudentDTO = dormitoryAdminService.getStudentInfoByAdminId(dormitoryAdmin.getId(), roomName);
        session.setAttribute("dormitoryStudentDTO", dormitoryStudentDTO);
        return "redirect:/dormitoryAdmin";
        // 返回同一个视图，但带有查询结果
    }


    // 更新报修记录的审核状态
    @PostMapping("/dormitoryAdmin/updateRecord")
    public String updateRepairRecord(@RequestParam("id") int id, @RequestParam("audit") String audit) {
        dormitoryAdminService.updateRepairRecord(id, audit);
        return "redirect:/dormitoryAdmin";
    }

    // 更新迁移记录的审核状态
    @PostMapping("/dormitoryAdmin/updateMoveoutRecord")
    public String updateMoveoutRecord(@RequestParam("id") int id, @RequestParam("audit") String audit, @RequestParam("targetDormitoryName") String targetDormitoryName, Model model) {
        dormitoryAdminService.updateMoveoutRecord(id, audit, targetDormitoryName);
        return "redirect:/dormitoryAdmin";
    }




    // 提交公告信息
    @PostMapping("/submit-notice")
    public String submitNotice(@RequestParam("title") String title,
                               @RequestParam("content") String content,
                               @RequestParam("buildingName") List<String> buildingNames, // 注意这里是List类型
                               Model model) {
        for (String buildingName : buildingNames) {
            dormitoryAdminService.saveRecord(title, content, "notice", 0, dormitoryAdmin.getId(), buildingName, null, dormitoryAdmin.getName());
        }
        model.addAttribute("message", "公告提交成功");
        return "redirect:/dormitoryAdmin";
    }



    // 更新公告信息
    @PostMapping("/update-notice/{id}")
    public String updateRecord(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("content") String content, Model model) {
        dormitoryAdminService.updateNoticeRecord(id, title, content);
        model.addAttribute("message", "公告记录更新成功");
        return "redirect:/dormitoryAdmin";
    }


    // 删除公告信息
    @PostMapping("/delete-notice/{id}")
    public String deleteRecord(@PathVariable("id") int id, Model model) {
        dormitoryAdminService.deleteRecord(id);
        model.addAttribute("message", "公告记录删除成功");
        return "redirect:/dormitoryAdmin";
    }

}
