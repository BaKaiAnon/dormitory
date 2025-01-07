package jmu.zzx.dormitory.dto;

public class DormitoryInfoDTO {
    private Integer buildingId;
    private String buildingName;
    private String buildingIntroduction;
    private Integer dormitoryId;
    private String dormitoryName;
    private Integer dormitoryType;
    private Integer dormitoryAvailable;
    private String dormitoryTelephone;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingIntroduction() {
        return buildingIntroduction;
    }

    public void setBuildingIntroduction(String buildingIntroduction) {
        this.buildingIntroduction = buildingIntroduction;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getDormitoryType() {
        return dormitoryType;
    }

    public void setDormitoryType(Integer dormitoryType) {
        this.dormitoryType = dormitoryType;
    }

    public Integer getDormitoryAvailable() {
        return dormitoryAvailable;
    }

    public void setDormitoryAvailable(Integer dormitoryAvailable) {
        this.dormitoryAvailable = dormitoryAvailable;
    }

    public String getDormitoryTelephone() {
        return dormitoryTelephone;
    }

    public void setDormitoryTelephone(String dormitoryTelephone) {
        this.dormitoryTelephone = dormitoryTelephone;
    }
}
