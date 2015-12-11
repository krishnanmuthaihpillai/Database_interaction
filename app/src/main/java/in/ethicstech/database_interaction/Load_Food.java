package in.ethicstech.database_interaction;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishnan on 10/12/15.
 */
public class Load_Food extends AsyncTask<String, String, String> {
    public ResultUpdater resultUpdater = null;
    Context context = null;
    String url = null;
    Chef_DB_Helper db;

    public Load_Food(ResultUpdater resultUpdater, Context context, Chef_DB_Helper db, String url) {
        Log.d("Load_Food", "Load_Food");
        this.resultUpdater = resultUpdater;
        this.context = context;
        this.url = url;
        this.db = db;


    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Log.d("doInBackground", "doInBackground");
//            db = new Chef_DB_Helper(context);
//            ServiceHandler serviceHandler = new ServiceHandler();

            JsonParser jParser = new JsonParser();
            List<NameValuePair> sFood = new ArrayList<NameValuePair>();
            sFood.add(new BasicNameValuePair("id", "22"));
            JSONObject json = jParser.getJSONFromUrlPOST(sFood, url);

//            String jsonData = serviceHandler.makeServiceCall(url, ServiceHandler.POST);
//            if (jsonData.length() > 0) {
//                JSONObject jsonObj = new JSONObject(jsonData);
            JSONArray jArray = json.getJSONArray("chef_food_today");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);
                String f_status = obj.getString("status");
                String f_food_quantity = obj.getString("food_quantity");
                String f_description = obj.getString("description");
                String f_food_image = obj.getString("food_image");
                String f_available_date = obj.getString("available_date");
                String f_food_type = obj.getString("food_type");
                String f_chef_id = obj.getString("chef_id");
                String f_food_name = obj.getString("food_name");
                String f_food_id = obj.getString("food_id");
                String f_food_price = obj.getString("food_price");
                String f_chef_name = obj.getString("chef_name");
                String image_filepath = download_image(f_food_image, f_food_name);


//                    Log.d("f_status",""+f_status);
//                    Log.d("f_food_quantity",""+f_food_quantity);
//                    Log.d("f_description",""+f_description);
//                    Log.d("f_food_image",""+f_food_image);
//                    Log.d("f_available_date",""+f_available_date);
//                    Log.d("f_food_type",""+f_food_type);
//                    Log.d("f_chef_id",""+f_chef_id);
//                    Log.d("f_food_name",""+f_food_name);
//                    Log.d("f_food_id",""+f_food_id);
//                    Log.d("f_food_price",""+f_food_price);
//                    Log.d("f_status",""+f_status);
//                    Log.d("f_chef_name",""+f_chef_name);
                Log.d("get_filepath", "" + image_filepath);

//                Log.d("imgurl", "" + imgurl);


//                    db.insert_data(f_chef_id,f_food_id,f_chef_name,f_food_type,f_food_name,f_description,f,foodDesc,foodQuantity,foodPrice,foodType);
//                }


            }

        } catch (Exception e) {
            Log.e("Exception", "" + e);
        }
        return "EXECUTED";
    }


    @Override
    protected void onPostExecute(String s) {

    }

    private String download_image(String image_url, String food_name) {

        String filePath = Environment.getExternalStorageDirectory() + "/momsdhaba/chef";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            URL url = new URL(image_url);
            URLConnection urlcon = url.openConnection();
            urlcon.connect();
            InputStream input = new BufferedInputStream(url.openStream());
             food_name=food_name.replaceAll("\\s+","") +"."+ get_file_extension(image_url);
            file = new File(file + "/" + food_name);
            if (file.exists()){
                file.delete();
            }
            food_name=file.toString();
            OutputStream output = new FileOutputStream(file);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {

        }

        return food_name;
    }

    private String get_file_extension(String image_url) {
        String filename = getFileName(image_url);
        return ext(filename);
    }

    private String ext(String filename) {
        String filenameArray[] = filename.split("\\.");
        return filenameArray[filenameArray.length - 1];
    }

    public String getFileName(String fPath) {
        String[] temp = fPath.split("/");
        return temp[temp.length - 1];
    }
}
