# Code Review For Android

### List Problem :

1. Jika terdapat kode yang beresiko terdapat error seperti fetch API, diharuskan untuk menambahkan **try-catch**, agar jika terjadi sesuatu error tidak menjadi crash.
2. Dalam pemahaman saya, fetching API didalam ViewModel bukan hal yang ideal, fungsi dari ViewModel itu sendiri menjadi jembatan antara UI layer dan juga data layer (kode tersebut seharusnya dilakukan pada data layer). 
3. Jika ingin mendeklarasikan variabel movies pastikan menggunakan MutableLiveData maupun LiveData agar data selalu aman pada saat ditampilkan ke layar.
4. Jika data yang diambil dari fetch API ingin segera ditampilkan ke UI, maka sangat disarankan fungsi tersebut dimasukkan pada **init{}** agar pemanggilan kode tersebut berjalan pada saat pemanggilan viewmodelnya.