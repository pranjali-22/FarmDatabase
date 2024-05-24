package ca.ubc.cs304.model;
// TODO: fix the datatype for start_date
public class ProduceModel {
    String crop_name;
    String farm_id;
    String start_month;
    int start_year;
    int quantity;

    public ProduceModel(String crop_name, String farm_id, String start_month,int start_year,
                              int quantity) {
        this.farm_id = farm_id;
        this.start_month = start_month;
        this.start_year = start_year;
        this.quantity = quantity;
        this.crop_name = crop_name;
    }
    public String getCrop_name() {
        return crop_name;
    }
    public String getFarm_id() {
        return farm_id;
    }
    public String getStart_month() {
        return start_month;
    }
    public int getStart_year() {
        return start_year;
    }
    public int getQuantity() {
        return quantity;
    }


}
