import java.util.Scanner;
import java.util.Arrays;
// (Giả định các import liên quan đến File đã được thêm nếu cần)

public class DanhSachPhieuNhapHang_Array { 
    private PhieuNhapHang[] danhSach;
    private int soLuongHienTai;
    private final int KICH_THUOC_BAN_DAU = 10;
    
    private DanhSachChiTietPhieuNhapHang_Array dsChiTiet; 

    public DanhSachPhieuNhapHang_Array(DanhSachChiTietPhieuNhapHang_Array dsChiTiet) {
        this.danhSach = new PhieuNhapHang[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        this.dsChiTiet = dsChiTiet; 
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- 1. THÊM (CREATE) ---
    public void themPhieuNhap(Scanner scanner) {
        tangKichThuoc();
        PhieuNhapHang pnh = new PhieuNhapHang();
        pnh.nhapThongTin(scanner);
        if (timTheoMa(pnh.getMaPhieu()) != null) {
            System.out.println("❌ Lỗi: Mã Phiếu Nhập đã tồn tại.");
            return;
        }
        this.danhSach[soLuongHienTai] = pnh;
        soLuongHienTai++;
        System.out.println("✅ Thêm Phiếu Nhập thành công.");
    }

    // --- 2. TÌM KIẾM/HIỂN THỊ (READ) ---
    public PhieuNhapHang timTheoMa(String maPhieu) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                return danhSach[i];
            }
        }
        return null;
    }
    
    public void hienThiTatCa() {
        if (soLuongHienTai == 0) {
            System.out.println("Danh sách Phiếu Nhập trống.");
            return;
        }
        System.out.println("===== DANH SÁCH PHIẾU NHẬP HÀNG (" + soLuongHienTai + " mục) =====");
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null) {
                System.out.println(danhSach[i].toString());
                dsChiTiet.hienThiChiTiet(danhSach[i].getMaPhieu()); 
            }
        }
        System.out.println("====================================");
    }

    // --- 3. SỬA (UPDATE) ---
    public void suaPhieuNhap(Scanner scanner) {
        System.out.print("Nhập Mã Phiếu cần sửa: ");
        String maPhieu = scanner.nextLine();
        PhieuNhapHang pnhCanSua = timTheoMa(maPhieu);

        if (pnhCanSua == null) {
            System.out.println("❌ Không tìm thấy Phiếu Nhập với mã: " + maPhieu);
            return;
        }
        // ... (Logic sửa thông tin)
        System.out.println("✅ Sửa Phiếu Nhập thành công.");
    }

    // --- 4. XÓA (DELETE) ---
    public void xoaPhieuNhap(Scanner scanner) {
        System.out.print("Nhập Mã Phiếu cần xóa: ");
        String maPhieu = scanner.nextLine();
        int viTriCanXoa = -1;
        
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
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
            
            dsChiTiet.xoaTatCaTheoMaPhieu(maPhieu); 

            System.out.println("✅ Đã xóa Phiếu Nhập với mã: " + maPhieu);
        } else {
            System.out.println("❌ Không tìm thấy Phiếu Nhập với mã: " + maPhieu);
        }
    }
    
    // --- MENU VÀ HÀM MAIN ---
    private static void hienThiMenu() {
        System.out.println("\n--- MENU QUẢN LÝ TỔNG HỢP ---");
        System.out.println("=== NHÀ CUNG CẤP (NCC) ===");
        System.out.println("1. Thêm NCC        | 2. Hiển thị NCC | 3. Sửa NCC | 4. Xóa NCC");
        System.out.println("=== PHIẾU NHẬP (PNH) ===");
        System.out.println("5. Thêm PNH        | 6. Hiển thị PNH | 7. Sửa PNH | 8. Xóa PNH");
        System.out.println("=== CHI TIẾT ===");
        System.out.println("9. Sửa Chi Tiết PNH (SL/DG)");
        System.out.println("0. Thoát chương trình");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Khởi tạo các đối tượng quản lý đã được đổi tên
        DanhSachChiTietPhieuNhapHang_Array dsCT = new DanhSachChiTietPhieuNhapHang_Array();
        DanhSachPhieuNhapHang_Array dsPNH = new DanhSachPhieuNhapHang_Array(dsCT); 
        DanhSachNhaCungCap_Array dsNCC = new DanhSachNhaCungCap_Array();

        int luaChon = -1;
        
        do {
            hienThiMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            try {
                luaChon = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lựa chọn phải là số. Vui lòng thử lại.");
                scanner.nextLine();
                luaChon = -1; 
            }

            switch (luaChon) {
                // NHÀ CUNG CẤP
                case 1: dsNCC.themNhaCungCap(scanner); break;
                case 2: dsNCC.hienThiTatCa(); break;
                case 3: dsNCC.suaNhaCungCap(scanner); break;
                case 4: dsNCC.xoaNhaCungCap(scanner); break;
                
                // PHIẾU NHẬP HÀNG
                case 5: dsPNH.themPhieuNhap(scanner); break;
                case 6: dsPNH.hienThiTatCa(); break;
                case 7: dsPNH.suaPhieuNhap(scanner); break;
                case 8: dsPNH.xoaPhieuNhap(scanner); break;

                // CHI TIẾT PHIẾU NHẬP
                case 9: dsCT.suaChiTiet(scanner); break; 
                
                case 0:
                    System.out.println("Kết thúc chương trình. Tạm biệt!");
                    break;
                default:
                    if (luaChon != -1) {
                         System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    }
            }
            System.out.println("--------------------------------------------------");
        } while (luaChon != 0);
        scanner.close();
    }
}