package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {

	@Autowired
	private KeluargaMapper keluargaMapper;
	
	@Override
	public String selectKeluargaId(String id_kelurahan) {
		// TODO Auto-generated method stub
		return keluargaMapper.selectKeluargaId(id_kelurahan);
	}

	@Override
	public int countKeluarga(String query) {
		// TODO Auto-generated method stub
		return keluargaMapper.countKeluarga(query);
	}
	
	@Override
	public void addkeluarga(KeluargaModel keluarga) {
		log.info("menambah keluarga dengan list of {}", keluarga);
		keluargaMapper.addKeluarga(keluarga);
	}
	
}
