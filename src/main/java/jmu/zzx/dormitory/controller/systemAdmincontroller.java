package jmu.zzx.dormitory.controller;

import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Records;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class systemAdmincontroller {

    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/systemAdmin")
    public String system(Model model) {
        // 省略其他代码
        return "systemAdmin";
    }

    // 查询学生信息
    @GetMapping("/systemAdmin/searchStudents")
    public String searchStudents(@RequestParam Map<String, String> allParams, Model model) {
        List<Student> students = systemAdminService.searchStudents(allParams);
        model.addAttribute("students", students);
        return "systemAdmin";
    }

    @PostMapping("/systemAdmin/addStudent")
    public String addStudent(@RequestParam String number, @RequestParam String name, @RequestParam String username,
                             @RequestParam String password, @RequestParam String gender, @RequestParam int dormitory_id) {
        systemAdminService.addStudent(number,name,username,password,gender,dormitory_id);
        // 返回重定向到学生管理页面或视图
        return "redirect:/systemAdmin";
    }


    @PostMapping("/systemAdmin/updateStudent")
    public String updateStudent(@RequestParam(value = "dormitoryName",required = false) Integer dormitoryName,
                                @RequestParam("studentId") int studentId,
                                @RequestParam(value = "updateName", required = false) String updateName,
                                @RequestParam(value = "user_Name", required = false) String user_Name,
                                @RequestParam(value = "userPassword", required = false) String userPassword) {
        systemAdminService.updateStudentMessage(studentId,dormitoryName,updateName,user_Name,userPassword);
        return "redirect:/systemAdmin";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id) {
        systemAdminService.deleteStudent(id);
        return "redirect:/systemAdmin";
    }


    @GetMapping("/getAvailableDormitories")
    public ResponseEntity<List<Dormitory>> getAvailableDormitories() {
        List<Dormitory> dormitories = systemAdminService.findAvailableDormitories();
        return ResponseEntity.ok(dormitories);
    }

    // 查询信息记录
    @GetMapping("/records")
    public String searchRecords(@RequestParam(value = "mark", required = false) String mark, Model model) {
        List<Records> records = systemAdminService.searchRecordsByMark(mark);
        model.addAttribute("records", records);
        return "systemAdmin";
    }

    // 删除信息记录
    @PostMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable int id) {
        systemAdminService.deleteRecord(id);
        return "redirect:/systemAdmin";
    }


}
