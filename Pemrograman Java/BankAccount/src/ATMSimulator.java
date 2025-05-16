import java.util.Scanner;

public class ATMSimulator {
  private BankAccount account;
  private Scanner scanner;

  // Meminta input dari pengguna untuk nomor akun, nama pemilik, dan saldo awal menggunakan class Scanner
  public ATMSimulator(BankAccount account) {
      this.account = account;
      this.scanner = new Scanner(System.in);
  }

  // Menampilkan menu untuk:
    // 1. Deposit
    // 2. Tarik Tunai
    // 3. Cek Saldo
    // 4. Lihat Total Transaksi
    // 5. Keluar dari program
    public void showMenu() {
        int option;
        do {
            System.out.println("\n--- Selamat datang di ATM ---");
            System.out.println("1. Deposit");
            System.out.println("2. Tarik Tunai");
            System.out.println("3. Cek Saldo");
            System.out.println("4. Lihat Total Transaksi");
            System.out.println("5. Keluar dari Program");
            System.out.print("Pilih opsi: ");

            // Mengimplementasikan logika untuk setiap opsi menu dengan input dari pengguna
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    depositMoney();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    showTotalTransactions();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan ATM!");
                    break;
                default:
                    System.out.println("Opsi tidak valid! Silakan coba lagi.");
            }
        } while (option != 5);  // Ulangi hingga pengguna memilih untuk keluar
    }

    // Metode untuk setor uang
    private void depositMoney() {
        System.out.print("Masukkan jumlah uang yang ingin disetor: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Metode untuk tarik uang
    private void withdrawMoney() {
        System.out.print("Masukkan jumlah uang yang ingin ditarik: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    // Metode untuk cek saldo
    private void checkBalance() {
        System.out.println("=== Laporan Saldo ===");
        System.out.println("Saldo Anda saat ini: " + account.getBalance());
        System.out.println("====================");
    }

    // Metode untuk melihat total transaksi
    private void showTotalTransactions() {
        System.out.println("Total transaksi yang telah dilakukan: " + account.getTransactionCount());
    }

  public static void main(String[] args) {
      // Membuat objek BankAccount dengan data yang dimasukkan
      BankAccount myAccount = new BankAccount("123456789", "Rikho Septian", 100000.0);

      // Membuat objek ATM dengan akun yang telah dibuat
      ATMSimulator atm = new ATMSimulator(myAccount);

      // Menampilkan menu ATM dan menjalankan simulasi
      atm.showMenu();
  }
}
