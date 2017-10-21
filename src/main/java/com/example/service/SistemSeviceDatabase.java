package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SistemMapper;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.LokasiModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class SistemSeviceDatabase implements SistemService {

	@Autowired
    private SistemMapper sistemMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
        return sistemMapper.selectPenduduk(nik);
	}

	@Override
	public KeluargaModel selectKeluarga(String nomor_kk) {
		log.info("select keluarga with nkk {}", nomor_kk);
		return sistemMapper.selectKeluarga(nomor_kk);
	}

	@Override
	public List<PendudukModel> selectPendudukList(String id) {
		log.info("select penduduk with id {}", id);
		return sistemMapper.selectPendudukList(id);
	}

	@Override
	public KeluargaModel selectKeluargaID(String id) {
		log.info("select keluarga with id_keluarga {}", id);
		return sistemMapper.selectKeluargaID(id);
	}

	@Override
	public List<PendudukModel> selectPendudukFromKeluarga(String id_keluarga) {
		log.info("select penduduk with id_keluarga {}", id_keluarga);
		return sistemMapper.selectPendudukFromKeluarga(id_keluarga);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		log.info("menambah penduduk dengan list of {}", penduduk);
		sistemMapper.addPenduduk(penduduk);
	}

	

	
}
