package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {
	
	@Select("select * from kecamatan where id_kota = #{id_kota}")
	List<KecamatanModel> selectAllKecamatanByIdKota(@Param("id_kota") String id_kota);
	
	@Select("select * from kecamatan where id = #{id}")
	KecamatanModel selectKecamatanById (@Param("id")String id);
}
