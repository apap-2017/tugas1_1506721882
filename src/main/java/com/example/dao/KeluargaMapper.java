package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	@Update("UPDATE keluarga SET nomor_kk = #{nomor_kk}, alamat = #{alamat}, RT = #{RT}, RW = #{RW}, id_kelurahan = #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} WHERE id = #{id}")
    void updateKeluarga (KeluargaModel keluarga);
}
