package in.ethicstech.database_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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


    public ArrayList<ArrayList<String>> get_chef_food_list(String chef_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String locQuery = "SELECT * FROM " + TABLE_CHEF_FOOD_DETAILS + " WHERE " + KEY_CHEF_ID + " ='" + chef_id + "'";
        ArrayList<ArrayList<String>> total_result = new ArrayList<ArrayList<String>>();
        ArrayList<String> chefId = new ArrayList<String>();
        ArrayList<String> foodId = new ArrayList<String>();
        ArrayList<String> chefName = new ArrayList<String>();
        ArrayList<String> foodType = new ArrayList<String>();
        ArrayList<String> foodName = new ArrayList<String>();
        ArrayList<String> foodDescription = new ArrayList<String>();
        ArrayList<String> foodImagePath = new ArrayList<String>();
        ArrayList<String> foodPrice = new ArrayList<String>();
        ArrayList<String> foodQuantity = new ArrayList<String>();
        ArrayList<String> foodAvailableDate = new ArrayList<String>();
        total_result.clear();
        Cursor cursor = db.rawQuery(locQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                chefId.add(cursor.getString(cursor.getColumnIndex(KEY_CHEF_ID)));
                foodId.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_ID)));
                chefName.add(cursor.getString(cursor.getColumnIndex(KEY_CHEF_NAME)));
                foodType.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_TYPE)));
                foodName.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME)));
                foodDescription.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_DESCRIPTION)));
                foodImagePath.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_IMAGE_PATH)));
                foodPrice.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_PRICE)));
                foodQuantity.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_QUANTITY)));
                foodAvailableDate.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_AVAILABLE_DATE)));
            } while (cursor.moveToNext());
        }
        total_result.add(chefId);
        total_result.add(foodId);
        total_result.add(chefName);
        total_result.add(foodType);
        total_result.add(foodName);
        total_result.add(foodDescription);
        total_result.add(foodImagePath);
        total_result.add(foodPrice);
        total_result.add(foodQuantity);
        total_result.add(foodAvailableDate);
        cursor.close();
        db.close();
        return total_result;

    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_CHEF_FOOD_DETAILS);
        db.close();
    }
}