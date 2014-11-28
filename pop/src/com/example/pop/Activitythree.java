package com.example.pop;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
public class Activitythree extends Activity {
	 public Elements title;
     // �� � ��� ����� ������� ������ ���� �� ��������� ��������
     public ArrayList<String> titleList = new ArrayList<String>();
     // Listview Adapter ��� ������ ������
     private ArrayAdapter<String> adapter;
     // List view
     private GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.three);
	    gv = (GridView) findViewById(R.id.gridView1);
        // ������ � ������ ���������� ����� �� ������� ������
        new NewThread().execute();
        // ��������� ������ ��� ListView
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, titleList);
	  }
	/** � ��� � ��������� ����� ������� ������ �������, ���� �� �� ������ ������ � ���� � ����� ��� ���������
     * ������ ������� �������� */
    public class NewThread extends AsyncTask<String, Void, String> {

            // ����� ����������� ������ � ����, � ������� ���� 4 ��������, ������� � ������� ������ ���������
            // ������, ������� ��� ��� ��� ����� ��������� - �������� � ��������� ����
            @Override
            protected String doInBackground(String... arg) {

                    // ����� ������� ����������� ��������
                    Document doc;                    try {
                            // ���������� ������ ����� �������� ������
                            doc = Jsoup.connect("http://1001bilet.com.ua/").get();
                            // ������ � ������ �����, � ������ ��������� ������
                            title = doc.select(".title");
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
                    gv.setAdapter(adapter);
            }
    }
}
