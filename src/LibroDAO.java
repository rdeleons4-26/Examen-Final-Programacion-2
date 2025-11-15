import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private final String USER = "root";
    private final String PASS = "2126";

    // Marcar o desmarcar favorito
    public boolean actualizarFavorito(int id, int estado) {
        String sql = "UPDATE libros SET destacado=? WHERE id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo, a.nombre AS autor, c.nombre AS categoria, " +
                     "l.anio, l.stock, l.destacado " +
                     "FROM libros l " +
                     "LEFT JOIN autores a ON l.autor_id=a.id " +
                     "LEFT JOIN categorias c ON l.categoria_id=c.id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setAnio(rs.getInt("anio"));
                libro.setStock(rs.getInt("stock"));
                libro.setDestacado(rs.getInt("destacado"));
                lista.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Listar solo libros destacados
    public List<Libro> listarDestacados() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo, a.nombre AS autor, c.nombre AS categoria, " +
                     "l.anio, l.stock, l.destacado " +
                     "FROM libros l " +
                     "LEFT JOIN autores a ON l.autor_id=a.id " +
                     "LEFT JOIN categorias c ON l.categoria_id=c.id " +
                     "WHERE l.destacado=1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setAnio(rs.getInt("anio"));
                libro.setStock(rs.getInt("stock"));
                libro.setDestacado(rs.getInt("destacado"));
                lista.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}