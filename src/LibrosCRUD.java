import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class LibrosCRUD extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtTitulo, txtAnio, txtStock;
    private JComboBox<String> cmbAutor, cmbCategoria;
    private JButton btnAgregar, btnModificar, btnEliminar, btnListar;

    public LibrosCRUD() {
        setTitle("LIBROS");
        setSize(750, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 30, 100, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 30, 200, 25);
        add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(30, 70, 100, 25);
        add(lblAutor);

        cmbAutor = new JComboBox<>();
        cmbAutor.setBounds(120, 70, 200, 25);
        add(cmbAutor);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(30, 110, 100, 25);
        add(lblCategoria);

        cmbCategoria = new JComboBox<>();
        cmbCategoria.setBounds(120, 110, 200, 25);
        add(cmbCategoria);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setBounds(30, 150, 100, 25);
        add(lblAnio);

        txtAnio = new JTextField();
        txtAnio.setBounds(120, 150, 200, 25);
        add(txtAnio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(30, 190, 100, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 190, 200, 25);
        add(txtStock);

        // BOTONES
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(350, 30, 120, 25);
        btnAgregar.addActionListener(e -> agregarLibro());
        add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(350, 70, 120, 25);
        btnModificar.addActionListener(e -> modificarLibro());
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350, 110, 120, 25);
        btnEliminar.addActionListener(e -> eliminarLibro());
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(350, 150, 120, 25);
        btnListar.addActionListener(e -> listarLibros());
        add(btnListar);

        modelo = new DefaultTableModel(new String[]{"ID", "Titulo", "Autor", "Categoria", "Año", "Stock"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 240, 670, 180);
        add(scroll);

        cargarAutores();
        cargarCategorias();
        listarLibros();
    }

   
    private void cargarAutores(){
       
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre FROM autores")) {
            cmbAutor.removeAllItems();
            while (rs.next()) cmbAutor.addItem(rs.getString("nombre"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar autores: " + e.getMessage());
        }
    }

    // cargar categorias
    private void cargarCategorias() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre FROM categorias")) {
            cmbCategoria.removeAllItems();
            while (rs.next()) cmbCategoria.addItem(rs.getString("nombre"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage());
        }
    }

    private void agregarLibro() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            String sql = "INSERT INTO libros (titulo, autor_id, categoria_id, anio, stock) " +
                    "VALUES (?, (SELECT id FROM autores WHERE nombre=?), (SELECT id FROM categorias WHERE nombre=?), ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtTitulo.getText());
            ps.setString(2, (String) cmbAutor.getSelectedItem());
            ps.setString(3, (String) cmbCategoria.getSelectedItem());
            ps.setInt(4, Integer.parseInt(txtAnio.getText()));
            ps.setInt(5, Integer.parseInt(txtStock.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro agregado correctamente");
            listarLibros();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + e.getMessage());
        }
    }

    private void modificarLibro() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            String sql = "UPDATE libros SET titulo=?, autor_id=(SELECT id FROM autores WHERE nombre=?), " +
                    "categoria_id=(SELECT id FROM categorias WHERE nombre=?), anio=?, stock=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtTitulo.getText());
            ps.setString(2, (String) cmbAutor.getSelectedItem());
            ps.setString(3, (String) cmbCategoria.getSelectedItem());
            ps.setInt(4, Integer.parseInt(txtAnio.getText()));
            ps.setInt(5, Integer.parseInt(txtStock.getText()));
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro modificado correctamente");
            listarLibros();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar datos: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM libros WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro eliminado correctamente");
            listarLibros();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
        }
    }

    private void listarLibros() {
        modelo.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT l.id, l.titulo, a.nombre AS autor, c.nombre AS categoria, l.anio, l.stock " +
                             "FROM libros l " +
                             "LEFT JOIN autores a ON l.autor_id=a.id " +
                             "LEFT JOIN categorias c ON l.categoria_id=c.id")) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getInt("anio"),
                        rs.getInt("stock")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LibrosCRUD().setVisible(true);
    }
}