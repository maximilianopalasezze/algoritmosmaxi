package modelo;
import interfaces.Mostrable;
import java.io.Serializable;

public class Cliente implements Mostrable, Serializable {
    private final String rut;
    private final String nombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final String direccion;
    private final int numero;
    private final String comuna;
    private final String telefono;
    private final CuentaBancaria cuenta;

public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
    String direccion, int numero, String comuna, String telefono, CuentaBancaria cuenta) {
    this.rut = rut;
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.direccion = direccion;
    this.numero = numero;
    this.comuna = comuna;
    this.telefono = telefono;
    this.cuenta = cuenta;
}
public String getRut() {
    return rut;
}
public CuentaBancaria getCuenta() {
    return cuenta;
}
@Override
public void mostrarDatos() {
      System.out.println("=== DATOS DEL CLIENTE ===");
      System.out.println("RUT: " + rut);
      System.out.println("Nombre: " + nombre);
      System.out.println("Apellido Paterno: " + apellidoPaterno);
      System.out.println("Apellido Materno: " + apellidoMaterno);
      System.out.println("Dirección: " + direccion + " " + numero + ", " + comuna);
      System.out.println("Teléfono: " + telefono);
      System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta());
      System.out.println("Saldo: " + cuenta.getSaldo());
      cuenta.mostrarTipoCuenta();
}
}