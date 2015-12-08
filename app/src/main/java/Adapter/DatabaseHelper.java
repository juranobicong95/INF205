package Adapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	//Khai Báo Ten SQLite
	public static final String Data_Name = "Mob202_ASM_Database";
	
	//Khai báo bảng Thu chi
	public static final String Data_KhoanTC = "KhoanTC";
	public static final String Key_KhoanGD = "KhoanGD";
	public static final String Key_TenKhoan = "TenKhoan";
	public static final String Key_IDNhomK = "IDNhom";
	
	//Khai Bao Bảng loại GD
	public static final String Data_Loai = "LoaiGD";
	public static final String Key_Loai = "LoaiGD";
	public static final String Key_TenLoai = "TenLoai";
	
	//Khai Báo Bảng GD
	public static final String Data_GD = "GiaoDich";
	public static final String Key_Ngay = "Ngay";
	public static final String Key_Tien = "Tien";
	public static final String Key_LoaiGD = "LoaiGD";
	public static final String Key_KhoanGDK = "KhoanGD";
	public static final String Key_Mota = "MoTa";
	
	//Kiểm tra đường dẫn Database
	Context context;
	String duongDanDataBase = "";
	
	public DatabaseHelper(Context context) {
		super(context, Data_Name, null, 1);
		
		// Lấy Đường dẫn database trong emulator
//		this.context = context;
//		duongDanDataBase = context.getFilesDir().getParent()+ "/databases/" + Data_Name;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		// Xóa bảng khi tạo mới!
//				db.execSQL("DROP TABLE IF EXISTS " + Data_KhoanTC);
//				db.execSQL("DROP TABLE IF EXISTS " + Data_Loai);
//				db.execSQL("DROP TABLE IF EXISTS " + Data_GD);
//		// Khai báo cú pháp SQL tạo table Class
//				String CREATE_TABLE = "CREATE TABLE " + Data_Loai +" ("+ Key_Loai +" VARCHAR PRIMARY KEY  NOT NULL ,"+ Key_TenLoai +" VARCHAR)";
//		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
//	public SQLiteDatabase openDatabase(){
//		return SQLiteDatabase.openDatabase(duongDanDataBase, null, SQLiteDatabase.OPEN_READWRITE);
//		
//	}
	
//	// Hàm copy dữ liệu 
//	public void copyDatabase(){
//		try {
//			InputStream is = context.getAssets().open(Data_Name);
//			OutputStream os = new FileOutputStream(duongDanDataBase);
//			byte[] buffer = new byte[1024];
//			int length = 0;
//			while((length = is.read(buffer))>0){
//				os.write(buffer,0,length);
//			}
//			os.flush();
//			os.close();
//			is.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	// Hàm tạo kiểm tra tạo mới và thực hiện copy
//	public void createatabase(){
//		boolean kt = KiemTraDB();
//		if(kt){
//			Log.d("KetNoi", "Máy đã có database");
//		}else{
//			Log.d("KetNoi", "Máy chưa có database tiến hành copy dữ liệu");
//			//this.getWritableDatabase();
//			//copyDatabase();
//		}
//	}
//	
//	// kiểm tra csdl trong IDM
//	public boolean KiemTraDB(){
//		SQLiteDatabase kiemTraDB = null;
//		try {
//			kiemTraDB = SQLiteDatabase.openDatabase(duongDanDataBase, null, SQLiteDatabase.OPEN_READONLY);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(kiemTraDB != null){
//			kiemTraDB.close();
//		}
//		return kiemTraDB != null ? true : false;
//	}

}
