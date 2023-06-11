import controlador.Controlador;
import modelo.*;
import modelo.excepciones.AbonadoDuplicadoException;
import modelo.excepciones.AbonadoNoExisteException;
import modelo.excepciones.ContratoDuplicadoException;
import modelo.excepciones.SinContratosException;
import modelo.input.AbonadoInput;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.output.AbonadoOutput;
import modelo.output.PromocionOutput;
import vista.VentanaPrincipal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {Main main = new Main();
            main.testPersistencia();
             Sistema modelo = Sistema.getInstance();
             VentanaPrincipal vista = new VentanaPrincipal();
             Controlador controlador = new Controlador(vista, modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void correrPruebas() throws IOException {
        testAbonadoDuplicado();
        testContratoDuplicado();
        testCrearContratoYFacturarConDecorator();
        testClonacionAbonadoFisico();
        testClonacionAbonadoJuridico();
        testClonacionFactura();
        muestraFacturasEmitidas();
    }

    public void testPersistencia() throws IOException {
        PromocionOutput promocionOutput = new PromocionOutput();
        promocionOutput.abrir();
        promocionOutput.escribir(new PromocionDorada());
        promocionOutput.cerrar();
        AbonadoOutput abonadoOutput = new AbonadoOutput();
        ArrayList<IAbonado> abonados = new ArrayList<>();
        IAbonado abonado1 = new AbonadoFisico("nombre1", "dni1");
        IAbonado abonado2 = new AbonadoJuridico("nombreEmpresa2", "idEmpresa2");
        IAbonado abonado3 = new AbonadoFisico("nombre3", "dni3");
        IAbonado abonado4 = new AbonadoJuridico("nombreEmpresa4", "idEmpresa4");

        IContrato contrato1 = ContratoFactory.getContrato("Comercio", "domicilio1", false, 1, 2);
        abonado1.contratarNuevoServicio(contrato1);

        try {
            abonado1.generarFactura(new PromocionDorada(), "EFECTIVO", LocalDate.now());
            abonado1.pagarFactura(abonado1.getFacturasEmitidas().get(0));
        } catch (SinContratosException e) {
            e.printStackTrace();
        }

        abonados.add(abonado1);
        abonados.add(abonado2);
        abonados.add(abonado3);
        abonados.add(abonado4);
        abonadoOutput.abrir();
        abonadoOutput.escribir(abonados);
        abonadoOutput.cerrar();

        AbonadoInput abonadoInput = new AbonadoInput();

        ArrayList<IAbonado> abonadosLectura = new ArrayList<>();
        abonadoInput.abrir();
        abonadosLectura = abonadoInput.leer();
        for (IAbonado abonado : abonadosLectura)
            System.out.println(abonado.toString());
        abonadoInput.cerrar();

    }

    public void testAbonadoDuplicado() throws IOException {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarAbonado("Fisico", "abonadoFiscio", "dniFisico");
            sistema.agregarAbonado("Fisico", "abonadoFiscio", "dniFisico");
        } catch (Exception e) {
            assert (e instanceof AbonadoDuplicadoException);
        }

    }

    public void testContratoDuplicado() throws IOException {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarContrato("dniFisico", "Comercio", "domicilio1", false, 3, 2);
            sistema.agregarContrato("dniFisico", "Comercio", "domicilio1", false, 3, 2);
        } catch (Exception e) {
            assert (e instanceof ContratoDuplicadoException);
        }

    }

    public void testCrearContratoYFacturarConDecorator() throws IOException {

        Sistema sistema = Sistema.getInstance();
        sistema.setPromocion(new PromocionDorada());
        try {
            sistema.agregarAbonado("Fisico", "abonadoFiscio1", "dniFisico1");
            IAbonado abonado = sistema.getAbonado("dniFisico1");

            sistema.agregarContrato("dniFisico1", "Comercio", "domicilio2", false, 3, 2);
            IFactura factura1 = sistema.generarFactura("dniFisico1", "EFECTIVO");

            sistema.agregarContrato("dniFisico1", "Vivienda", "domicilio3", false, 3, 2);
            IFactura factura2 = sistema.generarFactura("dniFisico1", "EFECTIVO");

            // abonado.pagarFactura(factura1);

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

    public void testClonacionAbonadoFisico() throws IOException {
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

    public void testClonacionAbonadoJuridico() throws IOException {
        Sistema sistema = Sistema.getInstance();
        try {
            sistema.agregarAbonado("Juridico", "abonadoFiscio", "dniJuridico");
            sistema.getAbonado("dniJuridico").clone();
        } catch (Exception e) {
            assert (e instanceof CloneNotSupportedException);
        }

    }

    public void testClonacionFactura() throws IOException {
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

    public void muestraFacturasEmitidas() throws IOException {
        Sistema sistema = Sistema.getInstance();
        ArrayList<IFactura> facturas = sistema.getFacturasEmitidas();
        for (IFactura factura : facturas) {
            System.out.println(factura.getDetalle());
        }

    }
}
