package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

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
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.LokasiModel;
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
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
    
    @Autowired
    KecamatanService kecamatanService;
    
    @Autowired
    KotaService kotaService;
    
    @Autowired
    KelurahanService kelurahanService;

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
    	return "form-sukses-ubah-penduduk";
    }
    
    @RequestMapping("/penduduk/cari")
    public String cariPenduduk (Model model,
    		@RequestParam(value = "kt", required = false) String kt,
    		@RequestParam(value = "kc", required = false) String kc,
    		@RequestParam(value = "kl", required = false) String kl) {
			
    	//ambil semua kota
    	List<KotaModel> allKota = kotaService.selectAllKota();
    	model.addAttribute("allKota", allKota);
    	
    	//ambil semua kecamatan
    	if (kt != null) {
    		model.addAttribute("kt", kt);
    		List<KecamatanModel> allKecamatan = kecamatanService.selectAllKecamatanByIdKota(kt);
    		model.addAttribute("allKecamatan", allKecamatan);
    	}
    	
    	//ambil semua kelurahan
    	if(kl != null) {
    		model.addAttribute("kl", kl);
    		List<KelurahanModel> allKelurahan = kelurahanService.selectAllKelurahanByIdKecamatan();
    		model.addAttribute("allKelurahan", allKelurahan);
    	}
    	
    	//jika tidak null, maka akan menampilkan datanya
    	if (kt != null && kl !=null && kc!=null) {
    		KotaModel kota = kotaService.SelectKotaById(kt);
    		model.addAttribute("kota", kota);
    		System.out.println(kota);
    		KecamatanModel kecamatan = kecamatanService.selectKecamatanById(kc);
    		model.addAttribute("kecamatan", kecamatan);
    		KelurahanModel kelurahan = kelurahanService.selectKelurahanById(kl);
    		model.addAttribute("kelurahan", kelurahan);
    		
    		return "form-list-penduduk";
    	}    	
    	return "form-cari";
    	
    }
    
    @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String pendudukWafat (Model model, @PathVariable(value = "nik") String nik) {
		
    	PendudukModel penduduk = sistemDAO.selectPenduduk(nik);
    	penduduk.setIs_wafat(1);
    	pendudukDAO.updatePenduduk(penduduk);
    	
    	List<PendudukModel> listPenduduk = sistemDAO.selectPendudukList(penduduk.getId_keluarga());
    	boolean semuaMati = true;
		
		for (int i = 0; i < listPenduduk.size(); i++) {
			if (listPenduduk.get(i).getIs_wafat() == 0 && listPenduduk.get(i).getId().compareTo(penduduk.getId()) != 0) {
				semuaMati = false;
			}
		}
		
		if(semuaMati == true) {
			KeluargaModel keluarga = keluargaDAO.selectKeluargaById(penduduk.getId_keluarga());
			keluarga.setIs_tidak_berlaku(1);
			keluargaService.updateKeluarga(keluarga);		
		}
		pendudukService.updatePenduduk(penduduk);
		model.addAttribute("nik", penduduk.getNik());
		model.addAttribute("penduduk", penduduk);
		System.out.println(nik);
	
    	return "form-penduduk-mati";
    	
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
    		@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
    		@RequestParam(value = "nama_kelurahan", required = false) String nama_kelurahan,
    		@ModelAttribute KeluargaModel keluarga) {
		/*
		 * Generate NKK
		 */
    	String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    	
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatanNKK(nama_kecamatan).substring(0,6); //ambil kode_kecamatan lalu di substring
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
    		@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
    		@ModelAttribute KeluargaModel keluarga) {
    	
    	String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    	
    	//sekarang cari nama_kecamatan dari id_kelurahan yang diketahui
    	String kode_kecamatan = lokasiDAO.selectKodeKecamatanUpdate(id_kelurahan);
    	String kode_kecamatan_new = kode_kecamatan.substring(0,6);
    	
    	String prefix = kode_kecamatan_new + date;
    	String query = prefix + "%";
    	
    	int hitungKeluarga = keluargaService.countKeluarga(query);
    	
    	String nomor_kk_new = prefix + String.format("%04d", hitungKeluarga + 1);
       	
    	keluarga.setNomor_kk(nomor_kk_new);
    	keluarga.setId_kelurahan(id_kelurahan);
    	
    	keluargaService.updateKeluarga(keluarga);
		model.addAttribute("nomor_kk_new", keluarga.getNomor_kk());
		return "form-sukses-ubah-keluarga";
    }   
    
    
 }



