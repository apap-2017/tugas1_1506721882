package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LokasiModel {
	private String id_kecamatan;
	private String nama_kecamatan;
	private String id_kelurahan;
	private String nama_kelurahan;
	private String id_kota;
	private String nama_kota;
	private String kode_kecamatan;
}
