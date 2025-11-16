import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ChiTietPhieuNhapHang {
    private String maPhieu; 
    private String maDienThoai; 
    private int soLuong; 
    private double donGiaNhap;
    
    // Đã sửa lỗi: Tên constructor trùng với tên class (ChiTietPhieuNhapHang)
    public ChiTietPhieuNhapHang() {
    }
    
    public ChiTietPhieuNhapHang(String maPhieu, String maDienThoai, int soLuong, double donGiaNhap) {
        this.maPhieu = maPhieu;
        this.maDienThoai = maDienThoai;
        this.soLuong = soLuong;
        this.donGiaNhap = donGiaNhap;
    } 
    
    public double tinhThanhTien() {
        return this.soLuong * this.donGiaNhap;
    }

    // --- Getters và Setters ---
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public String getMaDienThoai() { return maDienThoai; }
    public void setMaDienThoai(String maDienThoai) { this.maDienThoai = maDienThoai; } // Đã sửa tên setter
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGiaNhap() { return donGiaNhap; }
    public void setDonGiaNhap(double donGiaNhap) { this.donGiaNhap = donGiaNhap; }
    
    // --- Phương thức nhập thông tin ---
    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap Ma Dien Thoai: ");
        this.maDienThoai = scanner.nextLine();
        // ... (phần nhập số lượng và đơn giá)
    }
    
    // --- Phương thức ghi File ---
    public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            // Định dạng: CT,maPhieu,maDT,soLuong,donGiaNhap
            writer.println(String.format("CT,%s,%s,%d,%.0f", 
                                         maPhieu, maDienThoai, soLuong, donGiaNhap));
        } catch (IOException e) {
            System.err.println("Loi khi ghi file ChiTietPhieuNhap: " + e.getMessage());
        }
    }

    // --- Bổ sung: Phương thức đọc File từ một dòng dữ liệu ---
    public void docFile(String duLieu) {
        // Định dạng mong muốn: CT,maPhieu,maDT,soLuong,donGiaNhap
        try {
            String[] parts = duLieu.split(",");
            if (parts.length >= 5 && parts[0].equals("CT")) {
                this.maPhieu = parts[1].trim();
                this.maDienThoai = parts[2].trim();
                this.soLuong = Integer.parseInt(parts[3].trim());
                this.donGiaNhap = Double.parseDouble(parts[4].trim());
            } 
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Loi phan tich du lieu ChiTietPhieuNhap: " + duLieu);
        }
    }
    
    @Override
    public String toString() {
        return String.format("   -> [Ma Phieu: %s] MaDT: %s | SL: %d | DG: %,.0f | TT: %,.0f", 
                             maPhieu, maDienThoai, soLuong, donGiaNhap, tinhThanhTien());
    }
}