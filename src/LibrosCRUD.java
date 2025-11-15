import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class LibrosCRUD extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtTitulo, txtAnio, txtStock;
    private JComboBox<String> cmbAutor, cmbCategoria;
    private JButton btnAgregar, btnModificar, btnEliminar, btnFavorito, btnListar;

    public LibrosCRUD() {
        setTitle("CRUD Libros");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Labels y campos
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 80, 25);
        add(lblTitulo);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(100, 20, 180, 25);
        add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 60, 80, 25);
        add(lblAutor);
        cmbAutor = new JComboBox<>();
        cmbAutor.setBounds(100, 60, 180, 25);
        add(cmbAutor);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(300, 60, 80, 25);
        add(lblCategoria);
        cmbCategoria = new JComboBox<>();
        cmbCategoria.setBounds(380, 60, 180, 25);
        add(cmbCategoria);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setBounds(20, 100, 80, 25);
        add(lblAnio);
        txtAnio = new JTextField();
        txtAnio.setBounds(100, 100, 80, 25);
        add(txtAnio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(200, 100, 80, 25);
        add(lblStock);
        txtStock = new JTextField();
        txtStock.setBounds(250, 100, 80, 25);
        add(txtStock);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(600, 20, 120, 25);
        btnAgregar.addActionListener(e -> agregarLibro());
        add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(600, 60, 120, 25);
        btnModificar.addActionListener(e -> modificarLibro());
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(600, 100, 120, 25);
        btnEliminar.addActionListener(e -> eliminarLibro());
        add(btnEliminar);

        btnFavorito = new JButton("Marcar como Favorito");
        btnFavorito.setBounds(20, 140, 180, 25);
        btnFavorito.addActionListener(e -> marcarFavorito());
        add(btnFavorito);

        btnListar = new JButton("Listar");
        btnListar.setBounds(220, 140, 120, 25);
        btnListar.addActionListener(e -> cargarTabla());
        add(btnListar);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "Categoría", "Año", "Stock", "Destacado"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 180, 740, 250);
        add(scroll);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked();
            }
        });

        cargarAutores();
        cargarCategorias();
        cargarTabla();
    }

    private void cargarAutores() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre FROM autores")) {

            cmbAutor.removeAllItems();
            while (rs.next()) {
                cmbAutor.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar autores: " + e.getMessage());
        }
    }

    private void cargarCategorias() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre FROM categorias")) {

            cmbCategoria.removeAllItems();
            while (rs.next()) {
                cmbCategoria.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage());
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        String sql = "SELECT l.id, l.titulo, a.nombre AS autor, c.nombre AS categoria, l.anio, l.stock, l.destacado " +
                     "FROM libros l " +
                     "LEFT JOIN autores a ON l.autor_id=a.id " +
                     "LEFT JOIN categorias c ON l.categoria_id=c.id";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String destacado = (rs.getInt("destacado") == 1) ? "Sí" : "No";
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getInt("anio"),
                        rs.getInt("stock"),
                        destacado
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar libros: " + e.getMessage());
        }
    }

    private void agregarLibro() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            String sql = "INSERT INTO libros (titulo, autor_id, categoria_id, anio, stock) " +
                    "VALUES (?, (SELECT id FROM autores WHERE nombre=?), (SELECT id FROM categorias WHERE nombre=?), ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtTitulo.getText());
            ps.setString(2, (String) cmbAutor.getSelectedItem());
            ps.setString(3, (String) cmbCategoria.getSelectedItem());
            ps.setInt(4, Integer.parseInt(txtAnio.getText()));
            ps.setInt(5, Integer.parseInt(txtStock.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro agregado correctamente");
            cargarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar libro: " + e.getMessage());
        }
    }

    private void modificarLibro() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            String sql = "UPDATE libros SET titulo=?, autor_id=(SELECT id FROM autores WHERE nombre=?), " +
                         "categoria_id=(SELECT id FROM categorias WHERE nombre=?), anio=?, stock=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtTitulo.getText());
            ps.setString(2, (String) cmbAutor.getSelectedItem());
            ps.setString(3, (String) cmbCategoria.getSelectedItem());
            ps.setInt(4, Integer.parseInt(txtAnio.getText()));
            ps.setInt(5, Integer.parseInt(txtStock.getText()));
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro modificado correctamente");
            cargarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar libro: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM libros WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Libro eliminado correctamente");
            cargarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar libro: " + e.getMessage());
        }
    }

    private void tablaMouseClicked() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            String destacado = (String) modelo.getValueAt(fila, 6);
            btnFavorito.setText(destacado.equals("Sí") ? "Quitar de Favorito" : "Marcar como Favorito");
        }
    }

    private void marcarFavorito() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro primero.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        String estadoStr = (String) modelo.getValueAt(fila, 6);
        int nuevoEstado = estadoStr.equals("Sí") ? 0 : 1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "2126")) {
            PreparedStatement ps = con.prepareStatement("UPDATE libros SET destacado=? WHERE id=?");
            ps.setInt(1, nuevoEstado);
            ps.setInt(2, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, (nuevoEstado == 1 ? "Libro marcado" : "Libro desmarcado") + " como favorito");
            cargarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar favorito: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LibrosCRUD().setVisible(true);
    }
}