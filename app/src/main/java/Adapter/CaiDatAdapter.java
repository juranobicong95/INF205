package Adapter;

import java.util.List;

import com.edu.fpt.demoparse.R;
import com.parse.ParseObject;

import fpoly_model.KhoanTC;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CaiDatAdapter extends ArrayAdapter<KhoanTC>{

	public CaiDatAdapter(Context context, int custom_listview_caidat, List<KhoanTC> khoantc) {
		super(context,  R.layout.custom_listview_caidat, khoantc);
		// TODO Auto-generated constructor stub
		// bi sao z chi
		//ki z sao no bat em import z r gio lai k cho @@


	}
	@Override
	public View getView(int position, View view, ViewGroup parent){
		KhoanTC khoantc = getItem(position);
		LayoutInflater inFlater =  ((Activity)getContext()).getLayoutInflater();
		KhoanTCDao ktcDAO = new KhoanTCDao((Activity)getContext());
		View row = inFlater.inflate(R.layout.custom_listview_caidat, null,true);
		TextView txtName = (TextView)row.findViewById(R.id.txtName);
		TextView txtL = (TextView)row.findViewById(R.id.txtL);
		txtName.setText(khoantc.TenKhoan);
		txtL.setText(khoantc.KhoanGD);
		return row;
	}
}
