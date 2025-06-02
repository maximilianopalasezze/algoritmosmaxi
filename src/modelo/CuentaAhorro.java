package modelo;

import java.io.Serializable;
import interfaces.OperacionesBancarias;

public class CuentaAhorro extends CuentaBancaria implements Serializable,OperacionesBancarias{
 public CuentaAhorro(int numeroCuenta, double saldo) {
 super(numeroCuenta, saldo);
}
@Override
public boolean girar(double monto) {
 if (saldo >= monto) {
     saldo -= monto;
 return true;
}
 return false;
}
@Override
public void mostrarTipoCuenta() {
      System.out.println("Cuenta de Ahorro");
}
@Override
public void mostrarInformacion() {
    System.out.println("Cuenta Ahorro - N°: " + getNumeroCuenta() + ", Saldo: $" + getSaldo());
}

@Override
public void procesarOperacion() {
    System.out.println("Procesando operación de cuenta de ahorro...");
}
}