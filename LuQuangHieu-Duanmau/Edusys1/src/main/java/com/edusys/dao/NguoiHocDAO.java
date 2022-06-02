package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{

    public void insert(NguoiHoc model){
        String sql="INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaNH(), 
                model.getHoTen(), 
                model.getNgaySinh(), 
                model.getGioiTinh(), 
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV());
    }
    
    public void update(NguoiHoc model){
        String sql="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaNH=?";
        XJdbc.update(sql, 
                model.getHoTen(), 
                model.getNgaySinh(), 
                model.getGioiTinh(), 
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaNH());
    }
    
    public void delete(String id){
        String sql="DELETE FROM NguoiHoc WHERE MaNH=?";
        XJdbc.update(sql, id);
    }
    
    public List<NguoiHoc> selectAll(){
        String sql="SELECT * FROM NguoiHoc";
        return selectBySql(sql);
    }
    
    public NguoiHoc selectById(String manh){
        String sql="SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = selectBySql(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NguoiHoc> selectBySql(String sql, Object...args){
        List<NguoiHoc> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NguoiHoc entity=new NguoiHoc();
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDienThoai(rs.getString("DienThoai"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayDK(rs.getDate("NgayDK"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql="SELECT * FROM NguoiHoc "
                + " WHERE HoTen LIKE ? AND "
                
                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.selectBySql(sql, "%"+keyword+"%", makh);
    }
}
