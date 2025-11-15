import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LibrosDestacados extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private LibroService servicio = new LibroService();

    public LibrosDestacados() {
        setTitle("Libros Destacados");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"ID","Título","Autor","Categoría","Año","Stock"},0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 20, 700, 250);
        add(scroll);

        JButton btnQuitar = new JButton("Quitar de Favoritos");
        btnQuitar.setBounds(20, 290, 200, 30);
        btnQuitar.addActionListener(e -> quitarFavorito());
        add(btnQuitar);

        actualizarTabla();
    }

    public void actualizarTabla() {
        modelo.setRowCount(0);
        List<Libro> lista = servicio.listarDestacados();
        for(Libro l : lista){
            modelo.addRow(new Object[]{l.getId(), l.getTitulo(), l.getAutor(), l.getCategoria(),
                    l.getAnio(), l.getStock()});
        }
    }

    private void quitarFavorito() {
        int fila = tabla.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(this, "Seleccione un libro.");
            return;
        }
        int id = (int) modelo.getValueAt(fila,0);
        if(servicio.actualizarFavorito(id,0)){
            JOptionPane.showMessageDialog(this,"Libro quitado de favoritos.");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this,"Error al actualizar.");
        }
    }

    public static void main(String[] args) {
        new LibrosDestacados().setVisible(true);
    }
}