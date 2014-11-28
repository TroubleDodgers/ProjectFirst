package com.example.pop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activitythree extends Activity {
	public Elements title;
	public ArrayList<String> titleList = new ArrayList<>();
	private ArrayAdapter<String> adapter;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.three);
	    lv = (ListView)findViewById(R.id.lvLinks);
	    new NewThread().execute();
	    adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.product_name, titleList);
	  }
	public class NewThread extends AsyncTask<String, Void, String> {

        // ����� ����������� ������ � ����, � ������� ���� 4 ��������, ������� � ������� ������ ���������
        // ������, ������� ��� ��� ��� ����� ��������� - �������� � ��������� ����
        @Override
        protected String doInBackground(String... arg) {

                // ����� ������� ����������� ��������
                Document doc;
                try {
                        // ���������� ������ ����� �������� ������
                        doc = Jsoup.connect("http://1001bilet.com.ua/estrada.html").get();
                        // ������ � ������ �����, � ������ ��������� ������
                        title = doc.select("div[class=info]");
                        String className = title.attr("class");
                        // ������ ��� ����� ���� ��� ���� ��� �� ���������
                        titleList.clear();
                        // � � ����� ����������� ��� ������ ����� ���� �� ��������
                        for (Element titles : title) {
                                // ���������� � ����� ����
                                titleList.add(titles.text());
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                // ������ �� ���������� ������ ��� � ��� �������)
                return null;
        }

        @Override
        protected void onPostExecute(String result) {

                // ����� ������� ��������� �������
                lv.setAdapter(adapter);
        }
}
}
