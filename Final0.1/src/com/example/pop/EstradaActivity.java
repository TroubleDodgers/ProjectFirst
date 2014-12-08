package com.example.pop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class EstradaActivity extends Activity {
	public Elements title;
	public Elements img;
	public Elements link;
	public Bitmap bitmap;
	public ArrayList<Item> titleList = new ArrayList<>();
	private ItemAdapter adapter;
	private ListView lv;
	private Item newItem;
	private String url;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.results_layout);	
	    progressDialog= ProgressDialog.show(this, "", "Loading. Please wait...", true);
       
	    Bundle gotBasket = getIntent().getExtras();
	    url = gotBasket.getString("url");
	    lv = (ListView)findViewById(R.id.lvLinks);
	    new NewThread().execute();
	    adapter = new ItemAdapter(this, titleList);
	   
	};
	
	public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {

                Document doc;
                try {
                        doc = Jsoup.connect(url).get();
                        title = doc.select("div[class=info]");
                        img = doc.select("a[class=photo] img[src]");
                        link = doc.select("a[class=order]");
                        titleList.clear();
                        for (int i = 0; i < title.size(); i++) {
                        	newItem = new Item();
                        	newItem.setTitle(title.get(i).select("a.title").text());
                        	newItem.setAddress(title.get(i).select("a.place.fn.org").text());
                        	newItem.setDate(title.get(i).select("div.date").text().split(" ")[0]);
                        	newItem.setTime(title.get(i).select("div.time").text());
                        	newItem.setPrice(title.get(i).select("div.price").text());
                        	newItem.setOrder(link.get(i).attr("abs:href"));
                        	String imgSrc = img.get(i).absUrl("src");
            				InputStream input = new java.net.URL(imgSrc).openStream();
            				bitmap = BitmapFactory.decodeStream(input);
            				newItem.setImage(bitmap);
            				titleList.add(newItem);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                progressDialog.dismiss();
                return null;
        }

        @Override
        protected void onPostExecute(String result) {
                lv.setAdapter(adapter);
        }
	};
	 
}
