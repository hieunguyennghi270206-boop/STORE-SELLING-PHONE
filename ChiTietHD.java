import java.util.Scanner;
import java.io.*;

public class ChiTietHD {
    private String maHD;
    private String maDT;
    private int soLuong;
    private double donGia;

    public ChiTietHD() {}

    public ChiTietHD(String maHD, String maDT, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maDT = maDT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHD (ChiTietHD cthd){
        this.maHD = cthd.maHD;
        this.maDT = cthd.maDT;
        this.soLuong = cthd.soLuong;
        this.donGia = cthd.donGia;
    }

    public String getMaHD() { return maHD; }
    public String getMaDT() { return maDT; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }

    public void setMaHD(String maHD) { this.maHD = maHD; }
    public void setMaDT(String maDT) { this.maDT = maDT; }
    public void setSoluong(int soLuong) { this.soLuong = soLuong; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public double thanhTien() {
        return soLuong*donGia;
    }

    public void nhap(Scanner sc) {
        System.out.print("Nhap ma HD: ");
        maHD = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-10s %-10d %-15.2f %-15.2f\n",
                                maHD, maDT, soLuong, donGia, thanhTien());
    }

    
}
