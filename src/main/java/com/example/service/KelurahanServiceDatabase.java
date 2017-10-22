package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService{
	
	@Autowired
	private KelurahanService kelurahanService;
	
	@Override
	public List<KelurahanModel> selectAllKelurahanByIdKecamatan(){
		log.info("Ini mengambil seluruh kelurahan ", selectAllKelurahanByIdKecamatan());
		return kelurahanService.selectAllKelurahanByIdKecamatan();
	}

	@Override
	public KelurahanModel selectKelurahanById(String id) {
		// TODO Auto-generated method stub
		return kelurahanService.selectKelurahanById(id);
	}
}
