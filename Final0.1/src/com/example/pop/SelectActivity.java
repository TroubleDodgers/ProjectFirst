package com.example.pop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SelectActivity extends Activity implements OnClickListener {
	private TextView tv;
	private Button btnAccept;
	private RadioButton estradabutt; 
	private RadioButton rockbutt;
	private RadioButton baletbutt;
	private RadioButton spectacletbutt;
	private String url;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.select_layout);
	    btnAccept = (Button) findViewById(R.id.btnAccept);
	    estradabutt = (RadioButton) findViewById(R.id.estrada);
	    rockbutt = (RadioButton) findViewById(R.id.rock);
	    baletbutt = (RadioButton) findViewById(R.id.balet);
	    spectacletbutt = (RadioButton) findViewById(R.id.spectacles);
        btnAccept.setOnClickListener(this);

	  }
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	      // TODO Auto-generated method stub
	      // добавл€ем пункты меню
	      menu.add(0, 1, 0, "Calendar");
	      
	      return super.onCreateOptionsMenu(menu);
	    }
	    
	    // обновление меню
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	      // TODO Auto-generated method stub
	      // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка

	      return super.onPrepareOptionsMenu(menu);
	    }
	    // обработка нажатий
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	      // TODO Auto-generated method stub
	      StringBuilder sb = new StringBuilder();
	      // ¬ыведем в TextView информацию о нажатом пункте меню 
	      sb.append("Item Menu");
	      sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
	      sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
	      sb.append("\r\n order: " + String.valueOf(item.getOrder()));
	      sb.append("\r\n title: " + item.getTitle());
	      tv.setText(sb.toString());
	      
	      return super.onOptionsItemSelected(item);
	    };


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	 
		
		switch (v.getId()) {
		
	    case R.id.btnAccept: 
	    	 
	    	    if (estradabutt.isChecked() == true){
	    	    	url = "http://1001bilet.com.ua/estrada.html";
	    	break;}
	    	    if (rockbutt.isChecked() == true){
	    	    	url = "http://1001bilet.com.ua/rock-metal.html";
		    	break;}
	    	    if (baletbutt.isChecked() == true){
	    	    	url = "http://1001bilet.com.ua/balet.html";
		    	break;}
	    	    if (spectacletbutt.isChecked() == true){
	    	    	url = "http://1001bilet.com.ua/teatr.html";
		    	break;}
	    }

	    Intent intent = new Intent (this, EstradaActivity.class);
    	Bundle basket = new Bundle();
    	basket.putString("url", url);
    	intent.putExtras(basket);
	    startActivity(intent);
	    }
}
	
	 
