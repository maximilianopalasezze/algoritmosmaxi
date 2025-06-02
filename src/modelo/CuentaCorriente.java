package modelo;

import interfaces.OperacionesBancarias;
import java.io.Serializable;

public class CuentaCorriente extends CuentaBancaria implements Serializable, OperacionesBancarias {
 public CuentaCorriente(int numeroCuenta, double saldo){
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
      System.out.println("Cuenta Corriente");
}
@Override
public void mostrarInformacion() {
    System.out.println("Cuenta Corriente - N°: " + getNumeroCuenta() + ", Saldo: $" + getSaldo());
}

@Override
public void procesarOperacion() {
    System.out.println("Procesando operación de cuenta corriente...");
}
}