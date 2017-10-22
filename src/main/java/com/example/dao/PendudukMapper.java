package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("SELECT COUNT(nik) FROM penduduk WHERE nik LIKE #{query}")
	int countPenduduk(String query);
	
	@Select("select id_kelurahan from keluarga where id = #{id_kelurahan}")
	String selectPendudukID(String id_kelurahan);

	@Select("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, "
			+ "tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, "
			+ "id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, "
			+ "status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, "
			+ "golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} WHERE id = #{id}")
	void updatePenduduk(PendudukModel penduduk);
		
	@Select("SELECT * FROM penduduk WHERE id_keluarga = #{id_keluarga}")
	List<PendudukModel> selectPendudukById (@Param("id_keluarga") String id_keluarga);
}
