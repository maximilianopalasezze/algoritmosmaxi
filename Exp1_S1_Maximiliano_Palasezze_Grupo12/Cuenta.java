package Exp1_S1_Maximiliano_Palasezze_Grupo12;

public class Cuenta {
  private final int numeroCuenta;
  private int saldo;
  
public Cuenta(int numeroCuenta) {
  this.numeroCuenta = numeroCuenta;
  this.saldo = 0; // saldo inicial en 0
}
public int getNumeroCuenta() {
  return numeroCuenta;
}
public int getSaldo() {
  return saldo;
}
public void depositar(double monto) {
  if (monto > 0) {
    saldo += monto;
      System.out.println("Depósito realizado con éxito. Nuevo saldo: $" + saldo);
} else {
      System.out.println("El monto a depositar debe ser mayor a 0.");
}
}
public void girar(double monto) {
  if (monto <= 0) {
      System.out.println("El monto a girar debe ser mayor a 0.");
} else if (monto > saldo) {
      System.out.println("Saldo insuficiente para realizar el giro.");
} else {
    saldo -= monto;
      System.out.println("Giro realizado con éxito. Nuevo saldo: $" + saldo);
}
}
public void consultarSaldo() {
      System.out.println("Saldo actual: $" + saldo);
}
}