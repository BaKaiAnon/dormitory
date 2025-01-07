package jmu.zzx.dormitory.dto;

import jmu.zzx.dormitory.pojo.Building;
import jmu.zzx.dormitory.pojo.Dormitory;
import jmu.zzx.dormitory.pojo.Student;
import org.springframework.stereotype.Controller;



public class StudentDTO {
    // student_id
    private Integer id;
    // student_number
    private String number;
    // student_name
    private String name;
    // student_gender
    private String gender;

    // Dormitory information
    // dormitory_id
    private Integer dormitory_id;
    // dormitory_buildingId
    private Integer building_id;
    // dormitory_name
    private String dormitory_name;
    // dormitory_type
    private String dormitory_type;
    // dormitory_available
    private Boolean dormitory_available;

    // Building information
    // building_name
    private String building_name;
    // building_introduction
    private String building_introduction;
    // building_adminId
    private Integer building_adminId;

    private int adminId;

    private String adminName;

    private String adminMail;

    private String adminPhone;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getDormitory_id() {
        return dormitory_id;
    }

    public void setDormitory_id(Integer dormitory_id) {
        this.dormitory_id = dormitory_id;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public String getDormitory_name() {
        return dormitory_name;
    }

    public void setDormitory_name(String dormitory_name) {
        this.dormitory_name = dormitory_name;
    }

    public String getDormitory_type() {
        return dormitory_type;
    }

    public void setDormitory_type(String dormitory_type) {
        this.dormitory_type = dormitory_type;
    }

    public Boolean getDormitory_available() {
        return dormitory_available;
    }

    public void setDormitory_available(Boolean dormitory_available) {
        this.dormitory_available = dormitory_available;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getBuilding_introduction() {
        return building_introduction;
    }

    public void setBuilding_introduction(String building_introduction) {
        this.building_introduction = building_introduction;
    }

    public Integer getBuilding_adminId() {
        return building_adminId;
    }

    public void setBuilding_adminId(Integer building_adminId) {
        this.building_adminId = building_adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }
}
