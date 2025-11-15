public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private int stock;
    private int destacado; // 0 = no, 1 = sí

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getDestacado() { return destacado; }
    public void setDestacado(int destacado) { this.destacado = destacado; }
}