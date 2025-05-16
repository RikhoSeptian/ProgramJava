# ATM Simulator - Sistem ATM Sederhana

Program ini mensimulasikan sistem ATM sederhana menggunakan bahasa pemrograman Java. Program terdiri dari dua kelas utama:
1. **BankAccount**: Menangani transaksi pada akun bank (deposit, tarik tunai, dan sebagainya).
2. **ATMSimulator**: Program utama yang berinteraksi dengan pengguna untuk melakukan berbagai transaksi.

## Fitur
- Menambahkan saldo (Deposit).
- Menarik saldo (Tarik Tunai).
- Melihat saldo saat ini.
- Melihat jumlah total transaksi yang telah dilakukan.
- Semua transaksi diukur dan dihitung menggunakan metode statis `getTransactionCount()`.

## Cara Penggunaan

1. **Input Akun dan Saldo Awal**
   - Program akan meminta input untuk nomor akun, nama pemilik, dan saldo awal akun.
   
2. **Menu ATM**
   Setelah itu, pengguna akan diberikan pilihan menu:
   - **1**: Deposit - Menambah saldo ke akun.
   - **2**: Tarik Tunai - Mengurangi saldo dengan penarikan tertentu.
   - **3**: Cek Saldo - Menampilkan saldo terkini akun.
   - **4**: Lihat Total Transaksi - Menampilkan jumlah total transaksi yang telah dilakukan.
   - **5**: Keluar - Menutup program.

## Struktur Program

### Kelas BankAccount
Kelas ini mengelola operasi dasar terkait akun bank, seperti deposit, tarik tunai, dan penghitungan transaksi.

#### Atribut:
- `accountNumber`: Nomor akun.
- `accountHolder`: Nama pemilik akun.
- `balance`: Saldo saat ini.
- `transactionCount`: Jumlah transaksi yang dilakukan.

#### Method:
- `deposit(double amount)`: Menambahkan dana ke saldo.
- `withdraw(double amount)`: Menarik dana dari saldo.
- `getTransactionCount()`: Mengembalikan jumlah total transaksi.
- `getBalance()`: Mengembalikan saldo saat ini.
- `printStatement()`: Mencetak informasi akun.

### Kelas ATMSimulator
Kelas ini menyediakan antarmuka pengguna untuk berinteraksi dengan sistem ATM, dan menampilkan menu pilihan transaksi.

## Contoh Penggunaan

