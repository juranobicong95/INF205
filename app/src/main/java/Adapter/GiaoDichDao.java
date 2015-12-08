package Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fpoly_model.GiaoDich;
import fpoly_model.ThongKe;

public class GiaoDichDao {
	private SQLiteDatabase db;
	public GiaoDichDao(Context context){
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}
	
	public long insert(GiaoDich g){
		ContentValues values = new ContentValues();
		values.put("MaGD", g.MaGD);
		values.put("Ngay", g.Ngay);
		values.put("KhoanGD",g.KhoanGD);
		values.put("LoaiGD",g.LoaiGD);
		values.put("Tien",g.Tien);
		values.put("MoTa",g.MoTa);
		return db.insert("GiaoDich", null, values);		
	}
	
	public int update(GiaoDich g){
		ContentValues values = new ContentValues();
		values.put("MaGD", g.MaGD);
		values.put("Ngay", g.Ngay);
		values.put("KhoanGD",g.KhoanGD);
		values.put("LoaiGD",g.LoaiGD);
		values.put("Tien",g.Tien);
		values.put("MoTa",g.MoTa);
		return db.update("GiaoDich", values, "MaGD=?", new String[]{g.MaGD});
	}
	public int delete(String name){
		return db.delete("GiaoDich", "MaGD=?", new String[]{name});
	}
	public List<GiaoDich> getGiaoDich(){
		String sql = "SELECT * FROM GiaoDich";
		return getGiaoDich();
	}
	public String TM(){
		String Tm = "";
		String GD1 = "'THU'";
		// Khai báo cú pháp SQL để lấy danh sách
		String selectQury = "SELECT SUM(Tien) FROM GiaoDich Where LoaiGD ="+ GD1;
		// Tao đối tượng Curoso để đọc dữ liệu
		Cursor curso = db.rawQuery(selectQury, null);
		// Di chuyển con trỏ về vị trí đầu tiên
		if(curso.moveToFirst()){
			do{
			// Khai báo biến nhận giá trị đọc từ curso
				
				String name = curso.getString(0);
				Tm = name;
			}while(curso.moveToNext()); // Di chuyển con trỏ để đọc dữ liệu
		}
		return Tm;
		
	}
	public String getTD(){
		String TD = "";
		String LoaiGD = "'TINDUNG'";
		String selectQuery = "SELECT SUM(Tien) FROM GiaoDich Where LoaiGD =" + LoaiGD;
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				String name = cursor.getString(0);
				TD = name;
			}while(cursor.moveToNext());
		}
		return TD;
	}
	public String getTK(){
		String TD = "";
		String LoaiGD = "'TIETKIEM'";
		String selectQuery = "SELECT SUM(Tien) FROM GiaoDich Where LoaiGD =" + LoaiGD;
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				String name = cursor.getString(0);
				TD = name;
			}while(cursor.moveToNext());
		}
		return TD;
	}
	public ArrayList<GiaoDich> getDate(String date){
		ArrayList<GiaoDich> lstDate = new ArrayList<GiaoDich>();
		String sql = "SELECT * FROM GiaoDich WHERE Date(Ngay) like '"+ date+"'";
		Cursor curso = db.rawQuery(sql, null);
		if(curso.moveToFirst()){
			do{
				String MaGD = curso.getString(0);
				String ngayGD = curso.getString(1);
				Float tien = Float.parseFloat(curso.getString(2));
				String loaiGD = curso.getString(3);
				String khoanGD = curso.getString(4);
				String moTa = curso.getString(5);
				GiaoDich GD = new GiaoDich(MaGD, ngayGD, tien, loaiGD, khoanGD, moTa);
				lstDate.add(GD);
			}while(curso.moveToNext());
			
		}
		return lstDate;	
	}
	public ArrayList<GiaoDich> getDate2(String date,String date2){
		ArrayList<GiaoDich> lstDate = new ArrayList<GiaoDich>();
		String sql = "SELECT * FROM GiaoDich where Date(Ngay) >= Date('"+ date +"') and Date(Ngay) <= Date('"+date2+"')";
		Cursor curso = db.rawQuery(sql, null);
		if(curso.moveToFirst()){
			do{
				String MaGD = curso.getString(0);
				String ngayGD = curso.getString(1);
				Float tien = Float.parseFloat(curso.getString(2));
				String loaiGD = curso.getString(3);
				String khoanGD = curso.getString(4);
				String moTa = curso.getString(5);
				GiaoDich GD = new GiaoDich(MaGD, ngayGD, tien, loaiGD, khoanGD, moTa);
				lstDate.add(GD);
			}while(curso.moveToNext());
			
		}
		return lstDate;	
	}
	public String getLoaiGDbyKhoanGD(String KhoanGD){
		String LoaiGD = "";
		String sql = "SELECT LoaiGD FROM GiaoDich where KhoanGD ='"+KhoanGD+"'";
		Cursor curso = db.rawQuery(sql, null);
		if(curso.moveToFirst()){
			do{
				String name = curso.getString(0);
				LoaiGD = name;
			}while(curso.moveToNext());
		}
		return LoaiGD;
	}
	public ArrayList<ThongKe> ThongKe_Khoanchi(String NgayBD, String NgayKT){
		ArrayList<ThongKe> list = new ArrayList<ThongKe>();
		
		String sql="Select KhoanTC.[TenKhoan], sum(GiaoDich.[Tien]) from GiaoDich, KhoanTC "+
				"Where GiaoDich.[KhoanGD] = KhoanTC.[KhoanGD] "+
				"AND GiaoDich.[LoaiGD] = 'CHI' "+
				"AND DATE(Ngay) BETWEEN DATE('"+NgayBD+"') AND DATE('"+NgayKT+"') "+
				"GROUP BY GiaoDich.[KhoanGD]";
		Cursor c=db.rawQuery(sql, null);
		while(c.moveToNext()){
			String name = c.getString(0);
			int Tien=c.getInt(1);
			ThongKe tk = new ThongKe(name, Tien);
			list.add(tk);
		}
		return list;
	}
	public ArrayList<ThongKe> ThongKe_Khoanthu(String NgayBD, String NgayKT){
		ArrayList<ThongKe> list = new ArrayList<ThongKe>();
		
		String sql="Select KhoanTC.[TenKhoan], sum(GiaoDich.[Tien]) from GiaoDich, KhoanTC "+
				"Where GiaoDich.[KhoanGD] = KhoanTC.[KhoanGD] "+
				"AND GiaoDich.[LoaiGD] = 'THU' "+
				"AND DATE(Ngay) BETWEEN DATE('"+NgayBD+"') AND DATE('"+NgayKT+"') "+
				"GROUP BY GiaoDich.[KhoanGD]";
		Cursor c=db.rawQuery(sql, null);
		while(c.moveToNext()){
			String name = c.getString(0);
			int Tien=c.getInt(1);
			ThongKe tk = new ThongKe(name, Tien);
			list.add(tk);
		}
		return list;
	}
}
