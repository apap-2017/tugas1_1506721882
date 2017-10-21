package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
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
}
