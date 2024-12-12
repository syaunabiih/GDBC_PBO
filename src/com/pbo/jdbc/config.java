package com.pbo.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class config {

  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/faktur";
  private static final String USER = "root";
  private static final String PASS = "";

  private static Connection connect;
  private static Statement statement;
  private static ResultSet resultData;

  // ini adalah method static connection
  public static void connection()
  
  {
    // method untuk melakukan koneksi ke database
    try {
      // registrasi driver yang akan dipakai
      Class.forName(JDBC_DRIVER);

      // buat koneksi ke database
      connect = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (Exception e) {
      // kalo ada error saat koneksi
      // maka tampilkan errornya
      e.printStackTrace();
    }

  }

  public static String getAllData()
  {
    config.connection();

    // isi nilai default dari variabel data
    String data = "Maaf data tidak ada";

    try {

      // buat object statement yang ambil dari koneksi
      statement = connect.createStatement();

      // query select all data from database
      String query = "SELECT no_faktur, nama_barang,harga_barang FROM transaksi";

      // eksekusi query-nya
      resultData = statement.executeQuery(query);

      // set variabel data jadi null
      data = "";

      // looping pengisian variabel data
      while( resultData.next() ){
        data += "no_faktur : " + resultData.getInt("no_faktur") + "| Nama Barang : " + resultData.getString("nama_barang")+ "| Harga : " + resultData.getString("harga_barang") + "\n";
      }
      
      
      // close statement dan connection
      statement.close();
      connect.close();


    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;

  }

public static boolean tambahData( String invoiceNumber, String customerName, String itemName, double price, int quantity, String NoHP, String alamat )
  {
    config.connection();
    boolean data = false;

    try {

      statement = connect.createStatement();

      String query = "INSERT INTO transaksi VALUES ('" + invoiceNumber + "', '" + customerName + "', '" + NoHP + "', '" + alamat + "', '" + itemName + "', " + price + ", " + quantity + ")";


      if( !statement.execute(query) ){
        data = true;
      }


      // close statement dan koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

public static boolean deleteData( int invoiceNumber )
  {
    connection();
    boolean data = false;

    try {
      
      statement = connect.createStatement();

      String query = "DELETE FROM transaksi WHERE no_faktur = " + invoiceNumber;
      //# String query = "UPDATE transaksi SET isActive = '0' WHERE no_faktur = " + invoiceNumber;

      if( !statement.execute(query) ){
        data = true;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }



public static boolean updateData( int invoiceNumber, String itemName, int quantity, int price )
  {

    config.connection();
    boolean data = false;

    try {

      statement = connect.createStatement();

      String queryCek = "SELECT * FROM transaksi WHERE no_faktur = " + invoiceNumber;

      resultData = statement.executeQuery(queryCek);
      // siapin variabel untuk menampung data pada fild satu row
      String namaCek = "";
      int stokCek = 0, hargaCek = 0;

      while( resultData.next() ){
        namaCek = resultData.getString("nama_barang");
        stokCek = resultData.getInt("jumlah_barang");
        hargaCek = resultData.getInt("harga_barang");
      }

      // validasi jika yang diisi diconsole kosong
      if( !itemName.equalsIgnoreCase("") ){
        namaCek = itemName;
      }
      if( quantity != 0 ){
        stokCek = quantity;
      }
      if( price != 0 ){
        hargaCek = price;
      }

      String queryUpdate = "UPDATE transaksi SET nama_barang = '" + namaCek + "', jumlah_barang = " + stokCek + ", harga_barang = " + hargaCek + " WHERE no_faktur = " + invoiceNumber ;
      
      if( !statement.execute(queryUpdate) ){
        data = true;
      }else{
        data = false;
      }

      // close statement dan close koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }


    return data;
  }
}



