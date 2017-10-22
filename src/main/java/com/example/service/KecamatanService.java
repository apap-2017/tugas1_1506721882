package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;

public interface KecamatanService {

	List<KecamatanModel> selectAllKecamatanByIdKota(String id_kota);

	KecamatanModel selectKecamatanById(String id);
}
