document.addEventListener('DOMContentLoaded', () => {
    // 1. KODE UNTUK MEMANGGIL DATA UKM DI HALAMAN 'DAFTAR UKM'
    const ukmGrid = document.getElementById('ukmGrid');
    if (ukmGrid) {
        fetch('/api/ukm')
            .then(res => res.json())
            .then(data => {
                if(data.length === 0) {
                    ukmGrid.innerHTML = `<p style="grid-column: 1/-1; text-align:center; color:#888;">Belum ada data UKM terdaftar di database.</p>`;
                    return;
                }
                ukmGrid.innerHTML = data.map(item => `
                    <div class="ukm-card" style="background:white; padding:25px; border-radius:12px; box-shadow:0 4px 15px rgba(0,0,0,0.05); border:1px solid #eee;">
                        <h3 style="font-size:1.4rem; color:#2c3e50; margin-bottom:10px;">${item.nama}</h3>
                        <p style="color:#666; font-size:0.95rem; margin-bottom:20px; line-height:1.5;">
                            ${item.visi ? item.visi.substring(0, 80) + '...' : 'Klik detail untuk melihat visi & misi UKM.'}
                        </p>
                        <div class="card-footer" style="display:flex; justify-content:space-between; align-items:center;">
                            <span style="font-size:0.85rem; color:#888;">📱 WA: ${item.kontakWa}</span>
                            <a href="/detail-ukm/${item.id}" class="join-btn" style="color:#007bff; text-decoration:none; font-weight:600;">Lihat Detail →</a>
                        </div>
                    </div>
                `).join('');
            })
            .catch(err => console.error("Gagal mengambil data UKM:", err));
    }

    // 2. KODE UNTUK LOAD DAFTAR UKM PADA Halaman Form Pendaftaran (Radio Button)
    const ukmPicker = document.getElementById('ukmPicker');
    if (ukmPicker) {
        fetch('/api/ukm')
            .then(res => res.json())
            .then(data => {
                if (data.length === 0) {
                    ukmPicker.innerHTML = '<p style="color:red;">Tidak ada UKM aktif yang bisa dipilih.</p>';
                    return;
                }
                ukmPicker.innerHTML = data.map(item => `
                    <label style="display:block; margin-bottom:10px; cursor:pointer;">
                        <input type="radio" name="selectedUkm" value="${item.nama}" style="margin-right:10px;" />
                        ${item.nama}
                    </label>
                `).join('');
            });
    }
});

// 3. LOGIC SUBMIT FORM PENDAFTARAN MAHASISWA
function submitRegistration(event) {
    event.preventDefault();
    
    // Reset Pesan Error
    document.querySelectorAll('.field-error').forEach(el => el.style.display = 'none');
    
    const nama = document.getElementById('inputNama').value.trim();
    const nim = document.getElementById('inputNIM').value.trim();
    const kelas = document.getElementById('inputKelas').value.trim();
    const selectedRadio = document.querySelector('input[name="selectedUkm"]:checked');
    
    let isValid = true;
    
    if (!nama) { document.getElementById('errNama').style.display = 'block'; isValid = false; }
    if (!nim) { document.getElementById('errNIM').style.display = 'block'; isValid = false; }
    if (!kelas) { document.getElementById('errKelas').style.display = 'block'; isValid = false; }
    if (!selectedRadio) { document.getElementById('errUKM').style.display = 'block'; isValid = false; }
    
    if (isValid) {
        const ukmForm = document.querySelector('.ukm-form');
        const successBox = document.getElementById('successBox');
        const summaryBox = document.getElementById('summaryBox');
        
        // Buat rangkuman pendaftaran
        summaryBox.innerHTML = `
            <div style="text-align:left; background:#f8f9fa; padding:15px; border-radius:8px; margin-top:15px; border-left:4px solid #28a745;">
                <p style="margin:5px 0;"><strong>Nama:</strong> ${nama}</p>
                <p style="margin:5px 0;"><strong>NIM:</strong> ${nim}</p>
                <p style="margin:5px 0;"><strong>Kelas:</strong> ${kelas}</p>
                <p style="margin:5px 0;"><strong>UKM Pilihan:</strong> ${selectedRadio.value}</p>
            </div>
        `;
        
        // Tampilkan kotak sukses
        ukmForm.style.display = 'none';
        successBox.style.display = 'block';
        successBox.classList.add('show');
    }
}