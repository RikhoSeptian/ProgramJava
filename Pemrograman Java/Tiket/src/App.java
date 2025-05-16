import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    // Class untuk menyimpan data transaksi
    static class Transaksi {
        String idTiket;
        String namaPembeli;
        String acara;
        String kelasTiket;
        int jumlah;
        int hargaPerTiket;
        int total;

        // Constructor
        Transaksi(String idTiket, String namaPembeli, String acara, String kelasTiket, int jumlah, int hargaPerTiket) {
            this.idTiket = idTiket;
            this.namaPembeli = namaPembeli;
            this.acara = acara;
            this.kelasTiket = kelasTiket;
            this.jumlah = jumlah;
            this.hargaPerTiket = hargaPerTiket;
            this.total = jumlah * hargaPerTiket;
        }

        // Method untuk format data tiket ke CSV
        public String toCSV() {
            return idTiket + "," + namaPembeli + "," + acara + "," + kelasTiket + "," + jumlah + "," + hargaPerTiket + "," + total;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Transaksi> daftarTransaksi = loadTransaksiFromFile(); // Memuat transaksi dari file

        // Data acara dan harga tiket
        String[] acara = {"Bioskop", "Konser", "Pertandingan Bola", "Teater", "Seminar"};
        int[] hargaReguler = {35000, 75000, 100000, 60000, 50000};
        int[] hargaVIP = {50000, 120000, 150000, 100000, 80000};

        boolean programBerjalan = true;

        while (programBerjalan) {
            // Menampilkan menu utama
            System.out.println("\n===== Menu Utama =====");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Semua Transaksi");
            System.out.println("3. Lihat Daftar Harga");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1/2/3/4): ");
            int pilihanMenu = input.nextInt();

            switch (pilihanMenu) {
                case 1:
                    tambahTransaksi(input, acara, hargaReguler, hargaVIP, daftarTransaksi);
                    break;
                case 2:
                    lihatSemuaTransaksi(daftarTransaksi);
                    break;
                case 3:
                    lihatDaftarHarga(acara, hargaReguler, hargaVIP);
                    break;
                case 4:
                    simpanTransaksiKeFile(daftarTransaksi); // Menyimpan transaksi sebelum keluar
                    programBerjalan = false;
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
                    break;
            }
        }

        input.close();
    }

    // Method untuk menambah transaksi
    public static void tambahTransaksi(Scanner input, String[] acara, int[] hargaReguler, int[] hargaVIP, ArrayList<Transaksi> daftarTransaksi) {
        // Menampilkan daftar acara
        System.out.println("\n===== Penjualan Tiket Acara =====");
        for (int i = 0; i < acara.length; i++) {
            System.out.println((i + 1) + ". " + acara[i]);
        }

        // Input acara
        System.out.print("Pilih acara (1-5): ");
        int pilihanAcara = input.nextInt();
        if (pilihanAcara < 1 || pilihanAcara > acara.length) {
            System.out.println("Pilihan acara tidak valid!");
            return;
        }

        // Input jenis tiket
        System.out.print("Pilih jenis tiket (Reguler/VIP): ");
        String kelasTiket = input.next();

        // Input jumlah tiket
        System.out.print("Masukkan jumlah tiket: ");
        int jumlah = input.nextInt();

        input.nextLine(); // consume newline
        System.out.print("Masukkan nama pembeli: ");
        String namaPembeli = input.nextLine();

        int hargaPerTiket;
        if (kelasTiket.equalsIgnoreCase("Reguler")) {
            hargaPerTiket = hargaReguler[pilihanAcara - 1];
        } else if (kelasTiket.equalsIgnoreCase("VIP")) {
            hargaPerTiket = hargaVIP[pilihanAcara - 1];
        } else {
            System.out.println("Jenis tiket tidak valid!");
            return;
        }

        // Generate ID tiket
        String idTiket = generateIdTiket();

        // Buat dan simpan transaksi
        Transaksi transaksiBaru = new Transaksi(
                idTiket,
                namaPembeli,
                acara[pilihanAcara - 1],
                kelasTiket,
                jumlah,
                hargaPerTiket
        );
        daftarTransaksi.add(transaksiBaru);
        System.out.println("Transaksi berhasil ditambahkan!");
    }

    // Method untuk melihat semua transaksi
    public static void lihatSemuaTransaksi(ArrayList<Transaksi> daftarTransaksi) {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("\nTidak ada transaksi yang tersimpan.");
            return;
        }

        System.out.println("\n===== Ringkasan Semua Transaksi =====");
        int totalSemua = 0;
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            Transaksi t = daftarTransaksi.get(i);
            System.out.println("\nTransaksi #" + (i + 1));
            System.out.println("ID Tiket     : " + t.idTiket);
            System.out.println("Nama Pembeli : " + t.namaPembeli);
            System.out.println("Acara        : " + t.acara);
            System.out.println("Jenis Tiket  : " + t.kelasTiket);
            System.out.println("Jumlah Tiket : " + t.jumlah);
            System.out.println("Harga/Tiket  : Rp " + t.hargaPerTiket);
            System.out.println("Total Bayar  : Rp " + t.total);
            totalSemua += t.total;
        }

        System.out.println("\nTotal seluruh transaksi: Rp " + totalSemua);
    }

    // Method untuk melihat daftar harga tiket
    public static void lihatDaftarHarga(String[] acara, int[] hargaReguler, int[] hargaVIP) {
        System.out.println("\n===== Daftar Harga Tiket =====");
        for (int i = 0; i < acara.length; i++) {
            System.out.println(acara[i] + ":");
            System.out.println("  - Reguler: Rp " + hargaReguler[i]);
            System.out.println("  - VIP    : Rp " + hargaVIP[i]);
        }
    }

    // Method untuk menyimpan transaksi ke file CSV
    public static void simpanTransaksiKeFile(ArrayList<Transaksi> daftarTransaksi) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TransaksiTiket.csv"))) {
            writer.write("ID,Nama Pembeli,Acara,Jenis Tiket,Jumlah,Harga Per Tiket,Total\n");

            for (Transaksi t : daftarTransaksi) {
                writer.write(t.toCSV() + "\n");
            }

            System.out.println("Data transaksi berhasil disimpan ke file 'TransaksiTiket.csv'!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    // Method untuk memuat transaksi dari file CSV
    public static ArrayList<Transaksi> loadTransaksiFromFile() {
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("TransaksiTiket.csv"))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue; // skip if invalid format
                String idTiket = data[0];
                String namaPembeli = data[1];
                String acara = data[2];
                String kelasTiket = data[3];
                int jumlah = Integer.parseInt(data[4]);
                int hargaPerTiket = Integer.parseInt(data[5]);

                daftarTransaksi.add(new Transaksi(idTiket, namaPembeli, acara, kelasTiket, jumlah, hargaPerTiket));
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data dari file: " + e.getMessage());
        }
        return daftarTransaksi;
    }

    // Method untuk generate ID tiket baru
    public static String generateIdTiket() {
        Random rand = new Random();
        int id = rand.nextInt(1000) + 100;
        return "RS-" + id;
    }
}
