package Adapter;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edu.fpt.demoparse.R;

import fpoly_model.GiaoDich;
import Adapter.KhoanTCDao;

public class GiaoDichAdapter extends ArrayAdapter<GiaoDich>{
	
	public GiaoDichAdapter(Context context, List<GiaoDich> giaodich) {
		super(context, R.layout.custom_listview_item, giaodich);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View view, ViewGroup parent){
		GiaoDich giaodichs = getItem(position);
		LayoutInflater inFlater =  ((Activity)getContext()).getLayoutInflater();
		View row = inFlater.inflate(R.layout.custom_listview_item, null,true);
		KhoanTCDao ktcDAO = new KhoanTCDao( ((Activity)getContext()));
		TextView tvMGD = (TextView)row.findViewById(R.id.txtMGD);
		TextView tvNGD = (TextView)row.findViewById(R.id.txtNGD);
		TextView tvTGD = (TextView)row.findViewById(R.id.txtTGD);
		TextView tvLGD = (TextView)row.findViewById(R.id.txtLGD);
		TextView tvKGD = (TextView)row.findViewById(R.id.txtKGD);
		TextView tvMTG = (TextView)row.findViewById(R.id.txtMTGD);
		tvMGD.setText(giaodichs.MaGD);
		tvNGD.setText(giaodichs.Ngay);
		tvTGD.setText("Số Tiền: "+String.valueOf(giaodichs.Tien));
		tvLGD.setText(giaodichs.LoaiGD);
		tvKGD.setText(ktcDAO.getTenKhoanThuChiwhere2(giaodichs.KhoanGD));
		tvMTG.setText(giaodichs.MoTa);
		return row;
	}
}
