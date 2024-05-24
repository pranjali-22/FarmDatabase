package ca.ubc.cs304.model;
// TODO: fix the datatype for start_date
public class ProduceAndStatusModel {
    String crop_name;
    String start_month;
    int start_year;
    String crop_status;
    String farm_id;
    int quantity;

    public ProduceAndStatusModel(String farm_id,String crop_name,String start_month,int start_year, int quantity,String crop_status) {
        this.farm_id = farm_id;
        this.crop_name = crop_name;
        this.crop_status = crop_status;
        this.start_month = start_month;
        this.start_year = start_year;
        this.quantity = quantity;
    }
    public String getCrop_name() {
        return crop_name;
    }
    public String getStart_month() {
        return start_month;
    }
    public int getStart_year() {
        return start_year;
    }
    public String getCrop_status() {
        return crop_status;
    }
    public String getFarm_id() {
        return farm_id;
    }
    public int getQuantity() {
        return quantity;
    }
}
