package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{

    public void insert(KhoaHoc model){
        String sql="INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV());
    }
    
    public void update(KhoaHoc model){
        String sql="UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
        XJdbc.update(sql, 
                model.getMaCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getNgayKG(), 
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }
    
    public void delete(Integer MaKH){
        String sql="DELETE FROM KhoaHoc WHERE MaKH=?";
        XJdbc.update(sql, MaKH);
    }
    
    public List<KhoaHoc> selectAll(){
        String sql="SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }
    
    public KhoaHoc selectById(Integer makh){
        String sql="SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = selectBySql(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<KhoaHoc> selectBySql(String sql, Object...args){
        List<KhoaHoc> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    KhoaHoc entity=new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    entity.setMaCD(rs.getString("MaCD"));
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
    
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql="SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySql(sql, macd);
    }

    public List<Integer> selectYears() {
        String sql="SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list=new ArrayList<>();
        try {
           ResultSet rs = XJdbc.query(sql);
           while(rs.next()){
                 list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
