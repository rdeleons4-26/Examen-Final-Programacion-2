import java.sql.*;

public class Conexion {

    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
        String user = "root";           
        String pass = "2126";           
        return DriverManager.getConnection(url, user, pass);
    }
}
