import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Membuat objek Scanner untuk membaca input dari pengguna
        Scanner input = new Scanner(System.in);

        // Meminta input angka pertama dari pengguna
        System.out.print("Masukkan angka pertama: ");
        double angka1 = input.nextDouble(); // Menyimpan angka pertama

        // Meminta input angka kedua dari pengguna
        System.out.print("Masukkan angka kedua: ");
        double angka2 = input.nextDouble(); // Menyimpan angka kedua

        // Meminta input operator dari pengguna (+, -, *, /)
        System.out.print("Pilih operasi (+, -, *, /): ");
        char operator = input.next().charAt(0); // Membaca karakter pertama dari input

        double hasil; // Variabel untuk menyimpan hasil perhitungan

        // Menggunakan switch-case untuk menentukan operasi yang dipilih
        switch (operator) {
            case '+':
                hasil = angka1 + angka2; // Penjumlahan
                System.out.println("Hasil: " + angka1 + " + " + angka2 + " = " + hasil);
                break;

            case '-':
                hasil = angka1 - angka2; // Pengurangan
                System.out.println("Hasil: " + angka1 + " - " + angka2 + " = " + hasil);
                break;

            case '*':
                hasil = angka1 * angka2; // Perkalian
                System.out.println("Hasil: " + angka1 + " * " + angka2 + " = " + hasil);
                break;

            case '/':
                if (angka2 != 0) {
                    hasil = angka1 / angka2; // Pembagian, jika angka kedua bukan nol
                    System.out.println("Hasil: " + angka1 + " / " + angka2 + " = " + hasil);
                } else {
                    // Menangani error pembagian dengan nol
                    System.out.println("Error: Pembagian dengan nol tidak diperbolehkan.");
                }
                break;

            default:
                // Menangani operator yang tidak valid
                System.out.println("Operator tidak valid! Silakan gunakan +, -, *, atau /.");
                break;
        }

        // Menutup scanner setelah selesai digunakan
        input.close();
    }
}
