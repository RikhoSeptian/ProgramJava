import java.util.Scanner;

class Buku {
    String bukuName;
    String pengarang;
    String tahunTerbit;
    int stock;
    static int soldCount = 0; // total semua buku yang dipinjam

    Buku(String bukuName, String pengarang, String tahunTerbit, int stock) {
        this.bukuName = bukuName;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.stock = stock;
    }

    public boolean borrow() {
        if (stock > 0) {
            stock--;
            soldCount++;
            return true;
        } else {
            return false;
        }
    }

    public void printProductInfo() {
        System.out.println("Judul Buku   : " + bukuName);
        System.out.println("Pengarang    : " + pengarang);
        System.out.println("Tahun Terbit : " + tahunTerbit);
        System.out.println("Stok Tersisa : " + stock);
    }

    public static int getTotalSold() {
        return soldCount;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Buat beberapa objek buku
        Buku[] daftarBuku = {
            new Buku("Laskar Pelangi", "Andrea Hirata", "2005", 3),
            new Buku("Bumi", "Tere Liye", "2014", 2),
            new Buku("Negeri 5 Menara", "Ahmad Fuadi", "2009", 1)
        };

        int pilihan;
        do {
            System.out.println("\n=== Menu Perpustakaan ===");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Tampilkan Info Buku");
            System.out.println("3. Tampilkan Total Buku yang Dipinjam");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("\nPilih buku untuk dipinjam:");
                    for (int i = 0; i < daftarBuku.length; i++) {
                        System.out.println((i + 1) + ". " + daftarBuku[i].bukuName);
                    }
                    System.out.print("Masukkan pilihan: ");
                    int idx = scanner.nextInt() - 1;

                    if (idx >= 0 && idx < daftarBuku.length) {
                        if (daftarBuku[idx].borrow()) {
                            System.out.println("Buku berhasil dipinjam.");
                        } else {
                            System.out.println("Maaf, stok buku habis.");
                        }
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;

                case 2:
                    System.out.println("\nInfo Buku:");
                    for (Buku buku : daftarBuku) {
                        buku.printProductInfo();
                        System.out.println("----------------------");
                    }
                    break;

                case 3:
                    System.out.println("Total buku yang telah dipinjam: " + Buku.getTotalSold());
                    break;

                case 4:
                    System.out.println("Keluar dari aplikasi...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 4);

        scanner.close();
    }
}
