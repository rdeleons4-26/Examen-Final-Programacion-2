import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CategoriasCRUD extends JFrame {

    private JTable tablaCategorias;
    private DefaultTableModel modelo;
    private JTextField txtNombre;
    private JButton btnAgregar, btnModificar, btnEliminar, btnListar;

    public CategoriasCRUD() {
        setTitle("CATEGORIAS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 25);
        add(txtNombre);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(320, 20, 100, 25);
        add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(430, 20, 100, 25);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(320, 60, 100, 25);
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(430, 60, 100, 25);
        add(btnListar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaCategorias = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaCategorias);
        scrollPane.setBounds(20, 100, 540, 200);
        add(scrollPane);

        // eventos de los botones
        btnAgregar.addActionListener(e -> agregarCategoria());
        btnListar.addActionListener(e -> cargarCategorias());
        btnEliminar.addActionListener(e -> eliminarCategoria());
        btnModificar.addActionListener(e -> modificarCategoria());

        // Cargar datos al iniciar
        cargarCategorias();
    }

    // METODOS CRUD 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC",
                "root",
                "2126"
        );
    }

    private void agregarCategoria() {
        String nombre = txtNombre.getText();

        if (txtNombre.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingresa un nombre de categoria.");
        return;
        }

        try (Connection conn = conectar()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO categorias (nombre) VALUES (?)");
            ps.setString(1, nombre);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Categoria agregada correctamente.");
            cargarCategorias();
            txtNombre.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar categoria: " + e.getMessage());
        }
    }

    private void cargarCategorias() {
        modelo.setRowCount(0);
        try (Connection conn = conectar()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nombre FROM categorias");
            while (rs.next()) {
                Object[] fila = {rs.getInt("id"), rs.getString("nombre")};
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorias: " + e.getMessage());
        }
    }

    private void eliminarCategoria() {
        int fila = tablaCategorias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoria para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar esta categoria?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) return;

        try (Connection conn = conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM categorias WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Categoria eliminada correctamente.");
            cargarCategorias();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar categoria: " + e.getMessage());
        }
    }

    private void modificarCategoria() {
        int fila = tablaCategorias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoria para modificarla.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        String nuevoNombre = txtNombre.getText();

        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nuevo nombre de la categoria.");
            return;
        }

        try (Connection conn = conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE categorias SET nombre = ? WHERE id = ?");
            ps.setString(1, nuevoNombre);
            ps.setInt(2, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Categoria modificada correctamente.");
            cargarCategorias();
            txtNombre.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar categoria: " + e.getMessage());
        }
    }

    // MAIN para probarlo independiente
    public static void main(String[] args) {
        new CategoriasCRUD().setVisible(true);
    }
}