package com.dsshopping.adapter;

import java.util.ArrayList;
import java.util.List;

import com.dsshopping.R;
import com.dsshopping.model.ListVO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShoppingListAdapter extends BaseAdapter {
	private List<ListVO> data;
	
	private Context context;

	public ShoppingListAdapter(Context ctx) {
		this.context = ctx;
		
		data = new ArrayList<ListVO>();
	}

	
	public int getCount() {
		return data.size();
	}

	public ListVO getItem(int position) {
		
		if (position < data.size()) {
			return data.get(position);
		} else {
			return null;
		}

	}

	public long getItemId(int position) {
		
		return position;

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout rl;
		
		if (convertView == null) {
			rl = (LinearLayout) View.inflate(context,
					R.layout.show_list_item, null);
		} else {
			rl = (LinearLayout) convertView;
		}

		ListVO listVO = getItem(position);
		
		TextView tv = (TextView) rl.findViewById(R.id.listName);
		tv.setText(listVO.getName());
		
		TextView ctv = (TextView) rl.findViewById(R.id.listCountItems);
		ctv.setText(String.format("%d/%d", listVO.getPurchased(), listVO.getTotal()));
		
		ProgressBar pb = (ProgressBar) rl.findViewById(R.id.listProgressBar);
		pb.setMax(listVO.getTotal());
		pb.setProgress(listVO.getPurchased());
		
		return rl;
	}


	public List<ListVO> getData() {
		return data;
	}


	public void setData(List<ListVO> data) {
		this.data = data;
	}

}
