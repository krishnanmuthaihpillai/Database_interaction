package in.ethicstech.database_interaction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = "http://momsdhaba.com/mobileapp/chefviewfood/";
    Chef_DB_Helper db;
//    ArrayList<ArrayList<String>> allDetails;
ArrayList<ArrayList<String>>  total_list = new ArrayList<>();

    List<String> chef_id;
    List<String> food_id;
    List<String> chef_name;
    List<String> food_type;
    List<String> food_name;
    List<String> food_desc;
    List<String> food_image;
    List<String> food_price;
    List<String> food_qantity;
    List<String> food_available;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert_data(getApplicationContext(), url, db);

    }

    private void insert_data(Context context, String url, Chef_DB_Helper db) {
        Load_Food asyncTask = new Load_Food(new ResultUpdater() {
            @Override
            public void processFinish(String output) {
                //Result
                toast_it(output);
                total_list=getFoodList("22");
                make_view(total_list);


                Log.d("output", "" + output);
            }

        }, context, db, url);
        asyncTask.execute();

    }

    private void make_view(ArrayList<ArrayList<String>> total_list) {

        System.out.println("Actual ArrayList:"+total_list);

        chef_id=total_list.get(0);
        food_id=total_list.get(1);
        chef_name=total_list.get(2);
        food_type=total_list.get(3);
        food_name=total_list.get(4);
        food_desc=total_list.get(5);
        food_image=total_list.get(6);
        food_price=total_list.get(7);
        food_qantity=total_list.get(8);
        food_available=total_list.get(9);

        log_it(""+chef_id);
        log_it(""+food_id);
        log_it(""+chef_name);
        log_it(""+food_type);
        log_it(""+food_name);
        log_it(""+food_desc);
        log_it(""+food_image);
        log_it(""+food_price);
        log_it(""+food_qantity);
        log_it(""+food_available);

        log_it(""+chef_id.size());
        log_it(""+food_id.size());
        log_it(""+chef_name.size());
        log_it(""+food_type.size());
        log_it(""+food_name.size());
        log_it(""+food_desc.size());
        log_it(""+food_image.size());
        log_it(""+food_price.size());
        log_it(""+food_qantity.size());
        log_it(""+food_available.size());
    }

    private void log_it(String msg) {

        Log.d("KRISHNAN",""+msg);
    }

//    private ArrayList<ArrayList<String>> get_data() {
//        ArrayList<ArrayList<String>>  arrayList = new ArrayList<>();
//        arrayList = getFoodList("22");
//        return arrayList;
//    }

    private ArrayList<ArrayList<String>> getFoodList(String chefId) {
        ArrayList<ArrayList<String>> foodDet = new ArrayList<>();
        db = new Chef_DB_Helper(getApplicationContext());
        foodDet = db.get_chef_food_list(chefId);
        return foodDet;
    }

    private void toast_it(final String output) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "" + output, Toast.LENGTH_LONG).show();


            }
        });

    }
}
