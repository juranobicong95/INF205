package Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fpoly_model.KhoanTC;

public class KhoanTCDao {
	private SQLiteDatabase db;
	public KhoanTCDao(Context context){
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}
	public long insert(KhoanTC k){
		ContentValues values = new ContentValues();
		values.put("KhoanGD", k.KhoanGD);
		values.put("TenKhoan",k.TenKhoan);
		values.put("Nhom", k.Nhom);
		return db.insert("KhoanTC", null, values);
	}
	public int update(KhoanTC k){
		ContentValues values = new ContentValues();
		values.put("KhoanGD", k.KhoanGD);
		values.put("TenKhoan",k.TenKhoan);
		values.put("Nhom", k.Nhom);
		return db.update("KhoanTC", values, "KhoanGD=?", new String[]{k.KhoanGD});
	}
	public int delete(String name){
		return db.delete("KhoanTC", "KhoanTC=?", new String[]{name});
	}
	public ArrayList<KhoanTC> getKhoanTC(){
		ArrayList<KhoanTC> sltKTC = new ArrayList();
		String sql = "SELECT TenKhoan, Nhom FROM KhoanTC";
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToFirst()){
			do{
				String TenKhoan = cursor.getString(0);
				String Nhom = cursor.getString(1);
				KhoanTC tc = new KhoanTC();
				tc.TenKhoan=TenKhoan;
				tc.Nhom=Nhom;
				sltKTC.add(tc);
			}while(cursor.moveToNext());
		}
		return sltKTC;
	}
	public ArrayList<String> getTenKhoan(){
		ArrayList<String> Tm = new ArrayList<String>();
		// Khai báo cú pháp SQL để lấy danh sách
		String selectQury = "SELECT KhoanGD FROM KhoanTC";
		// Tao đối tượng Curoso để đọc dữ liệu
		Cursor curso = db.rawQuery(selectQury, null);
		// Di chuyển con trỏ về vị trí đầu tiên
		if(curso.moveToFirst()){
			do{
			// Khai báo biến nhận giá trị đọc từ curso
				String name = curso.getString(0);
				Tm.add(name);
			}while(curso.moveToNext()); // Di chuyển con trỏ để đọc dữ liệu
		}
		return Tm;
		
	}
	public String getTenKhoanThuChiwhere2(String makhoan){
		String TenKhoanThuChi="";
		String selectQuery = "SELECT TenKhoan FROM KhoanTC where KhoanGD='"+makhoan+"'";	
		Cursor cursor = db.rawQuery(selectQuery,null);
		if(cursor.moveToFirst()){
			do{		
				TenKhoanThuChi = cursor.getString(0);	
			}while(cursor.moveToNext());
		}
		return TenKhoanThuChi;
	}
	public String getNhom(String TenKhoan){
		String Nhom = "";
		String SQL= "SELECT Nhom FROM KhoanTC Where TenKhoan = '"+ TenKhoan + "'";
		Cursor curso = db.rawQuery(SQL, null);
		if(curso.moveToFirst()){
			do{		
			Nhom = curso.getString(0);
			}while(curso.moveToNext());
		}
		return Nhom;
	}
	public ArrayList<KhoanTC> getAllByNhom(String Nhom){
		ArrayList<KhoanTC> ktc = new ArrayList();
		String SQL  = "SELECT TenKhoan, Nhom FROM KhoanTC Where Nhom = '"+ Nhom +"'";
		Cursor cursor = db.rawQuery(SQL, null);
		if(cursor.moveToFirst()){
			do{
				String name = cursor.getString(0);
				String nhom = cursor.getString(1);
				KhoanTC tc = new KhoanTC();
				tc.TenKhoan=name;
				tc.Nhom=nhom;
				ktc.add(tc);
			}while(cursor.moveToNext());
		}
		return ktc;
		
	}
	public String getMa(String ten){
		String ma = "";
		String SQL= "SELECT KhoanGD FROM KhoanTC Where TenKhoan = '"+ ten + "'";
		Cursor curso = db.rawQuery(SQL, null);
		if(curso.moveToFirst()){
			do{		
			ma = curso.getString(0);
			}while(curso.moveToNext());
		}
		return ma;
	}
}
