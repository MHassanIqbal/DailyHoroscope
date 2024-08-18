package t.hkb.designhoroscope.JSONApiResponse.utils;

public class JSONProperties {

    private  String date_range;
    private  String current_date;
    private  String description;
    private  String compatibility;
    private  String mood;
    private  String color;
    private  String lucky_number;
    private  String lucky_time;

    public JSONProperties(String date_range, String current_date, String description, String compatibility, String mood, String color, String lucky_number, String lucky_time) {

        this.date_range = date_range;
        this.current_date = current_date;
        this.description =description;
        this.compatibility = compatibility;
        this.mood = mood;
        this.color = color;
        this.lucky_number = lucky_number;
        this.lucky_time = lucky_time;
    }

    public String getDate_range() {
        return date_range;
    }

    public void setDate_range(String date_range) {
        this.date_range = date_range;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(String lucky_number) {
        this.lucky_number = lucky_number;
    }

    public String getLucky_time() {
        return lucky_time;
    }

    public void setLucky_time(String lucky_time) {
        this.lucky_time = lucky_time;
    }

}
