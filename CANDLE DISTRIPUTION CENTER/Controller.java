import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {

    public int numOfProducts = 0;
    Products[] allProducts = new Products[1000];

    public Controller() throws FileNotFoundException {

      readProductFile();
    }

    public boolean addNewProduct(int id, String name, double price, String date) {
        if (this.numOfProducts < this.allProducts.length) {
            ++Products.counter;
            Products p = new Products(id,name,price,date);
            this.allProducts[this.numOfProducts] = p;
            ++this.numOfProducts;
            return true;
        } else {
            return false;
        }
    }
    public Products[] viewAllProducts() {
        return this.allProducts;
    }


    public Products searchForProduct(int id) {
        int i = 0;
        boolean found = false;

        while(!found && i < this.numOfProducts) {
            if (this.allProducts[i].getId() == id) {
                found = true;
            } else {
                ++i;
            }
        }

        return i < this.numOfProducts ? this.allProducts[i] : null;
    }

    public int searchForProductIndex(int id) {
        int i = 0;
        boolean found = false;

        while(!found && i < this.numOfProducts) {
            if (this.allProducts[i].getId() == id) {
                found = true;
            } else {
                ++i;
            }
        }

        return i < this.numOfProducts ? i : -1;
    }

    public boolean DeleteProduct(int id) {
        int index = this.searchForProductIndex(id);
        if (index == -1) {
            return false;
        } else {
            int i;
            for(i = index; i < this.numOfProducts; ++i) {
                this.allProducts[i] = this.allProducts[i + 1];
            }

            this.allProducts[i] = null;
            --this.numOfProducts;
            return true;
        }
    }
    public boolean checkForDuplicate(Products p) {
        for(int i = 0; i < this.numOfProducts; ++i) {
            if (this.allProducts[i].equals(p)) {
                return true;
            }
        }

        return false;
    }

    public boolean editProductData(int id, int newID, String newName, double newPrice, String newDate) {
        int index = this.searchForProductIndex(id);
        if (index != -1) {
            this.allProducts[index].setId(newID);
            this.allProducts[index].setName(newName);
            this.allProducts[index].setPrice(newPrice);
            this.allProducts[index].setDate(newDate);
            return true;
        } else {
            return false;
        }
    }
    public void readProductFile() throws FileNotFoundException {
        Scanner in = new Scanner(new File("D:\\ProductsFile.txt"));

        while(in.hasNext()) {
            int i = in.nextInt();
            String n = in.next();
            double p = Double.parseDouble(in.next());
            String d = in.next();
            this.addNewProduct(i,n, p, d);
        }

        in.close();
    }

    public void writeProductFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("D:\\ProductsFile.txt"));

        for(int i = 0; i < this.numOfProducts; ++i) {
            int v = this.allProducts[i].getId();
            writer.write("" + v + " " + this.allProducts[i].getName() + " " + this.allProducts[i].getPrice() + " " + this.allProducts[i].getDate() + "\n");
        }

        writer.close();
    }
}



