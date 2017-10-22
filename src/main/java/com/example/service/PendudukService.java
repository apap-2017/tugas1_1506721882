package com.example.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.PendudukModel;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public interface PendudukService {

	int countPenduduk(String query);
	String selectPendudukID(String id_kelurahan);
	void updatePenduduk(PendudukModel penduduk);
	List<PendudukModel> selectPendudukById(String id_keluarga);

}
