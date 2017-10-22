package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KotaMapper;
import com.example.model.KotaModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService {

	@Autowired
	private KotaMapper kotaMapper;
	
	@Override
	public List<KotaModel> selectAllKota() {
		// TODO Auto-generated method stub
		log.info("Ini mengambil seluruh data Kota ");
		return kotaMapper.selectAllKota();
	}

	@Override
	public KotaModel SelectKotaById(String id) {
		// TODO Auto-generated method stub
		log.info("ini mengambil kota berdasarkan ", id);
		return kotaMapper.SelectKotaById(id);
	}
	
}
