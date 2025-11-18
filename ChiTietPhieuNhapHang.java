import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ChiTietPhieuNhapHang {
    private String maPhieu; 
    private String maDienThoai; 
    private int soLuong; 
    private double donGiaNhap;
    
    // Constructor không tham số (Bắt buộc phải có cho hàm docTatCaTuFile)
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
    public void setMaDienThoai(String maDienThoai) { this.maDienThoai = maDienThoai; } 
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGiaNhap() { return donGiaNhap; }
    public void setDonGiaNhap(double donGiaNhap) { this.donGiaNhap = donGiaNhap; }
    
    // --- Phương thức nhập thông tin ---
    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap Ma Dien Thoai: ");
        this.maDienThoai = scanner.nextLine();
       while (true) {
            System.out.print("Nhap So Luong: ");
            try {
                // Đọc input dưới dạng String, sau đó chuyển sang int
                this.soLuong = Integer.parseInt(scanner.nextLine());
                if (this.soLuong < 0) {
                    System.out.println("(!) So Luong phai la so khong am. Vui long nhap lai.");
                    continue; // Quay lại đầu vòng lặp
                }
                break; // Thoát vòng lặp nếu nhập thành công và hợp lệ
            } catch (NumberFormatException e) {
                // Bắt lỗi nếu người dùng nhập chữ/kí tự đặc biệt thay vì số
                System.out.println("(!) Loi: So Luong phai la mot so nguyen. Vui long nhap lai.");
            }
        }
        while (true) {
            System.out.print("Nhap Don Gia: ");
            try {
                // Đọc input dưới dạng String, sau đó chuyển sang double
                this.donGiaNhap = Double.parseDouble(scanner.nextLine());
                if (this.donGiaNhap < 0) {
                    System.out.println("(!) Don Gia phai la so khong am. Vui long nhap lai.");
                    continue; // Quay lại đầu vòng lặp
                }
                break; // Thoát vòng lặp nếu nhập thành công và hợp lệ
            } catch (NumberFormatException e) {
                // Bắt lỗi nếu người dùng nhập chữ/kí tự đặc biệt thay vì số
                System.out.println("(!) Loi: Don Gia phai la mot so thuc. Vui long nhap lai.");
            }
        }
    }
    
    // --- 🚨 PHƯƠNG THỨC GHI FILE CẦN THIẾT CHO LỚP QUẢN LÝ (Đã thêm) ---
    public void ghiFile(PrintWriter writer) {
        // Định dạng: CT,maPhieu,maDT,soLuong,donGiaNhap
        writer.println(String.format("CT,%s,%s,%d,%.0f", 
                                     maPhieu, maDienThoai, soLuong, donGiaNhap));
    }
    
    // --- Phương thức ghi File cũ (Giữ nguyên) ---
    public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            ghiFile(writer); // Gọi phương thức ghi file mới để tránh lặp code
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