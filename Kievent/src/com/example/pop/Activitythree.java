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

        // Метод выполняющий запрос в фоне, в версиях выше 4 андроида, запросы в главном потоке выполнять
        // нельзя, поэтому все что вам нужно выполнять - выносите в отдельный тред
        @Override
        protected String doInBackground(String... arg) {

                // класс который захватывает страницу
                Document doc;
                try {
                        // определяем откуда будем воровать данные
                        doc = Jsoup.connect("http://1001bilet.com.ua/estrada.html").get();
                        // задаем с какого места, я выбрал заголовке статей
                        title = doc.select("div[class=info]");
                        String className = title.attr("class");
                        // чистим наш аррей лист для того что бы заполнить
                        titleList.clear();
                        // и в цикле захватываем все данные какие есть на странице
                        for (Element titles : title) {
                                // записываем в аррей лист
                                titleList.add(titles.text());
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                // ничего не возвращаем потому что я так захотел)
                return null;
        }

        @Override
        protected void onPostExecute(String result) {

                // после запроса обновляем листвью
                lv.setAdapter(adapter);
        }
}
}
