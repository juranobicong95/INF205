package fpoly_model;

import java.io.Serializable;

public class GiaoDich implements Serializable{
	public String MaGD;
	public String Ngay;
	public Float Tien;
	public String LoaiGD;
	public String KhoanGD;
	public String MoTa;
	public GiaoDich(String maGD, String ngay, Float tien, String loaiGD,
			String khoanGD, String moTa) {
		super();
		MaGD = maGD;
		Ngay = ngay;
		Tien = tien;
		LoaiGD = loaiGD;
		KhoanGD = khoanGD;
		MoTa = moTa;
	}
	@Override
	public String toString() {
		return "KhoanTC [MaGD=" + MaGD + ", Ngay=" + Ngay + ", Tien=" + Tien
				+ ", LoaiGD=" + LoaiGD + ", KhoanGD=" + KhoanGD + ", MoTa="
				+ MoTa + "]";
	}
}
