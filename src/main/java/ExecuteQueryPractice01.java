import java.sql.*;

public class ExecuteQueryPractice01 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_Practice", "techpro", "password");

        Statement statement = con.createStatement();

        // 1-  ID 1-50 arası ülkeler
        System.out.println("SORU -1 ------------");

        String sql1 = "SELECT name FROM countries_v2 WHERE id BETWEEN 1 AND 50";
        boolean query1 = statement.execute(sql1);
        System.out.println("sql1 = " + query1);

        ResultSet rs = statement.executeQuery(sql1);
        while (rs.next()) {
            System.out.println("Ülke adı: " + rs.getString("name"));
            System.out.println("Ülke adı: " + rs.getString(1));// Bu da olur.
        }
// 2-ID 50'den büyük ülkeler
        System.out.println("SORU -2 ------------");

        String sql2 = "SELECT phone_code,name FROM countries_v2 WHERE phone_code>50 ORDER BY phone_code";// ORDER BY ile sıraladık.
        ResultSet rs2 = statement.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println("Ülke adı: " + rs2.getString("name") +
                    "Telefon Kodu: " + rs2.getString("phone_code"));//getString yerine getInt
            // yazarsak sayısal değerler int gelir
        }
//3- salary minimum olan developerlar, developers tablosundan.
        System.out.println("SORU -3 ------------");

        ResultSet rs3 = statement.executeQuery("SELECT * FROM developers_v2 WHERE monthly_salary = (SELECT MIN(monthly_salary) FROM developers_v2)");

        while (rs3.next()) {
            System.out.println("----isim: " + rs3.getString("full_name") +
                    "----maas: " + rs3.getDouble("monthly_salary") +
                    "------prog lang: " + rs3.getString("language_specialty"));
        }
        //SELECT full_name,monthly salary, language_specialty FROM developers_v2 WHERE monthly_salary >4500;
        System.out.println("SORU -4 ------------");

        String sql4 = "SELECT full_name,monthly_salary,language_specialty FROM developers_v2 WHERE monthly_salary >5000;";
        ResultSet rs4 = statement.executeQuery(sql4);
        while (rs4.next()) {
            System.out.println("aylık maas = " + rs4.getString("monthly_salary"));
        }

        // En yüksek maaşlı geliştiriciyi listele:
        System.out.println("SORU -5 ------------");

        ResultSet rs5 = statement.executeQuery(
                "SELECT full_name, monthly_salary, language_specialty " +
                        "FROM developers_v2 " +
                        "WHERE monthly_salary = (SELECT MAX(monthly_salary) FROM developers_v2)"
        );

        while (rs5.next()) {
            System.out.println("Isim : " + rs5.getString("full_name") +
                    ", Aylık maas: " + rs5.getDouble("monthly_salary") +
                    ", Uzmanlık alanı: " + rs5.getString("language_specialty"));
        }

    }
}

