package in.ethicstech.database_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krishnan on 10/12/15.
 */
public class Chef_DB_Helper extends SQLiteOpenHelper {


    public static final String TABLE_CHEF_FOOD_DETAILS = "CHEF_FOOD_DETAILS";
    public static final String KEY_CHEF_ID = "CHEF_ID";
    public static final String KEY_FOOD_ID = "FOOD_ID";
    public static final String KEY_CHEF_NAME = "CHEF_NAME";
    public static final String KEY_FOOD_TYPE = "FOOD_TYPE";
    public static final String KEY_FOOD_NAME = "FOOD_NAME";
    public static final String KEY_FOOD_DESCRIPTION = "FOOD_DESCRIPTION";
    public static final String KEY_FOOD_IMAGE_PATH = "FOOD_IMAGE_PATH";
    public static final String KEY_FOOD_PRICE = "FOOD_PRICE";
    public static final String KEY_FOOD_QUANTITY = "FOOD_QUANTITY";
    public static final String KEY_FOOD_AVAILABLE_DATE = "FOOD_AVAILABLE_DATE";


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MomsDhaba";

    public Chef_DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHEF_FOOD_TABLE = "CREATE TABLE CHEF_FOOD_DETAILS ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "CHEF_ID TEXT, " + "FOOD_ID TEXT, " + "CHEF_NAME TEXT, "
                + "FOOD_TYPE TEXT, " + "FOOD_NAME TEXT, " + "FOOD_DESCRIPTION TEXT, " + "FOOD_IMAGE_PATH TEXT, " + "FOOD_PRICE TEXT, "
                + "FOOD_QUANTITY TEXT, " + "FOOD_AVAILABLE_DATE TEXT) ";

        db.execSQL(CREATE_CHEF_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CHEF_FOOD_DETAILS");
        this.onCreate(db);
    }

    public void insert_data(String chefId, String foodId, String chefName, String foodType, String foodName, String foodDescription, String foodImagePath, String foodPrice, String foodQuantity, String foodAvailableDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHEF_ID, chefId);
        values.put(KEY_FOOD_ID, foodId);
        values.put(KEY_CHEF_NAME, chefName);
        values.put(KEY_FOOD_TYPE, foodType);
        values.put(KEY_FOOD_NAME, foodName);
        values.put(KEY_FOOD_DESCRIPTION, foodDescription);
        values.put(KEY_FOOD_IMAGE_PATH, foodImagePath);
        values.put(KEY_FOOD_PRICE, foodPrice);
        values.put(KEY_FOOD_QUANTITY, foodQuantity);
        values.put(KEY_FOOD_AVAILABLE_DATE, foodAvailableDate);
        db.insertWithOnConflict(TABLE_CHEF_FOOD_DETAILS, KEY_CHEF_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
}