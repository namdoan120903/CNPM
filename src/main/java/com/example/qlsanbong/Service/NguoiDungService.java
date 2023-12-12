package com.example.qlsanbong.Service;

import com.example.qlsanbong.DTO.DonHangDTO;
import com.example.qlsanbong.DTO.NguoiDungDTO;
import com.example.qlsanbong.DTO.SanBongDTO;
import com.example.qlsanbong.DTO.SanPhamDTO;
import com.example.qlsanbong.Model.ChiTietDatSan;
import com.example.qlsanbong.Model.ChiTietDonHang;
import com.example.qlsanbong.Model.DonHang;
import com.example.qlsanbong.Model.NguoiDung;
import com.example.qlsanbong.Model.SanBong;
import com.example.qlsanbong.Model.SanPham;
import com.example.qlsanbong.Repository.ChiTietDatSanRepository;
import com.example.qlsanbong.Repository.ChiTietDonHangRepository;
import com.example.qlsanbong.Repository.DonHangRepository;
import com.example.qlsanbong.Repository.NguoiDungRepository;
import com.example.qlsanbong.Repository.SanBongRepository;
import com.example.qlsanbong.Repository.SanPhamRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService {
  @Autowired
  NguoiDungRepository nguoiDungRepository;
  @Autowired
  SanPhamRepository sanPhamRepository;
  @Autowired
  SanBongRepository sanBongRepository;
  @Autowired
  ChiTietDonHangRepository chiTietDonHangRepository;
  @Autowired
  ChiTietDatSanRepository chiTietDatSanRepository;
  @Autowired
  DonHangRepository donHangRepository;

  public void dangkyNguoiDung(NguoiDungDTO nguoiDungDTO){
    String sdt = nguoiDungDTO.getSdt();
    String password = nguoiDungDTO.getPassword();
    String name = nguoiDungDTO.getName();
    if(nguoiDungRepository.findBySdt(sdt) == null){
      NguoiDung nguoiDung = new NguoiDung();
      nguoiDung.setSdt(sdt);
      nguoiDung.setHoTen(name);
      nguoiDung.setPassword(password);
      nguoiDung.setVaiTro("User");
      nguoiDungRepository.save(nguoiDung);
    }
    else {
      throw new RuntimeException("Tên đăng nhâp đã được sử dụng");
    }
  }

  public int dangnhapNguoiDung(String sdt, String password){
    if(nguoiDungRepository.findBySdt(sdt) == null){
      return 0;
    }
    else {
      NguoiDung nguoiDung = nguoiDungRepository.findBySdt(sdt);
      if(nguoiDung.getPassword().equals(password)) {
        if(nguoiDung.getVaiTro() == "Admin") return 3;
        else return 1;
      }
      else return 2;
    }
  }
  public String thayDoiMatKhau(String sdt, String matKhauMoi){
    NguoiDung nguoiDung = nguoiDungRepository.findBySdt(sdt);
    nguoiDung.setPassword(matKhauMoi);
    nguoiDungRepository.save(nguoiDung);
    return "thay đổi mật khẩu thành công";
  }

  // xem danh sach san pham

  public List<SanPham> danhSachSanPham(){
    return sanPhamRepository.findAll();
  }
  public List<SanBong> danhsachSanBong(){
    return sanBongRepository.findAll();
  }
  // nhap don hang vao co so du lieu
  public String donHangNguoiDung(Long id, DonHangDTO donHangDTO){
    DonHang donHang = new DonHang();
    int tongTien = 0;

    NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
    if(nguoiDung == null) return "Không tìm thấy người dùng";
    donHang.setNguoiDung(nguoiDung);
    donHang.setNgayTao(new Date());
    donHangRepository.save(donHang);

    List<SanPhamDTO> sanPhamDTOS = donHangDTO.getDonHangSanPham();
    for (SanPhamDTO sanPhamDTO: sanPhamDTOS) {
      SanPham sanPham = sanPhamRepository.findById( sanPhamDTO.getId()).orElse(null);
      int soLuongMua = sanPhamDTO.getSoLuongMua();
      tongTien += sanPham.getGiaBan() * soLuongMua;
      ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
      chiTietDonHang.setDonHang(donHang);
      chiTietDonHang.setSanPham(sanPham);
      chiTietDonHang.setSoLuongMua(soLuongMua);
      chiTietDonHangRepository.save(chiTietDonHang);
    }

    List<SanBongDTO> sanBongDTOS = donHangDTO.getDonHangSanBong();
    for(SanBongDTO sanBongDTO : sanBongDTOS){
      SanBong sanBong = sanBongRepository.findById( sanBongDTO.getId()).orElse(null);
      ChiTietDatSan chiTietDatSan = new ChiTietDatSan();
      chiTietDatSan.setDonHang(donHang);
      chiTietDatSan.setSanBong(sanBong);
      chiTietDatSan.setKip(sanBongDTO.getKip());
      chiTietDatSan.setNgay(sanBongDTO.getNgay());
      tongTien += sanBong.getGia();
      chiTietDatSanRepository.save(chiTietDatSan);
    }
    donHang.setTongTien(tongTien);
    donHangRepository.save(donHang);
    return "Nhập đơn hàng thành công";
  }
}
