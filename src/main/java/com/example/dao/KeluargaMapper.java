package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.model.KeluargaModel;

@Mapper
public interface KeluargaMapper {
	
	
	@Insert("insert into keluarga (nomor_kk,alamat,RT,RW,id_kelurahan,is_tidak_berlaku) "
			   + "values ('${nomor_kk}','${alamat}','${RT}','${RW}','${id_kelurahan}',0)")
	void addKeluarga (KeluargaModel keluarga);

	@Select("SELECT COUNT(nomor_kk) FROM keluarga WHERE nomor_kk LIKE #{query}")
	int countKeluarga(String query);
	
	@Select("select id_kelurahan from keluarga where id = #{id_kelurahan}")
	String selectKeluargaId(String id_kelurahan);
}
