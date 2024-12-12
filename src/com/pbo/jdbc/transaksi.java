package com.pbo.jdbc;

import java.util.Scanner;


public class transaksi {
    
    private static String username = "syauqinabiihmarwa";
    private static String password = "syauqinabiihmarwa12";
    private static String captcha = "DwnLod";

    public static void main(String[] args) {
        config.connection();
        
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String un = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();

        if (un.equals(username) && pw.equals(password)) {
           
            System.out.print("Masukkan captcha: ");
            String inputCaptcha = scanner.next();

        
            if (inputCaptcha.equalsIgnoreCase(captcha)) {
                System.out.println("Otentikasi berhasil. Selamat datang, " + un + "!");
                 while(true){

            System.out.print("\n====== MENU ======\n"
            + "1. Tambah Transaksi\n"
            + "2. Lihat Data Barang\n"
            + "3. Hapus Data Barang\n"
            + "4. Update Data Barang\n"
            + "0. Exit\n"
            + "Pilih[1/2/3/4/0] : ");

            Scanner input = new Scanner(System.in);
            String pilihan = input.next();

            if( pilihan.equalsIgnoreCase("0") ){
                System.out.println("Terimakasih!!");
                break;
            }

            switch (pilihan) {
                case "1" :
                    try {
                    
                    view.tambahData();

                } catch (Exception e) {
                    System.err.println("Input tidak valid. Pastikan input sesuai dengan tipe data yang diminta.");
                } finally {
                    System.out.println("===============Kasir===============");
                    String myString = "Nofaldi";
                    String uppercaseString = myString.toUpperCase();
                    System.out.println("Kasir\t\t\t: " + uppercaseString);
                    
                }
                    break;
                case "2" :
                    view.getAllData();
                    break;
                case "3" :
                    view.deleteData();
                    break;
                case "4" :
                    view.updateData();
                    break;
                default:
                    System.out.println("Pilihan salah!!");
                    break;
                    
            }
        input.close ();
        }
           scanner.close();     
            } else {
                System.out.println("Captcha tidak valid. Autentikasi gagal.");
            }
        } else {
            System.out.println("Nama pengguna atau kata sandi tidak valid. Autentikasi gagal.");
        }
    }
    
}
