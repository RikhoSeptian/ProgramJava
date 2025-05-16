public class App {
    String productName;
    double price;
    int stock;
    static int soldCount = 0; // total semua item yang terjual dari semua produk

    public App(String productName, double price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    // Metode untuk membeli produk
    public double buy(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
            soldCount += quantity;
            return price * quantity;
        } else {
            return -1; // Indikasi stok tidak cukup
        }
    }

    // Menampilkan informasi produk
    public void printProductInfo() {
        System.out.println("Nama Produk : " + productName);
        System.out.println("Harga       : " + price);
        System.out.println("Stok        : " + stock);
    }

    // Mendapatkan total item yang terjual
    public static int getTotalSold() {
        return soldCount;
    }
}
