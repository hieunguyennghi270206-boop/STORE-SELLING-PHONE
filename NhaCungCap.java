import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String sdt;
    private String diaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC, String tenNCC, String sdt, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    // --- Getters và Setters ---
    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }
    public String getTenNCC() { return tenNCC; }
    public void setTenNCC(String tenNCC) { this.tenNCC = tenNCC; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    // --- BỔ SUNG: Phương thức nhập thông tin từ Scanner ---
    public void nhapThongTin(Scanner scanner) {
        System.out.println("===== Nhập Thông Tin Nhà Cung Cấp =====");
        System.out.print("Nhap Ma NCC: ");
        this.maNCC = scanner.nextLine();
        System.out.print("Nhap Ten NCC: ");
        this.tenNCC = scanner.nextLine();
        System.out.print("Nhap SĐT: ");
        this.sdt = scanner.nextLine();
        System.out.print("Nhap Dia Chi: ");
        this.diaChi = scanner.nextLine();
        System.out.println("----------------------------------------");
    }

    // --- BỔ SUNG: Phương thức ghi File (dùng PrintWriter) ---
    public void ghiFile(PrintWriter writer) {
        // Định dạng file: NCC,maNCC,tenNCC,sdt,diaChi
        writer.println(String.format("NCC,%s,%s,%s,%s", 
                                     maNCC, tenNCC, sdt, diaChi));
    }

    // --- BỔ SUNG: Phương thức ghi File (dùng tên file) ---
    public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            ghiFile(writer);
        } catch (IOException e) {
            System.err.println("Loi khi ghi file NhaCungCap: " + e.getMessage());
        }
    }

    // --- BỔ SUNG: Phương thức đọc File từ một dòng dữ liệu ---
    public void docFile(String duLieu) {
        // Định dạng mong muốn: NCC,maNCC,tenNCC,sdt,diaChi
        try {
            String[] parts = duLieu.split(",");
            if (parts.length >= 5 && parts[0].equals("NCC")) {
                this.maNCC = parts[1].trim();
                this.tenNCC = parts[2].trim();
                this.sdt = parts[3].trim();
                this.diaChi = parts[4].trim();
            } 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Loi phan tich du lieu NhaCungCap: " + duLieu);
        }
    }

    // Hàm xuất thông tin nhà cung cấp
    public void xuat() {
        System.out.println("===== Thông Tin Nhà Cung Cấp =====");
        System.out.println("Mã NCC: " + maNCC);
        System.out.println("Tên NCC: " + tenNCC);
        System.out.println("SĐT: " + sdt);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("----------------------------------");
    }

    @Override
    public String toString() {
        return "Mã NCC: " + maNCC +
                ", Tên NCC: " + tenNCC +
                ", SĐT: " + sdt +
                ", Địa Chỉ: " + diaChi;
    }
}


