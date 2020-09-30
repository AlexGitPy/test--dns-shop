import java.sql.*;

public class Postgre {


    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "1234";
        final String url = "jdbc:postgresql://localhost:5432/dns_test";

        final Connection connection = DriverManager.getConnection(url, user, password);


        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dns_shop WHERE id = (?)")) {

            statement.setInt(1, 4);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                final int UI_ID = resultSet.getInt(2);
                String byName = "name: " + resultSet.getString(3);
                final int price = resultSet.getInt(4);
                final float rating = resultSet.getInt(5);
                System.out.println("ui_id: " + UI_ID);
                System.out.println(byName);
                System.out.println("price: " + price);
                System.out.println("rating: " + rating);
            }
        } finally {
            connection.close();
        }

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dns_shop WHERE id = (?)")) {

            statement.setInt(1, 4);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                final int UI_ID = resultSet.getInt(2);
                String byName = "name: " + resultSet.getString(3);
                final int price = resultSet.getInt(4);
                final float rating = resultSet.getInt(5);
                System.out.println("ui_id: " + UI_ID);
                System.out.println(byName);
                System.out.println("price: " + price);
                System.out.println("rating: " + rating);
            }
        } finally {
            connection.close();
        }

        try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM dns_shop ORDER BY MAX(price)")) {

//            statement.setInt(1, 0);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                final int UI_ID = resultSet.getInt(2);
                String byName = "name: " + resultSet.getString(3);
                final int price = resultSet.getInt(4);
                final float rating = resultSet.getInt(5);
                System.out.println("ui_id: " + UI_ID);
                System.out.println(byName);
                System.out.println("price: " + price);
                System.out.println("rating: " + rating);
            }
        } finally {
            connection.close();
        }
    }

}
