import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataTiket {

    // Class untuk menyimpan data transaksi
    static class Tiket {
        String id;
        String namaPembeli;
        String acara;
        String kelasTiket;
        int jumlah;
        int hargaPerTiket;
        int total;

        // Constructor
        Tiket(String id, String namaPembeli, String acara, String kelasTiket, int jumlah, int hargaPerTiket) {
            this.id = id;
            this.namaPembeli = namaPembeli;
            this.acara = acara;
            this.kelasTiket = kelasTiket;
            this.jumlah = jumlah;
            this.hargaPerTiket = hargaPerTiket;
            this.total = jumlah * hargaPerTiket;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Tiket> daftarTiket = loadTiketFromFile(); // Memuat tiket dari file saat program dijalankan
        boolean programBerjalan = true;

        while (programBerjalan) {
            // Menampilkan menu utama
            System.out.println("\n===== Menu Utama =====");
            System.out.println("1. Tambah Tiket");
            System.out.println("2. Lihat Semua Tiket");
            System.out.println("3. Update Tiket");
            System.out.println("4. Hapus Tiket");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1/2/3/4/5): ");
            int pilihanMenu = input.nextInt();
            input.nextLine(); // Untuk menangani nextLine setelah nextInt()

            switch (pilihanMenu) {
                case 1:
                    tambahTiket(input, daftarTiket);
                    break;
                case 2:
                    lihatSemuaTiket(daftarTiket);
                    break;
                case 3:
                    updateTiket(input, daftarTiket);
                    break;
                case 4:
                    hapusTiket(input, daftarTiket);
                    break;
                case 5:
                    simpanTiketKeFile(daftarTiket); // Menyimpan data tiket sebelum keluar
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

    // Method untuk menambah tiket
    public static void tambahTiket(Scanner input, ArrayList<Tiket> daftarTiket) {
        System.out.println("\n===== Menambah Tiket Baru =====");
        System.out.print("Masukkan Nama Pembeli: ");
        String namaPembeli = input.nextLine();
        System.out.print("Masukkan Acara: ");
        String acara = input.nextLine();
        System.out.print("Masukkan Jenis Tiket (Reguler/VIP): ");
        String kelasTiket = input.nextLine();
        System.out.print("Masukkan Jumlah Tiket: ");
        int jumlah = input.nextInt();
        System.out.print("Masukkan Harga Per Tiket: ");
        int hargaPerTiket = input.nextInt();
        input.nextLine(); // Untuk menangani nextLine setelah nextInt()

        // Membuat ID dengan format RS-999
        String id = generateId(daftarTiket.size() + 1); // Menambahkan ID otomatis berdasarkan nomor urut

        // Menambahkan tiket baru ke daftar
        Tiket tiketBaru = new Tiket(id, namaPembeli, acara, kelasTiket, jumlah, hargaPerTiket);
        daftarTiket.add(tiketBaru);
        System.out.println("Tiket berhasil ditambahkan!");
    }

    // Method untuk melihat semua tiket
    public static void lihatSemuaTiket(ArrayList<Tiket> daftarTiket) {
        if (daftarTiket.isEmpty()) {
            System.out.println("\nTidak ada tiket yang tersimpan.");
            return;
        }

        System.out.println("\n===== Daftar Semua Tiket =====");
        for (Tiket t : daftarTiket) {
            System.out.println("ID: " + t.id);
            System.out.println("Nama Pembeli : " + t.namaPembeli);
            System.out.println("Acara        : " + t.acara);
            System.out.println("Jenis Tiket  : " + t.kelasTiket);
            System.out.println("Jumlah Tiket : " + t.jumlah);
            System.out.println("Harga/Tiket  : Rp " + t.hargaPerTiket);
            System.out.println("Total Bayar  : Rp " + t.total);
            System.out.println("------------------------------");
        }
    }

    // Method untuk update tiket
    public static void updateTiket(Scanner input, ArrayList<Tiket> daftarTiket) {
        System.out.print("\nMasukkan ID tiket yang ingin diupdate: ");
        String idUpdate = input.nextLine();

        Tiket tiketUntukUpdate = null;
        for (Tiket t : daftarTiket) {
            if (t.id.equals(idUpdate)) {
                tiketUntukUpdate = t;
                break;
            }
        }

        if (tiketUntukUpdate == null) {
            System.out.println("Tiket dengan ID " + idUpdate + " tidak ditemukan.");
            return;
        }

        System.out.println("\nTiket yang akan diupdate: ");
        System.out.println("Nama Pembeli : " + tiketUntukUpdate.namaPembeli);
        System.out.println("Acara        : " + tiketUntukUpdate.acara);
        System.out.println("Jenis Tiket  : " + tiketUntukUpdate.kelasTiket);
        System.out.println("Jumlah Tiket : " + tiketUntukUpdate.jumlah);
        System.out.println("Harga/Tiket  : Rp " + tiketUntukUpdate.hargaPerTiket);
        System.out.println("Total Bayar  : Rp " + tiketUntukUpdate.total);

        System.out.println("\nMasukkan data baru untuk tiket ini:");
        System.out.print("Masukkan Nama Pembeli: ");
        tiketUntukUpdate.namaPembeli = input.nextLine();
        System.out.print("Masukkan Acara: ");
        tiketUntukUpdate.acara = input.nextLine();
        System.out.print("Masukkan Jenis Tiket (Reguler/VIP): ");
        tiketUntukUpdate.kelasTiket = input.nextLine();
        System.out.print("Masukkan Jumlah Tiket: ");
        tiketUntukUpdate.jumlah = input.nextInt();
        System.out.print("Masukkan Harga Per Tiket: ");
        tiketUntukUpdate.hargaPerTiket = input.nextInt();
        input.nextLine(); // Untuk menangani nextLine setelah nextInt()

        tiketUntukUpdate.total = tiketUntukUpdate.jumlah * tiketUntukUpdate.hargaPerTiket;
        System.out.println("Tiket berhasil diupdate!");
    }

    // Method untuk menghapus tiket
    public static void hapusTiket(Scanner input, ArrayList<Tiket> daftarTiket) {
        System.out.print("\nMasukkan ID tiket yang ingin dihapus: ");
        String idHapus = input.nextLine();

        Tiket tiketUntukHapus = null;
        for (Tiket t : daftarTiket) {
            if (t.id.equals(idHapus)) {
                tiketUntukHapus = t;
                break;
            }
        }

        if (tiketUntukHapus == null) {
            System.out.println("Tiket dengan ID " + idHapus + " tidak ditemukan.");
            return;
        }

        daftarTiket.remove(tiketUntukHapus);
        System.out.println("Tiket dengan ID " + idHapus + " berhasil dihapus.");
    }

    // Method untuk menyimpan tiket ke file
    public static void simpanTiketKeFile(ArrayList<Tiket> daftarTiket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DataTiket.csv"))) {
            writer.write("ID,Nama Pembeli,Acara,Jenis Tiket,Jumlah,Harga Per Tiket,Total\n");

            for (Tiket t : daftarTiket) {
                writer.write(t.id + "," + t.namaPembeli + "," + t.acara + "," + t.kelasTiket + "," + t.jumlah + "," + t.hargaPerTiket + "," + t.total + "\n");
            }

            System.out.println("Data tiket berhasil disimpan ke file 'DataTiket.csv'!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    // Method untuk memuat tiket dari file
    public static ArrayList<Tiket> loadTiketFromFile() {
        ArrayList<Tiket> daftarTiket = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DataTiket.csv"))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String namaPembeli = data[1];
                String acara = data[2];
                String kelasTiket = data[3];
                int jumlah = Integer.parseInt(data[4]);
                int hargaPerTiket = Integer.parseInt(data[5]);
                int total = Integer.parseInt(data[6]);

                daftarTiket.add(new Tiket(id, namaPembeli, acara, kelasTiket, jumlah, hargaPerTiket));
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data dari file: " + e.getMessage());
        }
        return daftarTiket;
    }

    // Method untuk menghasilkan ID dengan format RS-999
    public static String generateId(int urutan) {
        return "RS-" + String.format("%03d", urutan); // Format ID seperti RS-001, RS-002, dll.
    }
}


