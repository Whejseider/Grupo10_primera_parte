import java.util.ArrayList;

import controlador.ControladorAbonados;
import modelo.PromocionDorada;
import modelo.Sistema;
import modelo.excepciones.AbonadoDuplicadoException;
import modelo.excepciones.ContratoDuplicadoException;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IFactura;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        ControladorAbonados controlador  = new ControladorAbonados(new VentanaPrincipal(), Sistema.getInstance());
    }
    
    public void correrPruebas() {
        testAbonadoDuplicado();
        testContratoDuplicado();
        testCrearContratoYFacturarConDecorator();
        testClonacionAbonadoFisico();
        testClonacionAbonadoJuridico();
        testClonacionFactura();
        muestraFacturasEmitidas();
    }

    public void testAbonadoDuplicado() {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarAbonado("Fisico", "abonadoFiscio", "dniFisico");
            sistema.agregarAbonado("Fisico", "abonadoFiscio", "dniFisico");
        } catch (Exception e) {
            assert (e instanceof AbonadoDuplicadoException);
        }

    }

    public void testContratoDuplicado() {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarContrato("dniFisico", "Comercio", "domicilio1", false, 3, 2);
            sistema.agregarContrato("dniFisico", "Comercio", "domicilio1", false, 3, 2);
        } catch (Exception e) {
            assert (e instanceof ContratoDuplicadoException);
        }

    }

    public void testCrearContratoYFacturarConDecorator() {

        Sistema sistema = Sistema.getInstance();
        sistema.setPromocion(new PromocionDorada());
        try {
            sistema.agregarAbonado("Fisico", "abonadoFiscio1", "dniFisico1");
            sistema.agregarContrato("dniFisico1", "Comercio", "domicilio2", false, 3, 2);
            IFactura factura = sistema.generarFactura("dniFisico1", "cheque");
            System.out.println(factura.getDetalle());
        } catch (Exception e) {
        }

    }

    public void testClonacionAbonadoFisico() {
        Sistema sistema = Sistema.getInstance();
        try {
            IAbonado abonado = sistema.getAbonado("dniFisico");
            IAbonado abonadoClon = abonado.clone();
            assert (abonadoClon.equals(abonado));
        } catch (Exception e) {

        }

    }

    public void testClonacionAbonadoJuridico() {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarAbonado("Juridico", "abonadoFiscio", "dniJuridico");
            sistema.getAbonado("dniJuridico").clone();
        } catch (Exception e) {
            assert (e instanceof CloneNotSupportedException);
        }

    }

    public void testClonacionFactura() {
        Sistema sistema = Sistema.getInstance();
        try {
            IFactura factura = sistema.generarFactura("dniJuridico", "efectivo");
            IFactura facturaClon = factura.clone();
            assert (facturaClon.equals(factura));
        } catch (Exception e) {

        }

    }

    public void muestraFacturasEmitidas() {
        Sistema sistema = Sistema.getInstance();
        ArrayList<IFactura> facturas = sistema.getFacturasEmitidas();
        for (IFactura factura : facturas) {
            System.out.println(factura.getDetalle());
        }

    }

}
