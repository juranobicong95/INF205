package Adapter;

import java.util.ArrayList;
import java.util.List;

import fpoly_model.LoaiGD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoaiGDDAO {
	private SQLiteDatabase db;
	
	public LoaiGDDAO(Context context){
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		databaseHelper.getWritableDatabase();
	}
	public long insert(LoaiGD l){
		ContentValues values = new ContentValues();
		values.put("LoaiGD", l.LoaiGD);
		values.put("TenLoai", l.TenLoai);
		return db.insert("LoaiGD", null, values);
	}
	public int update(LoaiGD l){
		ContentValues values = new ContentValues();
		values.put("LoaiGD", l.LoaiGD);
		values.put("TenLoai", l.TenLoai);
		return db.update("LoaiGD", values, "LoaiGD=?", new String[]{l.LoaiGD});
	}
	public int delete(String name){
		return db.delete("LoaiGD", "LoaiGD=?", new String[]{name});
	}
	public List<LoaiGD> getLoaiGD(){
		String sql = "SELECT * FROM LoaiGD";
		return getLoaiGD();
	}
	public ArrayList<String> getTenLoaiGD(){
		ArrayList<String> Tm = new ArrayList<String>();
		// Khai báo cú pháp SQL để lấy danh sách
		String selectQury = "SELECT LoaiGD FROM LoaiGD";
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
}
