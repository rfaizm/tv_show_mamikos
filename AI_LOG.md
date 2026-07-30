## Entry 1: Impelementasi UI Screen

### What I asked the AI / the problem I was solving:

Saya bertanya kepada AI untuk membuatkan UI Screen untuk menampilkan List Tv Show dan Detail Tv Show. Saya juga memberikan instruksi bahwa jika ada component yang bisa di reusable maka pisahkan UI tersebut dengan halaman utama dan saya bertanya ke AI bahwa diperlukan dummy data (bukan data dari API)

### What it gave me:

AI memberikan saya kode halaman List Tv Show, Detail Tv Show, dan juga kode-kode yang bisa reusable (kode tersebut terdapat pada ui/components/).

### What I did:

Saya mengimplementasikan kode tersebut dan mencoba untuk build kode tersebut, lalu merevisi bagian UI pada rating (yang awalnya tidak bisa handle null, menjadi N/A).

### One thing the AI got wrong or that I verified myself:

Saya memverifikasi bahwa kode yang diberikan tidak sesuai dengan data yang ingin ditampilkan (seperti title, image, dan rating).


## Entry 2: Impelementasi Logic List Tv Show

### What I asked the AI / the problem I was solving:

Saya bertanya kepada AI untuk membuatkan saya kode yang konversi dari DTO (Body API) menjadi model Show.kt, lalu membuatkan kode yang sama seperti template yang saya kirimkan untuk GetTvShowListUseCase dan ListScreenViewModel, lalu merubah kode pada ListScreen.kt

### What it gave me:

AI memberikan saya kode mapper untuk konversi dari data DTO menjadi model (Show.kt), dan juga memberikan kode

### What I did:

Saya memastikan kode yang dibuat sama persis dengan alur dari arsitektur saya, saya juga menambahkan permission Internet,dan template yang sudah saya buat.

### One thing the AI got wrong or that I verified myself:

Disini saya kecolongan, karena response dari API diharuskan untuk berbentuk List<>, sedangkan kode yang saya berikan pada ApiService sebelumnya berbentuk Object.


## Entry 3: Impelementasi Unit Testing

### What I asked the AI / the problem I was solving:

Saya bertanya kepada AI untuk membuatkan saya unit testing untuk ListScreenViewModel dan juga GetTvShowListUseCase, saya juga mention bahwa berikan test case yang semua memungkinkan terjadi pada sistem

### What it gave me:

AI Memberikan saya kode unit testing yang diinginkan, seperti ListScreenViewModelTest dan GetTvShowListUseCaseTest

### What I did:

Karena saya mempunyai kekurangan pada unit testing, saya coba untuk menjalankan test tersebut.

### One thing the AI got wrong or that I verified myself:

Karena unit testing adalah kelemahan saya, saya coba untuk memahami isi kode pada unit testing tersebut.

