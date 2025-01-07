package jmu.zzx.dormitory.controller;

import jakarta.servlet.http.HttpSession;
import jmu.zzx.dormitory.dto.StudentDTO;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.service.StudentService;
import jmu.zzx.dormitory.utils.SimpleMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



// 定义控制器
@Controller
public class StudentController {

    // 成员变量，存储学生信息
    Student student;
    // studentDTO 混合类，整合了学生、宿舍和宿舍管理员等多部分信息
    StudentDTO studentDTO;

    // 自动注入SimpleMail组件
    @Autowired
    private SimpleMail simpleMail;


    // 自动注入StudentService服务
    @Autowired
    private StudentService studentService;

    // 初始化学生DTO信息
    protected void initStudentDTO(HttpSession session, Model model) {
        // 从session中获取学生信息
        student = (Student) session.getAttribute("student");
        // 如果学生信息为空，则添加错误信息并抛出异常
        if (student == null) {
            model.addAttribute("error", "学生信息未找到，请重新登录");
            throw new RuntimeException("学生信息未找到，请重新登录");
        }
        // 通过学生ID验证学生信息
        studentDTO = studentService.authenticateStudent(student.getId());
        // 如果学生DTO信息为空，则添加错误信息并抛出异常
        if (studentDTO == null) {
            model.addAttribute("error", "学生信息验证失败");
            throw new RuntimeException("学生信息验证失败");
        }
        // 将学生DTO信息添加到模型中
        model.addAttribute("studentDTO", studentDTO);
    }

    // 处理GET请求，展示学生信息页面
    @GetMapping("/student")
    public String student(Model model, HttpSession session) {
        // 初始化学生DTO信息
        initStudentDTO(session, model);
        // 查询报修记录
        List<Records> repairRecords = studentService.findRecordsByBuildingAndDormitory(studentDTO.getBuilding_name(), studentDTO.getDormitory_name(),"repair");
        model.addAttribute("repairRecords", repairRecords);
        // 查询迁出记录
        List<Records> moveoutRecords = studentService.findRecordsByBuildingAndDormitory(studentDTO.getBuilding_name(), studentDTO.getDormitory_name(),"moveout");
        model.addAttribute("moveoutRecords", moveoutRecords);
        // 查询公告记录
        List<Records> noticeRecords = studentService.findNoticeByBuildnameAndMark(studentDTO.getBuilding_name(),"notice");
        model.addAttribute("noticeRecords", noticeRecords);
        // 查询总记录
        List<Records> totalRecords = studentService.findTotalRecordByname(studentDTO.getName());
        model.addAttribute("totalRecords", totalRecords);
        // 返回学生信息页面
        return "student";
    }


    // 处理POST请求，提交报修
    @PostMapping("/submit-repair")
    public String submitRepair(@RequestParam("title") String title,
                               @RequestParam("content") String content,
                               Model model, HttpSession session) {
        // 从session中获取学生信息
        Student student = (Student) session.getAttribute("student");
        // 如果学生信息为空，则返回学生信息页面
        if (student == null) {
            return "student";
        }
        // 提交报修记录
        studentService.saveRecord(title, content, "repair", studentDTO.getDormitory_id(), studentDTO.getAdminId(), studentDTO.getBuilding_name(), studentDTO.getDormitory_name(), studentDTO.getName());
        // 添加成功信息到模型
        model.addAttribute("message", "报修提交成功");
        // 重定向到学生信息页面
        return "redirect:/student";
    }


    // 处理GET请求，获取可用宿舍列表
    @GetMapping("/available-dormitories")
    public @ResponseBody List<Dormitory> getAvailableDormitories() {
        // 查询并返回可用宿舍列表
        return studentService.findAvailableDormitories();
    }


    // 处理POST请求，提交迁出申请
    @PostMapping("/submit-moveout")
    public String submitMoveout(@RequestParam("targetDormitory") String targetDormitoryname,
                                @RequestParam("content") String content,
                                Model model, HttpSession session) {
        // 从session中获取学生信息
        Student student = (Student) session.getAttribute("student");
        // 如果学生信息为空，则返回学生信息页面
        if (student == null) {
            return "student";
        }
        // 提交迁出申请记录
        studentService.saveRecord(targetDormitoryname, content, "moveout", studentDTO.getDormitory_id(),
                studentDTO.getAdminId(),studentDTO.getBuilding_name(),studentDTO.getDormitory_name(),studentDTO.getName());
        // 添加成功信息到模型
        model.addAttribute("message", "迁出申请提交成功");
        // 重定向到学生信息页面
        return "redirect:/student";
    }



    // 处理POST请求，更新（报修、迁出）记录
    @PostMapping("/update-record/{id}")
    public String updateRecord(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("content") String content, Model model) {
        // 更新记录
        studentService.updateRecord(id, title, content);
        // 添加成功信息到模型
        model.addAttribute("message", "记录更新成功");
        // 重定向到学生信息页面
        return "redirect:/student";
    }

    // 处理POST请求，删除（报修、迁出）记录
    @PostMapping("/delete-record/{id}")
    public String deleteRecord(@PathVariable("id") int id, Model model) {
        // 删除记录
        studentService.deleteRecord(id);
        // 添加成功信息到模型
        model.addAttribute("message", "记录删除成功");
        // 重定向到学生信息页面
        return "redirect:/student";
    }


    // 处理POST请求，发送邮件
    @PostMapping("/send-mail")
    public String sendMail(String subject, String content, Model model) {
        // 发送邮件
        simpleMail.sendSimpleMail(studentDTO.getAdminMail(), subject, content, studentDTO.getName());
        // 添加成功信息到模型
        model.addAttribute("message", "邮件发送成功");
        // 重定向到学生信息页面
        return "redirect:/student";
    }
}