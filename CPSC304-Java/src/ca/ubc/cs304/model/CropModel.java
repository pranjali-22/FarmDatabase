package ca.ubc.cs304.model;

public class CropModel {
    String crop_name;
    int growth_duration_days;
    public CropModel(String crop_name, int growth_duration_days) {
        this.crop_name = crop_name;
        this.growth_duration_days = growth_duration_days;
    }

    public int getGrowth_duration_days() {
        return growth_duration_days;
    }

    public String getCrop_name() {
        return crop_name;
    }
}
