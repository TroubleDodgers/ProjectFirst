package com.example.pop;

import java.util.ArrayList;

import com.example.pop.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
	private static ArrayList<Item> itemList;
	private LayoutInflater lInflater;
	private Bitmap img;
	private Context mContext;
	public ItemAdapter(Context context, ArrayList<Item> results) {
		  itemList = results;
		  lInflater = LayoutInflater.from(context);
		  mContext = context;
		 }

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			convertView = lInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
			holder.tvDate = (TextView)convertView.findViewById(R.id.tvDate);
			holder.tvTime = (TextView)convertView.findViewById(R.id.tvTime);
			holder.tvAddress = (TextView)convertView.findViewById(R.id.tvAddress);
			holder.tvPrice = (TextView)convertView.findViewById(R.id.tvPrice);
			holder.itemImage = (ImageView)convertView.findViewById(R.id.ivImg);
			holder.bMap = (Button)convertView.findViewById(R.id.bMap);
			holder.bOrder = (Button)convertView.findViewById(R.id.bOrder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tvTitle.setText(itemList.get(position).getTitle());
		holder.tvDate.setText(itemList.get(position).getDate());
		holder.tvTime.setText(itemList.get(position).getTime());
		holder.tvAddress.setText(itemList.get(position).getAddress());
		holder.tvPrice.setText(itemList.get(position).getPrice());
		holder.itemImage.setImageBitmap(itemList.get(position).getImage());
		holder.bMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (parent.getContext(), MapActivity.class);
				Bundle basket = new Bundle();
				String addr = itemList.get(position).getAddress();
				basket.putString("addr", addr);
				intent.putExtras(basket);
	    	    parent.getContext().startActivity(intent);
			}
		});
		holder.bOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (parent.getContext(), OrderActivity.class);
				Bundle basket = new Bundle();
				String addr = itemList.get(position).getOrder();
				basket.putString("addr", addr);
				intent.putExtras(basket);
	    	    parent.getContext().startActivity(intent);
			}
		});
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView tvTitle;
		TextView tvDate;
		TextView tvTime;
		TextView tvAddress;
		TextView tvPrice;
		ImageView itemImage;
		Button bMap;
		Button bOrder;
	}
}
