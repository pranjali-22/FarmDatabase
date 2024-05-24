package ca.ubc.cs304.model;
public class WorkingFarmersModel {
    String farmer_id;
    String farmer_name;
    String farmer_phone_number;
    String farmer_type;
    String farm_id;
    float farmer_hours;

    public WorkingFarmersModel(String farmer_id, String farmer_name, String farmer_phone_number,
                       String farmer_type, String farm_id, float farmer_hours) {
        this.farm_id = farm_id;
        this.farmer_id = farmer_id;
        this.farmer_name = farmer_name;
        this.farmer_phone_number = farmer_phone_number;
        this.farmer_type = farmer_type;
        this.farmer_hours = farmer_hours;
    }

    public float getFarmer_hours() {
        return farmer_hours;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getFarmer_type() {
        return farmer_type;
    }

    public String getFarmer_phone_number() {
        return farmer_phone_number;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public String getFarmer_id() {
        return farmer_id;
    }
}
