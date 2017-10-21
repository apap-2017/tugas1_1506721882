package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.KeluargaMapper;
import com.example.dao.LokasiMapper;
import com.example.dao.PendudukMapper;
import com.example.dao.SistemMapper;
import com.example.model.KeluargaModel;
import com.example.model.LokasiModel;
import com.example.model.PendudukModel;
import com.example.service.KeluargaService;
import com.example.service.PendudukService;
import com.example.service.SistemService;

@Controller
public class SistemController
{
    @Autowired
    SistemMapper sistemDAO;
    
    @Autowired
    LokasiMapper lokasiDAO;
    
    @Autowired
    PendudukMapper pendudukDAO;
    
    @Autowired
    KeluargaMapper keluargaDAO;
    
    @Autowired
    SistemService sistemService;
    
    @Autowired
    PendudukService pendudukService;
    
    @Autowired
    KeluargaService keluargaService;

    @RequestMapping("/")
    public String index ()
    {
        return "form-request";
    }

    /*
     * Penduduk
     */
    @RequestMapping("/penduduk")
    public String find (Model model, @RequestParam(value = "nik", required = false) String nik){
       
    	PendudukModel penduduk = sistemDAO.selectPenduduk(nik);      
    	KeluargaModel keluarga = sistemDAO.selectKeluargaID(penduduk.getId_keluarga());
    	LokasiModel lokasi = lokasiDAO.selectLokasi(keluarga.getId_kelurahan());
        
        if(penduduk != null) {
        	model.addAttribute("penduduk", penduduk);
        	model.addAttribute("keluarga", keluarga);
        	model.addAttribute("lokasi", lokasi);
        	
        	return "form-penduduk";        	
        } else {
            return "form-not-penduduk";
        }        
    }
    
 
    @RequestMapping("/penduduk/tambah")
    public String tambahPenduduk (Model model){
    	PendudukModel penduduk = new PendudukModel();
    	   	
    	model.addAttribute("action", "/penduduk/tambah");
    	model.addAttribute("penduduk", penduduk);
    	return "form-tambah-penduduk";
    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String tambahPenduduk (Model model, @ModelAttribute PendudukModel penduduk){
    	
    	String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
    	int tahun = (Integer.parseInt(tanggal_lahir[0]) % 1000) %100;
    	int bulan = (Integer.parseInt(tanggal_lahir[1]));
    	int hari = (Integer.parseInt(tanggal_lahir[2]));
    	
    	if(penduduk.getJenis_kelamin() == 1) {
    		hari += 40;
    	}
   
    	String id_kelurahan = pendudukDAO.selectPendudukID(penduduk.getId_keluarga());
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatan(id_kelurahan).substring(0, 6);
    	
    	String prefix = kode_kecamatan + String.format("%02d", hari) + String.format("%02d", bulan) + String.format("%02d", tahun);
    	String query = prefix + "%";
    	
    	int hitungPenduduk = pendudukService.countPenduduk(query);
   
    	String getNewNIK = prefix + String.format("%04d", hitungPenduduk + 1);
    	
    	penduduk.setNik(getNewNIK);
    	
    	sistemDAO.addPenduduk(penduduk);
    	model.addAttribute("nik", penduduk.getNik());
    	return "form-sukses-nambah-penduduk";
    }  
    
    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.GET)
    public String ubahPenduduk (Model model, @PathVariable(value = "nik") String nik) {
		
    	PendudukModel penduduk = sistemDAO.selectPenduduk(nik);
    	
    	if(penduduk != null) {
    		model.addAttribute("penduduk", penduduk);
    		return "form-ubah-penduduk";
    	} else {
    		return "not-found";
    	}
    }
    
    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
    public String ubahPendudukSubmit (Model model, @PathVariable(value = "nik") String nik, @ModelAttribute PendudukModel penduduk) {
			
    	String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
    	int tahun = (Integer.parseInt(tanggal_lahir[0]) % 1000) %100;
    	int bulan = (Integer.parseInt(tanggal_lahir[1]));
    	int hari = (Integer.parseInt(tanggal_lahir[2]));
    	
    	if(penduduk.getJenis_kelamin() == 1) {
    		hari += 40;
    	}
   
    	String id_kelurahan = pendudukDAO.selectPendudukID(penduduk.getId_keluarga());
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatan(id_kelurahan).substring(0, 6);
    	
    	String prefix = kode_kecamatan + String.format("%02d", hari) + String.format("%02d", bulan) + String.format("%02d", tahun);
    	String query = prefix + "%";
    	
    	int hitungPenduduk = pendudukService.countPenduduk(query);
   
    	String getNewNIK = prefix + String.format("%04d", hitungPenduduk + 1);
    	
    	penduduk.setNik(getNewNIK);
    	
    	pendudukDAO.updatePenduduk(penduduk);
    	model.addAttribute("nik", nik);
    //	model.addAttribute("penduduk", penduduk);
    	return "form-sukses-ubah-penduduk";
    }
    
    
    
    /*
     * Keluarga
     */
    @RequestMapping("/keluarga")
    public String findNKK (Model model, @RequestParam(value = "nomor_kk", required = false) String nomor_kk){
       
    	KeluargaModel keluarga = sistemDAO.selectKeluarga(nomor_kk);
    	LokasiModel lokasi = lokasiDAO.selectLokasi(keluarga.getId_kelurahan());
    	List<PendudukModel> penduduk = sistemDAO.selectPendudukFromKeluarga(keluarga.getId());
    	
    	if(keluarga != null) {
        	model.addAttribute("penduduk", penduduk);
        	model.addAttribute("keluarga", keluarga);
        	model.addAttribute("lokasi", lokasi);
        	
        	return "form-keluarga";        	
        } else {
            return "form-not-penduduk";
        }        
    }
    
    @RequestMapping("/keluarga/tambah")
    public String tambahKeluarga (Model model) {
    	KeluargaModel keluarga = new KeluargaModel();
	   	
    	model.addAttribute("action", "/keluarga/tambah");
    	model.addAttribute("keluarga", keluarga);
    	return "form-tambah-keluarga";
    }
    
    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String tambahKeluarga (Model model, @RequestParam(value = "nama_kecamatan", required = false) String nama_kecamatan,
    		@RequestParam(value = "alamat", required = false) String alamat,
    		@RequestParam(value = "RT", required = false) String RT,
    		@RequestParam(value = "RW", required = false) String RW,
    		@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
    		@RequestParam(value = "nama_kota", required = false) String nama_kota,
    		@RequestParam(value = "nama_kelurahan", required = false) String nama_kelurahan,
    		@ModelAttribute KeluargaModel keluarga) {
		/*
		 * Generate NKK
		 */
    	String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    	
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatanNKK(nama_kecamatan).substring(0,6);
    	String id_kelurahan1 = lokasiDAO.selectKelurahanId(nama_kelurahan); //udah dapet id
    	
    	String prefix = kode_kecamatan + date;
    	String query = prefix + "%";
    	
    	int hitungKeluarga = keluargaService.countKeluarga(query);
    	
    	String nomor_kk = prefix + String.format("%04d", hitungKeluarga + 1);
    	System.out.println(nomor_kk);
    	
    	keluarga.setNomor_kk(nomor_kk);
    	keluarga.setId_kelurahan(id_kelurahan1);
    	
    	keluargaService.addkeluarga(keluarga);
		model.addAttribute("nomor_kk", keluarga.getNomor_kk());
		return "form-sukses-nambah-keluarga";
    	
    }
    
    @RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.GET)
    public String ubahKeluarga (Model model, @PathVariable(value = "nomor_kk") String nomor_kk) {
		
    	KeluargaModel keluarga = sistemDAO.selectKeluarga(nomor_kk);
    	
    	if(keluarga != null) {
    		model.addAttribute("keluarga", keluarga);
    		return "form-ubah-keluarga";
    	} else {
    		return "not-found";
    	}
    }
    
    @RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
    public String ubahKeluargaSubmit (Model model, @PathVariable(value = "nomor_kk") String nomor_kk,
    		@RequestParam(value = "nama_kecamatan", required = false) String nama_kecamatan,
    		@RequestParam(value = "alamat", required = false) String alamat,
    		@RequestParam(value = "RT", required = false) String RT,
    		@RequestParam(value = "RW", required = false) String RW,
    		@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
    		@RequestParam(value = "nama_kota", required = false) String nama_kota,
    		@RequestParam(value = "nama_kelurahan", required = false) String nama_kelurahan,
    		@ModelAttribute KeluargaModel keluarga) {
    	
    	String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    	
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatanNKK(nama_kecamatan).substring(0,6);
    	String id_kelurahan1 = lokasiDAO.selectKelurahanId(nama_kelurahan); //udah dapet id
    	
    	String prefix = kode_kecamatan + date;
    	String query = prefix + "%";
    	
    	int hitungKeluarga = keluargaService.countKeluarga(query);
    	
    	String nomor_kk_new = prefix + String.format("%04d", hitungKeluarga + 1);
       	
    	keluarga.setNomor_kk(nomor_kk_new);
    	keluarga.setId_kelurahan(id_kelurahan1);
    	
    	keluargaService.updateKeluarga(keluarga);
		model.addAttribute("nomor_kk_new", keluarga.getNomor_kk());
		return "form-sukses-ubah-keluarga";
    	
    }    
 }



