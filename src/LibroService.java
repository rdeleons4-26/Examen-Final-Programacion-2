import java.util.List;

public class LibroService {

    private LibroDAO dao = new LibroDAO();

    public List<Libro> listarLibros() {
        return dao.listarLibros();
    }

    public List<Libro> listarDestacados() {
        return dao.listarDestacados();
    }

    public boolean actualizarFavorito(int id, int estado) {
        return dao.actualizarFavorito(id, estado);
    }
}