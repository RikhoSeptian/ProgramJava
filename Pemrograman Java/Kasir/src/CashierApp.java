import java.util.Scanner;

public class CashierApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat beberapa produk
        App[] products = {
            new App("Roti", 5000, 10),
            new App("Air Mineral", 3000, 20),
            new App("Susu", 8000, 15)
        };

        int choice;
        do {
            System.out.println("\n=== Menu Kasir ===");
            System.out.println("1. Beli Produk");
            System.out.println("2. Tampilkan Info Produk");
            System.out.println("3. Tampilkan Total Item Terjual");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nPilih produk:");
                    for (int i = 0; i < products.length; i++) {
                        System.out.println((i + 1) + ". " + products[i].productName);
                    }
                    System.out.print("Masukkan nomor produk: ");
                    int productIndex = scanner.nextInt() - 1;

                    if (productIndex >= 0 && productIndex < products.length) {
                        System.out.print("Masukkan jumlah yang ingin dibeli: ");
                        int qty = scanner.nextInt();

                        double totalHarga = products[productIndex].buy(qty);
                        if (totalHarga != -1) {
                            System.out.println("Total harga: Rp" + totalHarga);
                        } else {
                            System.out.println("Stok tidak cukup!");
                        }
                    } else {
                        System.out.println("Pilihan produk tidak valid.");
                    }
                    break;

                case 2:
                    System.out.println("\nInformasi Produk:");
                    for (App p : products) {
                        p.printProductInfo();
                        System.out.println("--------------------");
                    }
                    break;

                case 3:
                    System.out.println("Total item terjual: " + App.getTotalSold());
                    break;

                case 4:
                    System.out.println("Terima kasih! Keluar dari program...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
