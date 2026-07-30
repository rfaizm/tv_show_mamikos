# Question

## 1. Which part of your submission are you least confident about, and why?
- Part dari submission yang saya kurang percaya diri ada di unit testing, dan UI. Karena saya terbiasa memakai format XML dibanding jetpack compose, dan saya juga tidak terbiasa memakai unit testing.

## 2. Describe a moment during this project (or any past project) where you got completely stuck. What did you do, step by step?
- Salah satu isu paling menantang ada saat saya magang membuat aplikasi e-KTP Reader untuk tamu. Masalahnya, server fisik di kantor sering down. Karena aplikasi ini standby seharian, setiap kali tamu tap KTP saat server mati, aplikasi crash karena gagal mengirim data. Saya mendiskusikannya dengan mentor dan saya mengimplementasikan Room Database sebagai penyimpanan lokal. Jadi, saat server down, data e-KTP disimpan di Room terlebih dahulu. Lalu, saya membuat background service (misalnya menggunakan WorkManager) yang mencoba mengirim ulang data tersebut setiap 1 jam. Jika setelah 1 jam server masih mati, aplikasi tidak akan crash, melainkan hanya memunculkan notifikasi lokal bahwa data tertunda di riwayat.

## 3. Imagine: it's Thursday, your task is due Friday, and you realize you misunderstood the requirement, half your work is wrong. What are you doing now?
- Pertama langkah yang saya lakukan adalah mengkonfirmasi ke mentor bahwa saya salah memahami requirement, tapi saya juga memberikan pernyataan bahwa saya akan coba untuk membernarkan perkejaan saya ASAP. Dan langkah selanjutnya saya cepat-cepat mengerjakan hal tersebut sebelum deadline itu selesai.

## 4. Your mentor asks you to change an approach you believe is worse. What do you do?
- Langkah yang saya lakukan adalah mengkonfirmasi perubahan tersebut, apakah benar-benar diperlukan. Dan saya juga akan mencoba memberi approach ke mentor bahwa hal tersebut bisa saja buruk (saya juga memberikan alasan kenapa itu buruk). Jikalau mentor tetap ingin hal itu terjadi maka saya akan mengikuti mentor.

## 5. What's something technical you taught yourself recently outside of class/work, and how did you learn it?
- Dari berbagai user interview yang saya lakukan kebelakang, bahwa hal yang saya terlewatkan adalah kode harus kompatibel di beberapa perangkat, bahkan perangat yang usang sekalipun.