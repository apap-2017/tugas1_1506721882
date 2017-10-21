package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.LokasiModel;

@Mapper
public interface LokasiMapper {
	@Select("select a.nama_kelurahan, b.nama_kecamatan, c.nama_kota from kelurahan a, kecamatan b, kota c where a.id = #{id_kelurahan} AND a.id_kecamatan = b.id AND b.id_kota = c.id ")
	LokasiModel selectLokasi(@Param("id_kelurahan") String id_kelurahan);
	
	@Select("select kode_kecamatan from kecamatan ke, kelurahan kel where kel.id = #{id_kelurahan} AND "
			+ "ke.id = kel.id_kecamatan")
	String selectKodeKecamatan(String id_kelurahan);
	
	@Select("select kode_kecamatan from kecamatan where nama_kecamatan = #{nama_kecamatan}")
	String selectKodeKecamatanNKK(String nama_kecamatan);
	
	@Select("select id from kelurahan where nama_kelurahan = #{nama_kelurahan}")
	String selectKelurahanId(String nama_kelurahan);
	
	@Select("select c.kode_kecamatan from kelurahan b, kecamatan c where b.id = #{id_kelurahan} AND  b.id_kecamatan = c.id")
	String selectKodeKecamatanUpdate (String id_kelurahan);
}
