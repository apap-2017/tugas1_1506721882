package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

public interface KotaService {
	List<KotaModel> selectAllKota();

	KotaModel SelectKotaById(String id);
}
