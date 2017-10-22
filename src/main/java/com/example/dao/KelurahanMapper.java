package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	
	@Select("select * from kelurahan where id_kecamatan=#{id_kecamatan}")
	List<KelurahanModel> selectAllKelurahanByIdKecamatan(@Param("id_kecamatan")String id_kecamatan);
	
	@Select("select * from kelurahan where id = #{id}")
	KelurahanModel selectKelurahanById(String id);
}
