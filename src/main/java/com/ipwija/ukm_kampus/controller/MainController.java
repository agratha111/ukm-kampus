package com.ipwija.ukm_kampus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipwija.ukm_kampus.model.Ukm;
import com.ipwija.ukm_kampus.repository.UkmRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    private UkmRepository ukmRepository;

    @GetMapping("/") public String beranda() { return "beranda"; }
    @GetMapping("/daftar_ukm") public String daftarUkm(Model model) { model.addAttribute("listUkm", ukmRepository.findAll()); return "daftar_ukm"; }
    @GetMapping("/detail-ukm/{id}") public String detailUkm(@PathVariable Long id, Model model) { model.addAttribute("ukm", ukmRepository.findById(id).orElse(null)); return "detail_ukm"; }
    @GetMapping("/pendaftaran") public String pendaftaran(Model model) { model.addAttribute("listUkm", ukmRepository.findAll()); return "pendaftaran"; }
    @GetMapping("/admin/login") public String login() { return "admin_login"; }

    @PostMapping("/admin/login")
    public String prosesLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if ("admin".equals(username) && "admin123".equals(password)) { session.setAttribute("adminLoggedIn", true); return "redirect:/admin/dashboard"; }
        return "redirect:/admin/login?error";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) return "redirect:/admin/login";
        model.addAttribute("listUkm", ukmRepository.findAll());
        return "admin_dashboard";
    }

    @PostMapping("/admin/tambah-ukm")
    public String tambahUkm(@RequestParam String nama, @RequestParam String kontakWa, 
                            @RequestParam(required = false) String visi, @RequestParam(required = false) String misi, 
                            HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) return "redirect:/admin/login";
        Ukm ukm = new Ukm(nama, kontakWa);
        ukm.setVisi(visi);
        ukm.setMisi(misi);
        ukmRepository.save(ukm);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/hapus-ukm/{id}")
    public String hapusUkm(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) return "redirect:/admin/login";
        ukmRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) { session.invalidate(); return "redirect:/"; }
}