public class BankAccount {
    // Constructor dengan parameter accountNumber dan accountHolder yang menginisialisasi balance dengan 0.
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private int transactionCount = 0;

    // Constructor dengan parameter accountNumber, accountHolder, dan initialBalance yang memanggil constructor pertama menggunakan keyword this dan kemudian menetapkan balance dengan nilai initialBalance jika nilainya positif.
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Method deposit(double amount) yang menambahkan jumlah ke balance jika positif dan menambah transactionCount.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;  // Menambah saldo
            transactionCount++;  // Menambah jumlah transaksi
            System.out.println("Setoran berhasil! Saldo sekarang: " + balance);
            System.out.println("Jumlah transaksi: " + transactionCount);
        } else {
            System.out.println("Jumlah setoran tidak valid! Harus lebih besar dari 0.");
        }
    }

    // Method withdraw(double amount) yang mengurangi jumlah dari balance jika dana mencukupi dan menambah transactionCount. Method ini harus mengembalikan true jika berhasil dan false jika gagal.
    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;  // Kurangi saldo
            transactionCount++;  // Tambah jumlah transaksi
            System.out.println("Penarikan berhasil! Saldo sekarang: " + balance);
            System.out.println("Jumlah transaksi: " + transactionCount);
        } else if (amount <= 0) {
            System.out.println("Jumlah penarikan tidak valid! Harus lebih besar dari 0.");
        } else {
            System.out.println("Saldo tidak cukup untuk penarikan ini!");
        }
    }

    // Static method getTransactionCount() yang mengembalikan jumlah total transaksi.
    public int getTransactionCount() {
        return transactionCount;
    }

    // Method getBalance() yang mengembalikan saldo akun saat ini.
    public double getBalance() {
        return balance;
    }

    // MMethod printStatement() yang mencetak informasi akun.
    public void printStatement() {
        System.out.println("=== Laporan Akun ===");
        System.out.println("Nomor Rekening  : " + accountNumber);
        System.out.println("Pemegang Rekening: " + accountHolder);
        System.out.println("Saldo           : " + balance);
        System.out.println("Jumlah Transaksi: " + transactionCount);
        System.out.println("====================");
    }

    public static void main(String[] args) throws Exception {
        // Membuat objek App dengan nilai awal saldo 1000.0
        BankAccount myAccount = new BankAccount("123456789", "Rikho Septian", 300000.0);

        // Menampilkan informasi akun
        // myAccount.printStatement();

        // Menambahkan setoran dan penarikan
        // myAccount.deposit(500.0);
        myAccount.withdraw(200000.0);
        myAccount.getTransactionCount();

        // Menampilkan informasi akun setelah transaksi
        myAccount.printStatement();
    }
}
