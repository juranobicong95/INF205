package com.edu.fpt.demoparse;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import Adapter.CaiDatAdapter;
import fpoly_model.KhoanTC;

public class ThuChiActivity extends Activity {
	//public final static int REQUEST_CODE = 10;
	SQLiteDatabase db;
	Button GiaoDich, clickThem, clickSaoke;
	EditText MaGD, ngayGD, tienGD, motaGD;
	Spinner KhoanGD, loaiGD;
	TextView setTM, setTD, setTK, setSD;
	String TM, TD, TK;
	int SD,inThu,intChi;
	//GiaoDichDao adc;
	//KhoanTCDao ktc;
	//LoaiGDDAO lgd;
	ArrayList<String> lstKhoan, lstLoaiGD;
	ArrayAdapter<String> adapter, adapter1;
	ListView lstShowDate;
	//ArrayList<fpoly_model.GiaoDich> lstGD;
	//GiaoDichAdapter adapetGD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thuchi_layout);
	//	adc = new GiaoDichDao(this);
	//	ktc = new KhoanTCDao(this);
	//	lgd = new LoaiGDDAO(this);
		setTK = (TextView) findViewById(R.id.txtTK);
		setTM = (TextView) findViewById(R.id.txtTM);
		setTD = (TextView) findViewById(R.id.txtTD);
		setSD = (TextView) findViewById(R.id.txtSD);
		setMoney();
		clickSaoke = (Button) findViewById(R.id.btnSaoKe);
		clickSaoke.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText NgayBD = (EditText)findViewById(R.id.edtNgayBD);
				EditText NgayKT = (EditText)findViewById(R.id.edtNgayKT);
				String txtNgayBD = NgayBD.getText().toString();
				String txtNgayKT = NgayKT.getText().toString();
				if(txtNgayBD.trim().equalsIgnoreCase("")){
					Toast.makeText(ThuChiActivity.this, "Ngày Bắt Đầu không để trống",
							Toast.LENGTH_LONG).show();
				}else if(txtNgayKT.trim().equalsIgnoreCase("")){
					Toast.makeText(ThuChiActivity.this, "Ngày Kết thúc không để trống! Nếu tra trong ngày hãy nhập cùng ngày",
							Toast.LENGTH_LONG).show();
				}else if(txtNgayBD != null && txtNgayKT != null){
					try {
	//					Intent intent = new Intent(ThuChiActivity.this, Ngay_Sao_Ke_2.class);
	//					Bundle bunle = new Bundle();
	//					bunle.putString("NgayB", txtNgayBD);
	//					bunle.putString("NgayKT", txtNgayKT);
	//					intent.putExtra("changed", bunle);
	//					startActivity(intent);
					} catch (Exception e) {
						Toast.makeText(ThuChiActivity.this, "Ngày Tháng Nhập Không đúng",
								Toast.LENGTH_LONG).show();
					}
					
				}
			}
		});
	//	clickGioiThieu.setOnClickListener(new View.OnClickListener() {
			
	//		@Override
	//		public void onClick(View v) {
	//			Intent intn = new Intent(ThuChiActivity.this, InfoActivity.class);
	//			startActivity(intn);
				
	//		}
	//	});
	}
	public void GiaoDich(View v) {
		final Dialog dialogGD = new Dialog(ThuChiActivity.this);
		dialogGD.setTitle("Thêm Giao Dịch");
		dialogGD.setContentView(R.layout.themiaodich_dialog);
		MaGD = (EditText) dialogGD.findViewById(R.id.edtMaGD);
		ngayGD = (EditText) dialogGD.findViewById(R.id.edtNgay);
		tienGD = (EditText) dialogGD.findViewById(R.id.edtTien);
		KhoanGD = (Spinner) dialogGD.findViewById(R.id.spnKhoan);
		loaiGD = (Spinner) dialogGD.findViewById(R.id.spnLoai);
		motaGD = (EditText) dialogGD.findViewById(R.id.edtMota);
		lstLoaiGD = new ArrayList<String>();
		lstLoaiGD.add("Thu");
		lstLoaiGD.add("Chi");
		lstLoaiGD.add("Tín Dụng");
		lstLoaiGD.add("Tiết Kiệm");
		adapter1 = new ArrayAdapter<String>(ThuChiActivity.this,
				android.R.layout.simple_list_item_1, lstLoaiGD);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		loaiGD.setAdapter(adapter1);
		lstKhoan = new ArrayList<String>();
	//	lstKhoan = ktc.getTenKhoan();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> listKhoan, com.parse.ParseException e) {
				if (e == null) {
					lstKhoan.clear();
					for (ParseObject ktc : listKhoan) {
						lstKhoan.add(ktc.getString("KhoanGD"));
						// Toast.makeText(CaiDatActivity.this, listTC.size()+"",Toast.LENGTH_LONG).show();
					}
				} else {

				}
				adapter = new ArrayAdapter<String>(ThuChiActivity.this,
						android.R.layout.simple_list_item_1, lstKhoan);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				KhoanGD.setAdapter(adapter);
			}
		});
		clickThem = (Button) dialogGD.findViewById(R.id.btnThem);
		clickThem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String txtMaGD, txtNgayGD, txtKhoanGD, txtLoaiGD, txtMoTaGD;
				txtMaGD = MaGD.getText().toString();
				txtNgayGD = ngayGD.getText().toString();
				final String txtTienGD = tienGD.getText().toString();
				txtKhoanGD = KhoanGD.getSelectedItem().toString();
				txtLoaiGD = loaiGD.getSelectedItem().toString();
				txtMoTaGD = motaGD.getText().toString();
				if (txtMaGD.trim().equalsIgnoreCase("")) {
					Toast.makeText(ThuChiActivity.this, "MaGD Trống!",
							Toast.LENGTH_LONG).show();
				} else if (txtNgayGD.trim().equalsIgnoreCase("")) {
					Toast.makeText(ThuChiActivity.this,
							"Ngày Giao dịch Trống!", Toast.LENGTH_LONG).show();
				} else if (txtTienGD.trim().equalsIgnoreCase("")) {
					Toast.makeText(ThuChiActivity.this,
							"Tiền Giao Dịch Trống!", Toast.LENGTH_LONG).show();
				} else if (txtMoTaGD.trim().equalsIgnoreCase("")) {
					Toast.makeText(ThuChiActivity.this, "Mô tả Trống!",
							Toast.LENGTH_LONG).show();
				}
				try {
					ParseQuery<ParseObject> query = ParseQuery.getQuery("GiaoDich");
					query.whereEqualTo("MaGD", txtMaGD);
					query.findInBackground(new FindCallback<ParseObject>() {
						@Override
						public void done(List<ParseObject> objects, com.parse.ParseException e) {
							if (objects.size() > 0) {
								Toast.makeText(ThuChiActivity.this, "Mã giao dịch đã tồn tại!", Toast.LENGTH_SHORT).show();

							} else {
								//addData(txtGetMa, txtGetTen, txtNhom);
								ParseObject GiaoDich = new ParseObject("GiaoDich");
								GiaoDich.put("MaGD", txtKhoanGD);
								GiaoDich.put("Ngay", txtNgayGD);
								GiaoDich.put("Tien", txtTienGD);
								GiaoDich.put("LoaiGD", txtLoaiGD);
								GiaoDich.put("KhoanGd", txtKhoanGD);
								GiaoDich.put("Mota", txtMoTaGD);
								GiaoDich.saveInBackground();
								Toast.makeText(ThuChiActivity.this, "Thành Công!", Toast.LENGTH_LONG).show();
							}
						}
					});
					dialogGD.dismiss();
					setMoney();
				} catch (Exception e) {
					Toast.makeText(ThuChiActivity.this, "Them That Bai!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		dialogGD.show();
	}
	public void setMoney(){
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("GiaoDich");
		query2.whereEqualTo("LoaiGD", "Tindung");
		query2.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					int totalTD = 0;
					for(ParseObject getTien : objects){
						totalTD += getTien.getInt("Tien");
					}
					TD = String.valueOf(totalTD);
					if (TD == null) {
						TD = "0";
					}
					setTD.setText(TD);
				} else {
				}
			}
		});
		ParseQuery<ParseObject> query4 = ParseQuery.getQuery("GiaoDich");
		query4.whereEqualTo("LoaiGD", "Tietkiem");
		query4.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					int totalTD = 0;
					for(ParseObject getTien : objects){
						totalTD += getTien.getInt("Tien");
					}
					TK = String.valueOf(totalTD);
					if (TK == null) {
						TK = "0";
					}
					setTK.setText(TK);
				} else {
				}
			}
		});
		ParseQuery<ParseObject> query5 = ParseQuery.getQuery("GiaoDich");
		query5.whereEqualTo("LoaiGD", "Thu");
		query5.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					int totalTD = 0;
					for (ParseObject getTien : objects) {
						totalTD += getTien.getInt("Tien");
					}
					inThu = totalTD;

				} else {
				}
			}
		});
		ParseQuery<ParseObject> query6 = ParseQuery.getQuery("GiaoDich");
		query6.whereEqualTo("LoaiGD", "Chi");
		query6.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					int totalTD = 0;
					for (ParseObject getTien : objects) {
						totalTD += getTien.getInt("Tien");
					}
					intChi = totalTD;
					TM = String.valueOf(inThu - intChi);
					if (TM == null) {
						TM = "0";
					}
					setTM.setText(TM);
				} else {
				}
			}
		});
		ParseQuery<ParseObject> query8 = ParseQuery.getQuery("GiaoDich");
		query8.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					int totalTD = 0;
					int totalTK = 0;
					int totalC = 0;
					int totalT = 0;
					for (ParseObject getTien : objects) {
						if (getTien.getString("LoaiGD") == "Tindung") {
							totalTD += getTien.getInt("Tien");
						}
						if (getTien.getString("LoaiGD") == "Tietkiem") {
							totalTK += getTien.getInt("Tien");
						}
						if (getTien.getString("LoaiGD") == "Chi") {
							totalC += getTien.getInt("Tien");
						}
						if (getTien.getString("LoaiGD") == "Thu") {
							totalT += getTien.getInt("Tien");
						}
					}
					 Toast.makeText(ThuChiActivity.this, "" + totalT + " "+ totalC, Toast.LENGTH_LONG).show();
					SD = totalT - totalC - totalTD - totalTK;
					setSD.setText(String.valueOf(SD));
				} else {
				}
			}
		});
	}
	
}
