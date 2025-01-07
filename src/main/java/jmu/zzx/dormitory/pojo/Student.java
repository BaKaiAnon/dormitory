package jmu.zzx.dormitory.pojo;



import java.time.LocalDateTime;


public class Student {
    private Integer id;
    private String number;
    private String name;
    private String username;
    private String password;
    private String gender;
    private Integer dormitory_Id;
    private Integer oldDormitory_Id;
    private String dormitoryName;
    private String state;
    private LocalDateTime create_Date;
    private LocalDateTime update_Date;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(LocalDateTime update_Date) {
        this.update_Date = update_Date;
    }

    public LocalDateTime getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        this.create_Date = create_Date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getOldDormitory_Id() {
        return oldDormitory_Id;
    }

    public void setOldDormitory_Id(Integer oldDormitory_Id) {
        this.oldDormitory_Id = oldDormitory_Id;
    }

    public Integer getDormitory_Id() {
        return dormitory_Id;
    }

    public void setDormitory_Id(Integer dormitory_Id) {
        this.dormitory_Id = dormitory_Id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
