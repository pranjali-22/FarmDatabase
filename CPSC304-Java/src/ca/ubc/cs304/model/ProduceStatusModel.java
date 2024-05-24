package ca.ubc.cs304.model;
// TODO: fix the datatype for start_date
public class ProduceStatusModel {
    String crop_name;
    String start_month;
    int start_year;
    String crop_status;
    String farm_id;

    public ProduceStatusModel(String crop_name, String farm_id, String start_month,int start_year, String crop_status) {
        this.crop_name = crop_name;
        this.farm_id = farm_id;
        this.start_month = start_month;
        this.start_year = start_year;
        this.crop_status = crop_status;
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
}
