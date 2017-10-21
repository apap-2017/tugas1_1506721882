package com.example.service;

import com.example.model.KeluargaModel;

public interface KeluargaService {
	
	int countKeluarga(String query);
	
	String selectKeluargaId(String id_kelurahan);
	
	void addkeluarga(KeluargaModel keluarga);
	
	void updateKeluarga(KeluargaModel keluarga);
}
