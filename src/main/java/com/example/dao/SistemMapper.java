package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

@Mapper
public interface SistemMapper {
	
	@Select("select * from penduduk where nik = #{nik}")
	@Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nik", column="nik"),
    		@Result(property="nama", column="nama"),
    		@Result(property="tempat_lahir", column="tempat_lahir"),
    		@Result(property="jenis_kelamin", column="jenis_kelamin"),
    		@Result(property="is_wni", column="is_wni"),
    		@Result(property="id_keluarga", column="id_keluarga"),
    		@Result(property="agama", column="agama"),
    		@Result(property="pekerjaan", column="pekerjaan"),
    		@Result(property="status_perkawinan", column="status_perkawinan"),
    		@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
    		@Result(property="golongan_darah", column="golongan_darah"),
    		@Result(property="is_wafat", column="is_wafat")
    })	
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Select("select * from keluarga where id = #{id}")
	KeluargaModel selectKeluargaID(@Param("id") String id);
	
	@Select("select * from kelurahan")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="id_kecamatan", column="id_kecamatan"),
			@Result(property="kode_kelurahan", column="kode_kelurahan"),
			@Result(property="nama_kelurahan", column="nama_kelurahan"),
			@Result(property="kode_pos", column="kode_pos")
	})
	KelurahanModel selectKelurahan(@Param("id") String id);

	@Select("select * from kecamatan")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="id_kota", column="id_kota"),
			@Result(property="kode_kecamatan", column="kode_kecamatan"),
			@Result(property="nama_kecamatan", column="nama_kecamatan")
	})
	KecamatanModel selectKecamatan(@Param("id") String id);

	@Select("select * from kota")
	@Results (value = {
			@Result(property="id", column="id"),
			@Result(property="kode_kota", column="kode_kota"),
			@Result(property="nama_kota", column="nama_kota")
	})
	KotaModel selectKota(@Param("id") String id);
	
	@Select("select * from keluarga a, kelurahan b, kota c where a.id_kelurahan = b.id and b.id_kecamatan = c.id")
	List<PendudukModel> selectPendudukList(@Param("id") String id);
	
	@Select("select * from penduduk p, keluarga k where p.id_keluarga = k.id and k.id = #{id_keluarga}")
	@Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nik", column="nik"),
    		@Result(property="nama", column="nama"),
    		@Result(property="tempat_lahir", column="tempat_lahir"),
    		@Result(property="jenis_kelamin", column="jenis_kelamin"),
    		@Result(property="is_wni", column="is_wni"),
    		@Result(property="id_keluarga", column="id_keluarga"),
    		@Result(property="agama", column="agama"),
    		@Result(property="pekerjaan", column="pekerjaan"),
    		@Result(property="status_perkawinan", column="status_perkawinan"),
    		@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
    		@Result(property="golongan_darah", column="golongan_darah"),
    		@Result(property="is_wafat", column="is_wafat")
    })	
	List<PendudukModel> selectPendudukFromKeluarga(@Param("id_keluarga") String id_keluarga);
	
	@Insert("INSERT INTO penduduk (nik, jenis_kelamin, nama, tempat_lahir, tanggal_lahir, golongan_darah, agama, "
			+ "status_perkawinan, pekerjaan, is_wni, is_wafat, id_keluarga, status_dalam_keluarga) "
			+ "VALUES (#{nik}, #{jenis_kelamin}, #{nama}, #{tempat_lahir}, "
			+ "#{tanggal_lahir}, #{golongan_darah}, #{agama}, #{status_perkawinan}, #{pekerjaan}, "
			+ "#{is_wni}, #{is_wafat},#{id_keluarga},#{status_dalam_keluarga})")
    void addPenduduk (PendudukModel penduduk);

	
}
