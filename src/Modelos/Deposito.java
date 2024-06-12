package Modelos;

public class Deposito {
    private int id;
    private int stock;
    private String direccion;

    public Deposito(int id, int stock, String direccion) {
        this.id = id;
        this.stock = stock;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Deposito [id=" + id + ", stock=" + stock + ", direccion=" + direccion + "]";
    }
}
