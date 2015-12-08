package fpoly_model;

import java.io.Serializable;

public class LoaiGD implements Serializable{
	public String LoaiGD;
	public String TenLoai;
	@Override
	public String toString() {
		return "LoaiGD [LoaiGD=" + LoaiGD + ", TenLoai=" + TenLoai + "]";
	}
	public LoaiGD(String loaiGD, String tenLoai) {
		super();
		LoaiGD = loaiGD;
		TenLoai = tenLoai;
	}
}
