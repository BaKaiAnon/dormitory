package jmu.zzx.dormitory.controller;

import jakarta.servlet.http.HttpSession;
import jmu.zzx.dormitory.pojo.DormitoryAdmin;
import jmu.zzx.dormitory.pojo.Student;
import jmu.zzx.dormitory.pojo.SystemAdmin;
import jmu.zzx.dormitory.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 标记这个类为Spring MVC的控制器
@RequestMapping("/login") // 定义类级别的请求映射，所有方法的URL前缀为/login
public class LoginController {

    @Autowired
    // 自动注入LoginService
    private LoginService loginService;

    // 标记这个方法为GET请求的处理方法
    @GetMapping
    public String login() {
        // 返回登录页面的视图名称
        return "login";
    }

    @PostMapping
    public ModelAndView login(@RequestParam("username") String username, // 获取请求参数username
                              @RequestParam("password") String password, // 获取请求参数password
                              @RequestParam("role") String role, // 获取请求参数role
                              HttpSession session) { // 获取HttpSession对象
        // 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();


        // 根据角色进行不同的登录验证
        switch (role) {
            // 如果角色是学生
            case "student":
                // 调用服务验证学生身份
                Student student = loginService.authenticateAsStudent(username, password);
                // 如果验证成功
                if (student != null) {
                    // 将学生对象存储在session中
                    session.setAttribute("student", student);
                    // 登录成功，重定向到学生页面
                    modelAndView.setViewName("redirect:/student");
                    // 如果验证失败
                } else {
                    // 返回登录页面
                    modelAndView.setViewName("login");
                    // 添加错误信息
                    modelAndView.addObject("error", "无效信息");
                }
                break;
            // 如果角色是宿舍管理员
            case "dormitory_manager":
                // 调用服务验证宿舍管理员身份
                DormitoryAdmin dormitoryAdmin = loginService.authenticateAsDormitoryAdmin(username, password);
                // 如果验证成功
                if (dormitoryAdmin != null) {
                    // 将宿舍管理员对象存储在session中
                    session.setAttribute("dormitoryAdmin", dormitoryAdmin);
                    // 登录成功，重定向到宿舍管理员页面
                    modelAndView.setViewName("redirect:/dormitoryAdmin");
                } else { // 如果验证失败
                    modelAndView.setViewName("login"); // 返回登录页面
                    modelAndView.addObject("error", "无效信息"); // 添加错误信息
                }
                break;
            case "admin": // 如果角色是系统管理员
                SystemAdmin systemAdmin = loginService.authenticateAsSystemAdmin(username, password); // 调用服务验证系统管理员身份
                if (systemAdmin != null) { // 如果验证成功
                    session.setAttribute("systemAdmin", systemAdmin); // 将系统管理员对象存储在session中
                    modelAndView.setViewName("redirect:/systemAdmin"); // 登录成功，重定向到系统管理员页面
                } else { // 如果验证失败
                    modelAndView.setViewName("login"); // 返回登录页面
                    modelAndView.addObject("error", "无效信息"); // 添加错误信息
                }
                break;
            default: // 如果角色无效
                modelAndView.setViewName("login"); // 返回登录页面
                modelAndView.addObject("error", "无效角色"); // 添加错误信息
                break;
        }

        return modelAndView; // 返回ModelAndView对象
    }
}