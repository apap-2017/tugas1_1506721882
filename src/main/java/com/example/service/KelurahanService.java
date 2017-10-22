package com.example.service;

import java.util.List;

import com.example.model.KelurahanModel;

public interface KelurahanService {
	List<KelurahanModel> selectAllKelurahanByIdKecamatan();
	
	KelurahanModel selectKelurahanById(String id);
}
