package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PendudukMapper {
	@Select("SELECT COUNT(nik) FROM penduduk WHERE nik LIKE #{query}")
	int countPenduduk(String query);
	
	@Select("select id_kelurahan from keluarga where id = #{id_kelurahan}")
	String selectPendudukID(String id_kelurahan);

}
