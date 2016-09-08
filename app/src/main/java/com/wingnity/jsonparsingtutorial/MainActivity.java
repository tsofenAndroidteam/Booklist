package com.wingnity.jsonparsingtutorial;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ArrayList<Book> bookList;
	
	bookAdapter adapter;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();

        // Hiding the Status Bar
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
             decorView.setSystemUiVisibility(uiOptions);
             // Remember that you should never show the action bar if the
             // status bar is hidden, so hide that too if necessary.
             ActionBar actionBar = getActionBar();
		     actionBar.hide();
        //Hiding the Status Bar
        setContentView(R.layout.activity_main);
		bookList = new ArrayList<Book>();
        //new JSONAsyncTask().execute("http://ec2-52-43-108-148.us-west-2.compute.amazonaws.com:8080/useraccount/search/dosearchbytitle?userid=123123&title=me&fromyear=1960&toyear=1970");
		new JSONAsyncTask().execute("http://52.29.110.203:8080/LibArab/search/booktitle?userId=23&title=any");
		  //http://ec2-52-43-108-148.us-west-2.compute.amazonaws.com:8080/useraccount/search/dosearchbytitle?userid=123123&title=me&fromyear=1960&toyear=1970
		ListView listview = (ListView)findViewById(R.id.list);
		adapter = new bookAdapter(getApplicationContext(), R.layout.row, bookList);
		
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), bookList.get(position).getTitle(), Toast.LENGTH_LONG).show();
			}
		});
	}


	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
			dialog.show();
			dialog.setCancelable(false);
		}
		
		@Override
		protected Boolean doInBackground(String... urls) {
			try {
				
				//------------------>>
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);

				// StatusLine stat = response.getStatusLine();
				int status = response.getStatusLine().getStatusCode();

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);

					JSONObject jsono = new JSONObject(data);
					JSONArray jarray = jsono.getJSONArray("docs");
					
					for (int i = 0; i < jarray.length(); i++)
                    {
						JSONObject object = jarray.getJSONObject(i);
						Book currentbook = new Book();
						currentbook.setRecordid(object.getString("recordId"));
						currentbook.setTitle(object.getString("title"));
						currentbook.setCreationdate(object.getString("creationdate"));
						currentbook.setPublisher(object.getString("publisher"));
						currentbook.setAuthor(object.getString("author"));
                        bookList.add(currentbook);
					}
					return true;
				}
				
				//------------------>>
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		protected void onPostExecute(Boolean result) {
			dialog.cancel();
			adapter.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

		}
	}
	
	

	
	
	
}
