package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args ){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> list = new ArrayList<>();

        System.out.println("Enter the file path: ");
        String sourceFileStr = sc.nextLine();

        File file = new File(sourceFileStr);
        String sourceFolderStr = file.getParent();

        String targetFileStr = sourceFolderStr + "\\summary.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
            String itemCsv = br.readLine();
            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");
                String name = fields[0];
                double value = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2]);

                list.add(new Product(name, value, quantity));
                itemCsv = br.readLine();
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                for(Product p: list){
                    bw.write(p.getName() + "," + String.format("%.2f", p.finalValue()));
                    bw.newLine();
                }
                System.out.println(targetFileStr + " CREATED");

            } catch (IOException e){
                System.out.println("Error writing file: " + e.getMessage());
            }

        } catch (IOException e){
            System.out.println("Error writing file: " + e.getMessage());
        }

        sc.close();
    }

}