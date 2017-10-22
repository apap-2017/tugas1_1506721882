package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public int countPenduduk(String query) {
		return pendudukMapper.countPenduduk(query);
	}

	@Override
	public String selectPendudukID(String id_kelurahan) {
		log.info("",id_kelurahan);
		return pendudukMapper.selectPendudukID(id_kelurahan);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		// TODO Auto-generated method stub
		log.info("ubah penduduk", penduduk);
		pendudukMapper.updatePenduduk(penduduk);
		
	}

	@Override
	public List<PendudukModel> selectPendudukById(String id_keluarga) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectPendudukById(id_keluarga);
	}
}
