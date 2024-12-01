import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutePractice01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

       Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_Practice","techpro","password");

        Statement statement = con.createStatement();

        System.out.println("Succes");

//Sorgu oluşturma
        //Tablo oluşturma ,sütün ekleme
        
        //DDL-false veri yok
        //DQL-true veri var
boolean sql1 = statement.execute("CREATE TABLE if NOT EXISTS workers(worker_id INT,worker_name VARCHAR(50),salary REAL )");
        System.out.println("sql1 = " + sql1);//tablo oluştu ,tekrar eklenemez.
        //IF NOT EXISTS tablo varsa tekar oluşturmak için işlem yapmaz.

//Tabloya sütun ekleme ALTER TABLE

      //  boolean sql2 = statement.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");
      //  System.out.println("sql2 = " + sql2);

//Tablo silme

        statement.execute("DROP TABLE workers");

        //Kaynakları kapatma
        statement.close();
        con.close();




        
        
        
        
        
    }

}
