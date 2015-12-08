package Adapter;

import java.util.List;
import fpoly_model.KhoanTC;
import fpoly_model.ThongKe;
//import fpoly_sqlite.KhoanTCDao;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edu.fpt.demoparse.R;

public class ThongKeAdapter extends ArrayAdapter<ThongKe>{

	public ThongKeAdapter(Context context, List<ThongKe> thongKe) {
		super(context, R.layout.custom_listview_thongke, thongKe);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View view, ViewGroup parent){
		ThongKe thongKe = getItem(position);
		LayoutInflater inFlater =  ((Activity)getContext()).getLayoutInflater();
		View row = inFlater.inflate(R.layout.custom_listview_thongke, null,true);
		TextView txtNameTK = (TextView)row.findViewById(R.id.txtTenKhoanTK);
		TextView txtMoneyTK = (TextView)row.findViewById(R.id.txtTienThongKe);
		txtNameTK.setText(thongKe.TenKhoan);
		txtMoneyTK.setText(String.valueOf(thongKe.SoTien));
		return row;
	}
}
