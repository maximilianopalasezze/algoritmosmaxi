package modelo;

import java.io.Serializable;

public abstract class CuentaBancaria implements Serializable {
    protected int numeroCuenta;
    protected double saldo;

    public CuentaBancaria(int numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    public CuentaBancaria(int numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
    this.saldo = 0;
}

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        this.saldo += monto;
    }

    public abstract boolean girar(double monto);

    public abstract void mostrarTipoCuenta();
}