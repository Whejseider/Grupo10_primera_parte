import negocio.PromocionDorada;
import negocio.Sistema;
import negocio.excepciones.AbonadoDuplicadoException;
import negocio.excepciones.ContratoDuplicadoException;
import negocio.interfaces.IAbonado;
import negocio.interfaces.IFactura;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testAbonadoDuplicado();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testContratoDuplicado();
        System.out.println("\n-------------------------------------------------------------\n");
        main.testCrearContratoYFacturarConDecorator();
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

    }

}
