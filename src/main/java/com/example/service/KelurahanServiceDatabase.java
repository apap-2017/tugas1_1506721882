package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService{
	
	@Autowired
	private KelurahanService kelurahanService;
	
	@Autowired
	private KelurahanMapper kelurahanMapper;
	
	@Override
	public List<KelurahanModel> selectAllKelurahanByIdKecamatan(String id_kecamatan){
		log.info("Ini mengambil seluruh kelurahan");
		return kelurahanMapper.selectAllKelurahanByIdKecamatan(id_kecamatan);
	}

	@Override
	public KelurahanModel selectKelurahanById(String id) {
		// TODO Auto-generated method stub
		return kelurahanService.selectKelurahanById(id);
	}
}
