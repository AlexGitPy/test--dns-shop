import java.sql.*;

public class GenerateValue {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Получить подключение к базе данных.
        final String user = "postgres";
        final String password = "1234";
        final String url = "jdbc:postgresql://localhost:5432/dns_test";

        final Connection conn = DriverManager.getConnection(url, user, password);

        // Employees (id, full_name, gender, hire_date)
        // ID: Auto Increase
        String sql = "Insert into dns_shop " //
                + " (ui_id, name, price, rating) " //
                + " values " //
                + " (?, ?, ?, ?)";

        // Создать объект PreparedStatement.
        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 1; i <= 5; i++) {

            pstm.setInt(1, 2);
            pstm.setString(2, "gjhjkjkkl");
            pstm.setInt(3, 4042499);
            pstm.setDouble(4,4.9);

            // Execute!
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            int idValue = 0;
            if (rs.next()) {
                // Value of ID.
                // Note, for some DB, column names are case sensitive.
                // (eg Postgres, column names are always lowercase).
                idValue = rs.getInt("id");
            }

            System.out.println("ID value: " + idValue);

        }

//        pstm.setInt(1, 2);
//        pstm.setInt(1, 2);
//        pstm.setString(2, "gjhjkjkkl");
//        pstm.setInt(3, 4042499);
//        pstm.setDouble(4,4.9);
//
//        // Execute!
//        pstm.execute();
//
//        ResultSet rs = pstm.getGeneratedKeys();
//
//        int idValue = 0;
//        if (rs.next()) {
//            // Value of ID.
//            // Note, for some DB, column names are case sensitive.
//            // (eg Postgres, column names are always lowercase).
//            idValue = rs.getInt("id");
//        }
//
//        System.out.println("ID value: " + idValue);

    }
}
