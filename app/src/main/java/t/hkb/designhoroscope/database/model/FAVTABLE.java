package t.hkb.designhoroscope.database.model;

public class FAVTABLE {



    // Create table SQL query

    public static final String TABLE_NAME = "StoreFavHoroscopes";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_ISFAV = "isFav";
    public static final String COLUMN_DIC = "dicription";
    public static final String COLUMN_DATERANGE = "date_range";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_COMPATIBILITY = "compatibility";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_LUCKYNUMBER = "lucky_number";
    public static final String COLUMN_LUCKYTIME = "lucky_time";
    public static final String COLUMN_TIMESTAMP = "timestamp";


    private int    id;
    private String isFav;
    private String discription;
    private String color;
    private String date_range;
    private String compatibility;
    private String mood;
    private String lucky_number;
    private String lucky_time;
    private String timestamp;

    public FAVTABLE(int id, String isFav, String discription, String color, String date_range, String compatibility, String mood, String lucky_number, String lucky_time, String timestamp) {
        this.id = id;
        this.isFav = isFav;
        this.discription = discription;
        this.color = color;
        this.date_range = date_range;
        this.compatibility = compatibility;
        this.mood = mood;
        this.lucky_number = lucky_number;
        this.lucky_time = lucky_time;
        this.timestamp = timestamp;
    }



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ISFAV + " INTEGER,"
                    + COLUMN_DIC + " TEXT,"
                    + COLUMN_DATERANGE + " TEXT,"
                    + COLUMN_COLOR + " TEXT,"
                    + COLUMN_COMPATIBILITY + " TEXT,"
                    + COLUMN_MOOD + " TEXT,"
                    + COLUMN_LUCKYNUMBER + " TEXT,"
                    + COLUMN_LUCKYTIME + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsFav() {
        return isFav;
    }

    public void setIsFav(String isFav) {
        this.isFav = isFav;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate_range() {
        return date_range;
    }

    public void setDate_range(String date_range) {
        this.date_range = date_range;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



}
