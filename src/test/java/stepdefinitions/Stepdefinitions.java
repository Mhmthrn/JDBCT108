package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;

public class Stepdefinitions {


    //url="databaseType://ipNo:portNo:/databaseName";


    /*
        Database baglantisi icin gerekli bilgiler.

        type: jdbc:mysql
        host/ip: 45.84.206.41
        port:3306
        database: u480337000_tlb_training
        username: u480337000_tbl_training_u
        password: pO9#4bmxU
         */

    Connection connection;

    Statement statement;

    ResultSet resultSet;

    String DBUrl ="jdbc:mysql://45.84.206.41:3306/u480337000_tlb_training";


   String username = "u480337000_tbl_training_u";

   String password = "pO9#4bmxU";

   String istenilencolumn;


    @Given("DB ile connection saglanir")
    public void db_ile_connection_saglanir() throws SQLException {

        connection= DriverManager.getConnection(DBUrl,username,password);

        statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    }



    @Then("Database {string} tablosundaki {string} degeri  {string} olan tüm product'lari listeler")
    public void databaseTablosundakiDegeriOlanTümProductLariListeler(String tableName, String istenilencolumn, String istenenDeger) throws SQLException {

        String query= "select * from "+tableName+" where "+ istenilencolumn+"= "+istenenDeger;

        resultSet=statement.executeQuery(query);

    }
    @Then("Listelenen toplam urun sayisinin {int} den dusuk oldugunu test eder {string} yazdirir")
    public void listelenenToplamUrunSayisininDenDusukOldugunuTestEderYazdirir(int girilenDeger, String istenilencolumn) throws SQLException {
        resultSet.absolute(0);
        int toplamUrunSayiAdedi=1;

        while (resultSet.next()){
            toplamUrunSayiAdedi++;

        }
        Assert.assertTrue(toplamUrunSayiAdedi<girilenDeger);

        resultSet.first();
        System.out.println(resultSet.getString(istenilencolumn));
    }


    @Then("DB ile connection kapatir.")
    public void db_ile_connection_kapatir() throws SQLException {
        connection.close();

    }



}
