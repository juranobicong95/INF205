package com.edu.fpt.demoparse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThongKeActivity extends Activity {
	Button ngayBD, ngayKT;
	TextView txtNgayBD, txtNgayKT;
	ListView lstTThu, lstTChi;
	//ArrayList<ThongKe> lstThongTHU, lstThongCHI;
	//ThongKeAdapter adapterTHU,adapterCHI;
	int mYear, mMonth, mDay;
	static String currentDate;
	Calendar c;
	//GiaoDichDao gdDAO;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar myCalendar = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			txtNgayBD.setText(sdf.format(myCalendar.getTime()));
		}
	};
	DatePickerDialog.OnDateSetListener e = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			txtNgayKT.setText(sdf.format(myCalendar.getTime()));
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_ke);
	//	gdDAO = new GiaoDichDao(this);
		
		ngayBD = (Button)findViewById(R.id.btnNgatDB);
		ngayKT = (Button)findViewById(R.id.btnNgayKT);
		txtNgayBD = (TextView)findViewById(R.id.txtNgayBD);
		txtNgayKT = (TextView)findViewById(R.id.txtNgayKT);
		lstTChi = (ListView)findViewById(R.id.lstTongChi);
		lstTThu = (ListView)findViewById(R.id.lstTongThu);
		ngayBD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(ThongKeActivity.this, d, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		ngayKT.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(ThongKeActivity.this, e, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	}
	public void showList(View v){
		String txtGetBD = txtNgayBD.getText().toString();
		String txtGetKT = txtNgayKT.getText().toString();
		if(txtGetBD.trim().equalsIgnoreCase("")){
			Toast.makeText(ThongKeActivity.this, "Ngày Bắt đầuCòn Trống", Toast.LENGTH_LONG).show();
		}else if (txtGetKT.trim().equalsIgnoreCase("")){
			Toast.makeText(ThongKeActivity.this, "Ngày Kết Thúc Còn Trống! Nếu tìm trong 1 Ngày vui lòng nhập cùng ngày!", Toast.LENGTH_LONG).show();
		}else{
	//		lstThongTHU = gdDAO.ThongKe_Khoanthu(txtGetBD, txtGetKT);
	//		adapterTHU =  new ThongKeAdapter(ThongKeActivity.this, lstThongTHU);
	//		lstTThu.setAdapter(adapterTHU);
	//		lstThongCHI = gdDAO.ThongKe_Khoanchi(txtGetBD, txtGetKT);
	//		adapterCHI = new ThongKeAdapter(ThongKeActivity.this, lstThongCHI);
	//		lstTChi.setAdapter(adapterCHI);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.thong_ke, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
