import java.sql.*;

public class ExecuteUpdatePractice01 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_Practice", "techpro", "password");

        Statement statement = con.createStatement();

        //  ÖRNEK1:developers tablosunda maaşı ortalama maaştan az
        //  olanların maaşını ortalama maaş ile güncelleyiniz.
        System.out.println("-------------UPDATE'den önce----------");

        ResultSet rs = statement.executeQuery("SELECT * FROM developers_v2");
        while (rs.next()){
            System.out.println("id: "+ rs.getInt("developer_id")+
                    " İsim: " + rs.getString("full_name")+ " aylık maaş "+
                    rs.getInt("monthly_salary")+ " Programlama dili: "
                    + rs.getString("language_specialty"));
        }
        String sql1 = "UPDATE developers_v2 SET monthly_salary=( SELECT AVG(monthly_salary)FROM developers_v2)"+
                "WHERE monthly_salary < (SELECT AVG(monthly_salary) FROM developers_v2)" ;
        //aylık maaşı ortalama maaşa eşitle , eğer developerin maaşı ortalama maaştan düşük ise.

        int updated =statement.executeUpdate(sql1);// güncellenen değer sayısını verir.
        System.out.println("güncellenen maaş sayısı: " + updated);
        System.out.println("-----UPDATE'den sonra--------");
        ResultSet rs2 = statement.executeQuery("SELECT * FROM developers_v2");

        while (rs2.next()) {
            System.out.println("id: " + rs2.getInt("developer_id") +
                    " İsim: " + rs2.getString("full_name") + " aylık maaş " +
                    rs2.getInt("monthly_salary") + " Programlama dili: "
                    + rs2.getString("language_specialty"));
        }
        }

}
