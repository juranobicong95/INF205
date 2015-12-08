package fpoly_model;

import java.io.Serializable;

public class ThongKe implements Serializable{
	public String TenKhoan;
	public int SoTien;
	public ThongKe(String tenKhoan, int soTien) {
		super();
		TenKhoan = tenKhoan;
		SoTien = soTien;
	}
}
