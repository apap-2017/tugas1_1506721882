package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	
	@Autowired
    private KecamatanMapper kecamatanMapper;
	
	@Override
	public List<KecamatanModel> selectAllKecamatanByIdKota(String id_kota) {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectAllKecamatanByIdKota(id_kota);
	}

	@Override
	public KecamatanModel selectKecamatanById(String id) {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectKecamatanById(id);
	}

}
