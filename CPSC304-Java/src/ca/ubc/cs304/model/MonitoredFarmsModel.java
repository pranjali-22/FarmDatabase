package ca.ubc.cs304.model;

public class MonitoredFarmsModel {
    String farm_id;
    String location;
    float area_acre;
    String supervisor_id;
    String phone_number;
    String supervisor_name;
    int salary;

    public MonitoredFarmsModel(String farm_id, String location,
                                  float area_acre, String supervisor_id,
                                  String phone_number, String supervisor_name, int salary) {
        this.farm_id = farm_id;
        this.location = location;
        this.area_acre = area_acre;
        this.supervisor_id = supervisor_id;
        this.phone_number = phone_number;
        this.supervisor_name = supervisor_name;
        this.salary = salary;
    }
    public String getFarm_id() {
        return farm_id;
    }

    public int getSalary() {
        return salary;
    }

    public String getSupervisor_name() {
        return supervisor_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSupervisor_id() {
        return supervisor_id;
    }

    public float getArea_acre() {
        return area_acre;
    }

    public String getLocation() {
        return location;
    }
}
