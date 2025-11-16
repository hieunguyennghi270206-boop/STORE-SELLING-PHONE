import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 

public class PhieuNhapHang {
    private String maPhieu;
    private String maNhanVien;
    private String maNhaCungCap;
    private String ngayNhap; 
    private double tongTienNhap;

    public PhieuNhapHang() {
    }

    public PhieuNhapHang(String maPhieu, String maNhanVien, String maNhaCungCap, String ngayNhap, double tongTienNhap) {
        this.maPhieu = maPhieu;
        this.maNhanVien = maNhanVien;
        this.maNhaCungCap = maNhaCungCap;
        this.ngayNhap = ngayNhap;
        this.tongTienNhap = tongTienNhap;
    }

    // --- Getters & Setters ---
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }
    public String getMaNhaCungCap() { return maNhaCungCap; }
    public void setMaNhaCungCap(String maNhaCungCap) { this.maNhaCungCap = maNhaCungCap; }
    public String getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(String ngayNhap) { this.ngayNhap = ngayNhap; }
    public double getTongTienNhap() { return tongTienNhap; }
    public void setTongTienNhap(double tongTienNhap) { this.tongTienNhap = tongTienNhap; }

    // --- Phương thức nhập thông tin ---
    public void nhapThongTin(Scanner scanner) {
        System.out.println("===== Nhập Thông Tin Phiếu Nhập =====");
        System.out.print("Nhap Ma Phieu: ");
        this.maPhieu = scanner.nextLine();
        System.out.print("Nhap Ma Nhan Vien: ");
        this.maNhanVien = scanner.nextLine();
        System.out.print("Nhap Ma Nha Cung Cap: ");
        this.maNhaCungCap = scanner.nextLine();
        System.out.print("Nhap Ngay Nhap (dd/mm/yyyy): ");
        this.ngayNhap = scanner.nextLine();
        System.out.print("Nhap Tong Tien Nhap (hoac 0): ");
        try {
            this.tongTienNhap = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            this.tongTienNhap = 0.0;
        }
    }
    
    // --- GHI FILE: Dùng PrintWriter (Ghi header vào stream đang mở) ---
    public void ghiFile(PrintWriter writer) {
        // Định dạng: PHIEU,maPhieu,maNhanVien,maNCC,ngayNhap,tongTienNhap
        writer.println(String.format("PHIEU,%s,%s,%s,%s,%.0f", 
                                            maPhieu, maNhanVien, maNhaCungCap, ngayNhap, tongTienNhap));
    }

    // --- GHI FILE: Dùng tên file (Mở/Đóng stream để ghi riêng lẻ) ---
    public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            ghiFile(writer); // Gọi phương thức ghi File bằng PrintWriter
        } catch (IOException e) {
            System.err.println("Loi khi ghi file PhieuNhapHang: " + e.getMessage());
        }
    }
    
    // --- ĐỌC FILE: Tạo đối tượng từ một dòng dữ liệu ---
    public void docFile(String duLieu) {
        // Định dạng mong muốn: PHIEU,maPhieu,maNhanVien,maNCC,ngayNhap,tongTienNhap
        try {
            String[] parts = duLieu.split(",");
            if (parts.length >= 6 && parts[0].equals("PHIEU")) {
                this.maPhieu = parts[1].trim();
                this.maNhanVien = parts[2].trim();
                this.maNhaCungCap = parts[3].trim();
                this.ngayNhap = parts[4].trim();
                this.tongTienNhap = Double.parseDouble(parts[5].trim());
            } 
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Loi phan tich du lieu PhieuNhapHang: " + duLieu);
        }
    }
    
    @Override
    public String toString() {
        return String.format("[Phiếu Nhập: %s] NV: %s | NCC: %s | Ngày: %s | Tổng: %,.0f VND", 
                             maPhieu, maNhanVien, maNhaCungCap, ngayNhap, tongTienNhap);
    }
}