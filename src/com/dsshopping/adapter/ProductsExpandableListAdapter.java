package com.dsshopping.adapter;

import java.util.List;

import com.dsshopping.R;
import com.dsshopping.model.CategoryVO;
import com.dsshopping.model.ProductDescriptionVO;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProductsExpandableListAdapter extends BaseExpandableListAdapter {
	private List<CategoryVO> data; 
	
	private Context context;

	public ProductsExpandableListAdapter(Context ctx) {
		this.context = ctx;
	}

	public ProductDescriptionVO getChild(int groupPosition, int childPosition) {
		if (groupPosition >= data.size()) {
			return null;
		}
		
		int size = data.get(groupPosition).getProductDescriptionList().size();
		
		if (childPosition >= size) {
			return null;
		}
		
		return data.get(groupPosition).getProductDescriptionList().get(childPosition);
	}
	
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		RelativeLayout rl;
		if (convertView == null) {
			rl = (RelativeLayout) View.inflate(context,
					R.layout.product_exp_list_item, null);
		} else {
			rl = (RelativeLayout) convertView;
		}

		ProductDescriptionVO product = getChild(groupPosition, childPosition);

//		ImageView imgView = (ImageView) ll.findViewById(R.id.promotion_item_image_view);
//		imgView.setImageBitmap(RequestUtils.getBitmapFromURL(current.getLogo())); 
		
		TextView productName = (TextView) rl.findViewById(R.id.productExpListName);
		productName.setText(product.getName());

		return rl;
	}
	
	public int getChildrenCount(int groupPosition) {
		if (groupPosition >= data.size()) {
			return 0;
		} else {
			return data.get(groupPosition).getProductDescriptionList().size();
		}
	}

	public CategoryVO getGroup(int groupPosition) {
		if (groupPosition >= data.size()) {
			return null;
		} else {
			return data.get(groupPosition);
		}
	}

	public int getGroupCount() {
		return data.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		RelativeLayout rl;
		if (convertView == null) {
			rl = (RelativeLayout) View.inflate(context,
					R.layout.category_exp_list_item, null);
		} else {
			rl = (RelativeLayout) convertView;
		}
		
		rl.setBackgroundColor(Color.rgb(235, 235, 235));
		
		TextView category_name = (TextView) rl.findViewById(R.id.categoryExpListName);
		category_name.setText(getGroup(groupPosition).getName());

		return rl;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public void setData(List<CategoryVO> data) {
		this.data = data;
	}

	public List<CategoryVO> getData() {
		return data;
	}
}
