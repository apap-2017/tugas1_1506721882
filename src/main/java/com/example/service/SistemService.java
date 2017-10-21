package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.LokasiModel;
import com.example.model.PendudukModel;

public interface SistemService {
	
	PendudukModel selectPenduduk(String nik);
	
	KeluargaModel selectKeluarga(String nomor_kk);
	
	KeluargaModel selectKeluargaID(String id);

	List<PendudukModel> selectPendudukList(String id);
	
	List<PendudukModel> selectPendudukFromKeluarga(String id_keluarga);
	
	

	void addPenduduk(PendudukModel penduduk);
	
	
}
