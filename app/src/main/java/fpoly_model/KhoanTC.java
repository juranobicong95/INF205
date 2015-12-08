package fpoly_model;

import java.io.Serializable;

public class KhoanTC implements Serializable{
	public String KhoanGD;
	public String TenKhoan;
	public String Nhom;
	public String Group;
	public KhoanTC(){

	}
	public KhoanTC(String khoanGD, String tenKhoan, String nhom,String Group) {
		super();
		KhoanGD = khoanGD;
		TenKhoan = tenKhoan;
		Nhom = nhom;
		Group = Group;
	}
	@Override
	public String toString() {
		return "KhoanTC [KhoanGD=" + KhoanGD + ", TenKhoan=" + TenKhoan
				+ ", Nhom=" + Nhom +",Group = "+ Group +"]";
	}

}
