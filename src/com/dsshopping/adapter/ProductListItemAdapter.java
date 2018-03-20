package com.dsshopping.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsshopping.R;
import com.dsshopping.model.ProductVO;

public class ProductListItemAdapter extends BaseAdapter {
	private List<ProductVO> data;

	private Context context;

	public ProductListItemAdapter(Context ctx) {
		this.context = ctx;

		data = new ArrayList<ProductVO>();
	}

	public int getCount() {
		return data.size();
	}

	public ProductVO getItem(int position) {

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
					R.layout.show_product_item, null);
		} else {
			rl = (RelativeLayout) convertView;
		}

		ProductVO product = getItem(position);

		TextView tv = (TextView) rl.findViewById(R.id.productName);
		tv.setText(product.getName());

		rl.setBackgroundColor((product.isPurchased()) ? Color.GRAY
				: Color.WHITE);

		return rl;
	}

	public List<ProductVO> getData() {
		return data;
	}

	public void setData(List<ProductVO> data) {
		this.data = data;
	}

}
