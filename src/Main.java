import java.util.ArrayList;

import modelo.AbonadoFactory;
import modelo.PromocionDorada;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.excepciones.AbonadoDuplicadoException;
import modelo.excepciones.AbonadoNoExisteException;
import modelo.excepciones.ContratoDuplicadoException;
import modelo.excepciones.SinContratosException;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IFactura;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testAbonadoDuplicado();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testContratoDuplicado();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testCrearContratoYFacturarConDecorator();
        main.testClonacionAbonadoFisico();
        main.testClonacionAbonadoJuridico();
        main.testClonacionFactura();
        main.muestraFacturasEmitidas();
        main.testHilos();
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
            IAbonado abonado = sistema.getAbonado("dniFisico1");

            sistema.agregarContrato("dniFisico1", "Comercio", "domicilio2", false, 3, 2);
            IFactura factura1 = sistema.generarFactura("dniFisico1", "EFECTIVO");

            sistema.agregarContrato("dniFisico1", "Vivienda", "domicilio3", false, 3, 2);
            IFactura factura2 = sistema.generarFactura("dniFisico1", "EFECTIVO");

//            abonado.pagarFactura(factura1);

            sistema.agregarContrato("dniFisico1", "Vivienda", "domicilio5", false, 3, 2);
            IFactura factura3 = sistema.generarFactura("dniFisico1", "EFECTIVO");

            System.out.println("********************************");
            abonado.pagarFactura(factura3);
            System.out.println(abonado.getFacturasEmitidas().get(2).getDetalle());
            System.out.println("********************************");

        } catch (ContratoDuplicadoException e) {
            throw new RuntimeException(e);
        } catch (AbonadoDuplicadoException e) {
            throw new RuntimeException(e);
        } catch (SinContratosException e) {
            throw new RuntimeException(e);
        } catch (AbonadoNoExisteException e) {
            throw new RuntimeException(e);
        }

    }

    public void testClonacionAbonadoFisico() {
        Sistema sistema = Sistema.getInstance();
        try {
            IAbonado abonado = sistema.getAbonado("dniFisico");
            IAbonado abonadoClon = abonado.clone();
            assert (abonadoClon.equals(abonado));
        } catch (AbonadoNoExisteException e) {
            throw new RuntimeException(e);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
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
            IFactura factura = sistema.generarFactura("dniFisico1", "efectivo");
            IFactura facturaClon = factura.clone();
            assert (facturaClon.equals(factura));
        } catch (SinContratosException e) {
            throw new RuntimeException(e);
        } catch (AbonadoNoExisteException e) {
            throw new RuntimeException(e);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

    public void muestraFacturasEmitidas() {
        Sistema sistema = Sistema.getInstance();
        ArrayList<IFactura> facturas = sistema.getFacturasEmitidas();
        for (IFactura factura : facturas) {
            System.out.println(factura.getDetalle());
        }

    }
    
    public void testHilos() {
    	Sistema sistema = Sistema.getInstance();
    	Tecnico t1=sistema.agregarTecnico("tec1");
    	Tecnico t2=sistema.agregarTecnico("tec2");
    	Tecnico t3=sistema.agregarTecnico("tec3");
    	  
    	t1.start();
    	t2.start();
    	t3.start(); 
    	
    	
    	IAbonado a1=AbonadoFactory.getAbonado("Juridico", "nom1", "dni1");
    	sistema.solicitarVisita(a1);
    	IAbonado a2=AbonadoFactory.getAbonado("Juridico", "nom2", "dni2");
    	sistema.solicitarVisita(a2);
    	IAbonado a3=AbonadoFactory.getAbonado("Juridico", "nom3", "dni3");
    	sistema.solicitarVisita(a3);
    	IAbonado a4=AbonadoFactory.getAbonado("Juridico", "nom4", "dni4");
    	sistema.solicitarVisita(a4);
    	IAbonado a5=AbonadoFactory.getAbonado("Juridico", "nom5", "dni5");
    	sistema.solicitarVisita(a5);
    	IAbonado a6=AbonadoFactory.getAbonado("Juridico", "nom6", "dni6");
    	sistema.solicitarVisita(a6);
    	
    }

}
