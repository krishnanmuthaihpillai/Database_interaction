package in.ethicstech.database_interaction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String url="http://momsdhaba.com/mobileapp/chefviewfood/";
    Chef_DB_Helper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
insert_data(getApplicationContext(),url,db);

    }

    private void insert_data(Context context, String url, Chef_DB_Helper db) {

        Load_Food asyncTask = new Load_Food(new ResultUpdater() {
            @Override
            public void processFinish(String output) {
                //Result
                Log.d("output",""+output);
            }

        }, context,db, url);
        asyncTask.execute();

    }
}
