package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single Farm
 */
public class FarmAndSupervisorModel {
    private final String area;
    private final String location;
    private final int farm_id;
    private final int supervisor_id;
    private final String phone_number;
    private final int salary;
    private final String supervisor_name;
    private final int bonus;

    public FarmAndSupervisorModel(String area, String location, String supervisor_name, String phone_number, int farm_id, int supervisor_id, int salary, int bonus) {
        this.area = area;
        this.location = location;
        this.farm_id = farm_id;
        this.supervisor_name = supervisor_name;
        this.supervisor_id = supervisor_id;
        this.phone_number = phone_number;
        this.salary = salary;
        this.bonus = bonus;
    }

    public String getArea() {
        return area;
    }

    public String getLocation() {
        return location;
    }

    public int getFarmId() {
        return farm_id;
    }

    public String getSupervisor_name() {
        return supervisor_name;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public int getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }


}
