package com.example.service;

import com.example.model.LokasiModel;

public interface LokasiService {
	LokasiModel selectLokasi(String id_kelurahan);
	
	String selectKodeKecamatan(String id_kelurahan);
	
	String selectKodeKecamatanNKK(String nama_kecamatan);
	
	String selectKelurahanId(String id_kelurahan);
	
	String selectKodeKecamatanUpdate (String id_kelurahan);
}
