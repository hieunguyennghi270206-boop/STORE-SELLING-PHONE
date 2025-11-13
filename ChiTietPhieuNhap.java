import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class ChiTietPhieuNhap {
    private String maPhieu; 
    private String maDoiTuong; 
    private int soLuong; 
    private double donGiaNhap; 
    public ChiTietPhieuNhap() {
    }
    public ChiTietPhieuNhap(String maPhieu, String maDoiTuong, int soLuong, double donGiaNhap) {
        this.maPhieu = maPhieu;
        this.maDoiTuong = maDoiTuong;
        this.soLuong = soLuong;
        this.donGiaNhap = donGiaNhap;
    } 
public double tinhThanhTien() {
        return this.soLuong * this.donGiaNhap;
    }
public String getMaPhieu() {
        return maPhieu;
    }
public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }
public String getMaDoiTuong() {
        return maDoiTuong;
    }
public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }
public int getSoLuong() {
        return soLuong;
    }
public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
public double getDonGiaNhap() {
        return donGiaNhap;
    }
public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }
public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap Ma Doi Tuong: ");
        this.maDoiTuong = scanner.nextLine();
        
        System.out.print("Nhap So Luong:");
        try {
            this.soLuong = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException e) { this.soLuong = 0; }

        System.out.print("Nhap Don Gia Nhap:");
        try {
            this.donGiaNhap = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) { this.donGiaNhap = 0.0; }
    }
public void ghiFile(PrintWriter writer) {
        writer.println(String.format("CT,%s,%d,%.0f", 
                                     maDoiTuong, soLuong, donGiaNhap));
    }
public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            // Định dạng: CT,maPhieu,maDT,soLuong,donGiaNhap
            writer.println(String.format("CT,%s,%s,%d,%.0f", 
                                         maPhieu, maDoiTuong, soLuong, donGiaNhap));
            
            System.out.println("Ghi Chi Tiet (MaDT: " + maDoiTuong + ") vao file thanh cong.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi file ChiTietPhieuNhap: " + e.getMessage());
        }
    }
@Override
public String toString() {
        return String.format("   -> [Ma Phieu: %s] MaDT: %s | SL: %d | DG: %,.0f | TT: %,.0f", 
                             maPhieu, maDoiTuong, soLuong, donGiaNhap, tinhThanhTien());
    }
}
