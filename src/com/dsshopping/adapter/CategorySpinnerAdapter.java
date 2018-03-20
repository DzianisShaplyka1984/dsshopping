package com.dsshopping.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsshopping.R;
import com.dsshopping.model.CategoryVO;

public class CategorySpinnerAdapter extends BaseAdapter {
	private List<CategoryVO> data;

	private Context context;

	public CategorySpinnerAdapter(Context ctx) {
		this.context = ctx;

		data = new ArrayList<CategoryVO>();
	}

	public int getCount() {
		return data.size();
	}

	public CategoryVO getItem(int position) {

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
		RelativeLayout rl;

		if (convertView == null) {
			rl = (RelativeLayout) View.inflate(context,
					R.layout.show_category_spinner_item, null);
		} else {
			rl = (RelativeLayout) convertView;
		}

		TextView tv = (TextView) rl.findViewById(R.id.categorySpinnerName);
		tv.setText(getItem(position).getName());

		return rl;
	}

	public List<CategoryVO> getData() {
		return data;
	}

	public void setData(List<CategoryVO> data) {
		this.data = data;
	}

}
