import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DanhSachNhaCungCap_Array {
    private NhaCungCap[] danhSach;
    private int soLuongHienTai;
    private final int KICH_THUOC_BAN_DAU = 10;
    private final String TEN_FILE = "NCC.txt"; 

    public DanhSachNhaCungCap_Array() {
        this.danhSach = new NhaCungCap[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        docTatCaTuFile(); 
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- 1. THÊM (CREATE) ---
    public void themNhaCungCap(Scanner scanner) {
        tangKichThuoc();
        NhaCungCap ncc = new NhaCungCap();
        ncc.nhapThongTin(scanner);
        if (timTheoMa(ncc.getMaNCC()) != null) {
            System.out.println("❌ Lỗi: Mã Nhà Cung Cấp đã tồn tại.");
            return;
        }
        danhSach[soLuongHienTai] = ncc;
        soLuongHienTai++;
        System.out.println("✅ Thêm Nhà Cung Cấp thành công.");
        ghiTatCaVaoFile(); 
    }
    
    // --- 2. TÌM KIẾM/HIỂN THỊ (READ) ---
    public NhaCungCap timTheoMa(String maNCC) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNCC().equalsIgnoreCase(maNCC)) {
                return danhSach[i];
            }
        }
        return null;
    }
    
    public void hienThiTatCa() {
        if (soLuongHienTai == 0) {
            System.out.println("Danh sách Nhà Cung Cấp trống.");
            return;
        }
        System.out.println("===== DANH SÁCH NHÀ CUNG CẤP (" + soLuongHienTai + " mục) =====");
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null)
                System.out.println(danhSach[i].toString());
        }
        System.out.println("===================================");
    }

    // --- 3. SỬA (UPDATE) ---
    public void suaNhaCungCap(Scanner scanner) {
        System.out.print("Nhập Mã NCC cần sửa: ");
        String maNCC = scanner.nextLine();
        NhaCungCap nccCanSua = timTheoMa(maNCC);

        if (nccCanSua == null) {
            System.out.println("❌ Không tìm thấy Nhà Cung Cấp với mã: " + maNCC);
            return;
        }

        System.out.println("Tìm thấy: " + nccCanSua.toString());
        
        System.out.print("Nhập Tên mới (Enter để giữ nguyên: " + nccCanSua.getTenNCC() + "): ");
        String tenMoi = scanner.nextLine();
        if (!tenMoi.trim().isEmpty()) {
            nccCanSua.setTenNCC(tenMoi);
        }
        
        System.out.print("Nhập SĐT mới (Enter để giữ nguyên: " + nccCanSua.getSdt() + "): ");
        String sdtMoi = scanner.nextLine();
        if (!sdtMoi.trim().isEmpty()) {
            // ĐÃ SỬA LỖI ĐỎ: Dùng nccCanSua
            nccCanSua.setSdt(sdtMoi); 
        }
        
        System.out.println("✅ Sửa Nhà Cung Cấp thành công.");
        ghiTatCaVaoFile(); 
    }
    
    // --- 4. XÓA (DELETE) ---
    public void xoaNhaCungCap(Scanner scanner) {
        System.out.print("Nhập Mã NCC cần xóa: ");
        String maNCC = scanner.nextLine();
        
        int viTriCanXoa = -1;
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNCC().equalsIgnoreCase(maNCC)) {
                viTriCanXoa = i;
                break;
            }
        }
        
        if (viTriCanXoa != -1) {
            for (int i = viTriCanXoa; i < soLuongHienTai - 1; i++) {
                danhSach[i] = danhSach[i + 1];
            }
            soLuongHienTai--;
            danhSach[soLuongHienTai] = null; 

            System.out.println("✅ Đã xóa Nhà Cung Cấp với mã: " + maNCC);
            ghiTatCaVaoFile(); 
        } else {
            System.out.println("❌ Không tìm thấy Nhà Cung Cấp với mã: " + maNCC);
        }
    }
    
    // --- 5. GHI FILE (SAVE) ---
    public void ghiTatCaVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEN_FILE, false))) {
            for (int i = 0; i < soLuongHienTai; i++) {
                if (danhSach[i] != null) // Kiểm tra null trước khi gọi hàm
                    danhSach[i].ghiFile(writer); 
            }
        } catch (IOException e) {
             System.err.println("❌ Lỗi khi ghi file NCC: " + e.getMessage());
        }
    }
    
    // --- 6. ĐỌC FILE (LOAD) ---
    public void docTatCaTuFile() {
        try (Scanner fileScanner = new Scanner(new File(TEN_FILE))) {
            while (fileScanner.hasNextLine()) {
                String duLieu = fileScanner.nextLine();
                tangKichThuoc(); 
                
                NhaCungCap ncc = new NhaCungCap();
                ncc.docFile(duLieu); 

                if (ncc.getMaNCC() != null && !ncc.getMaNCC().trim().isEmpty() && timTheoMa(ncc.getMaNCC()) == null) {
                    danhSach[soLuongHienTai] = ncc;
                    soLuongHienTai++;
                }
            }
        } catch (IOException e) {
             if (soLuongHienTai == 0) {
                 System.out.println("ℹ️ File NCC chưa tồn tại. Bắt đầu với danh sách trống.");
             } else {
                 System.err.println("❌ Lỗi khi đọc file NCC: " + e.getMessage());
             }
        }
    }
}