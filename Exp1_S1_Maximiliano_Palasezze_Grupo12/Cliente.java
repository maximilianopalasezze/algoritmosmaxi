package Exp1_S1_Maximiliano_Palasezze_Grupo12;

public class Cliente {
  private String rut;
  private final String nombre;
  private final String apellidoPaterno;
  private final String apellidoMaterno;
  private final String domicilio;
  private final String comuna;
  private final String telefono;
  private final Cuenta cuenta;

public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String comuna, String telefono, int numeroCuenta) {
  if (rut.length() >= 11 && rut.length() <= 12) {
  this.rut = rut;
} else {
  throw new IllegalArgumentException("El RUT debe tener puntos y digito verificador .");
}
  this.nombre = nombre;
  this.apellidoPaterno = apellidoPaterno;
  this.apellidoMaterno = apellidoMaterno;
  this.domicilio = domicilio;
  this.comuna = comuna;
  this.telefono = telefono;
  this.cuenta = new Cuenta(numeroCuenta);
}
public Cuenta getCuenta() {
  return cuenta;
}
public void verDatosCliente() {
      System.out.println("RUT: " + rut);
      System.out.println("Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
      System.out.println("Domicilio: " + domicilio + ", " + comuna);
      System.out.println("Teléfono: " + telefono);
      System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta());
}
}