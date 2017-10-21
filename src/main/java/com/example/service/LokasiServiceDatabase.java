package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.LokasiMapper;
import com.example.model.LokasiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LokasiServiceDatabase implements LokasiService {
	@Autowired
    private LokasiMapper lokasiMapper;
	
	
	@Override
	public LokasiModel selectLokasi(String id_kelurahan) {
		log.info("select lokasi with id {}", id_kelurahan);
		return lokasiMapper.selectLokasi(id_kelurahan);
	}


	@Override
	public String selectKodeKecamatan(String id_kelurahan) {
		// TODO Auto-generated method stub
		return lokasiMapper.selectKodeKecamatan(id_kelurahan);
	}


	@Override
	public String selectKodeKecamatanNKK(String nama_kecamatan) {
		System.out.println(selectKodeKecamatanNKK(nama_kecamatan));
		// TODO Auto-generated method stub
		return lokasiMapper.selectKodeKecamatanNKK(nama_kecamatan);
		
	}

	@Override
	public String selectKelurahanId(String id_kelurahan) {
		// TODO Auto-generated method stub
		return lokasiMapper.selectKelurahanId(id_kelurahan);
	}


	@Override
	public String selectKodeKecamatanUpdate(String id_kelurahan) {
		// TODO Auto-generated method stub
		return lokasiMapper.selectKodeKecamatanUpdate(id_kelurahan);
	}
}
