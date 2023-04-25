import modelo.abonado.AbonadoFactory;
import modelo.abonado.IAbonado;
import modelo.alarma.IServicioAlarma;
import modelo.alarma.ServicioAlarmaComercio;
import modelo.alarma.ServicioAlarmaVivienda;
import modelo.contrato.Contrato;
import modelo.contrato.IContrato;
import modelo.domicilio.Domicilio;
import modelo.factura.Factura;
import modelo.factura.Facturable;
import modelo.pago.Efectivo;

public class Prueba {

    public static void main(String[] args) {
        IServicioAlarma servicioAlarma = new ServicioAlarmaVivienda();

        IContrato contrato = new Contrato(servicioAlarma, new Domicilio("lala", "JAJA"));
        contrato.agregarCamaras(3);
        contrato.agregarBAPs(5);

        IContrato contrato1 = new Contrato(servicioAlarma, new Domicilio("AAA", "555"));
        contrato1.agregarCamaras(1);
        contrato1.contratarMovil();


        IServicioAlarma servicioAlarma1 = new ServicioAlarmaComercio();

        IContrato contrato2 = new Contrato(servicioAlarma1, new Domicilio("SSead", "418"));
        contrato2.agregarCamaras(9);
        contrato2.agregarBAPs(3);
        contrato2.contratarMovil();

        IAbonado abonado = AbonadoFactory.getAbonado("Juridica", "Carlos", "2424");
        abonado.agregarContrato(contrato);
        abonado.agregarContrato(contrato1);
        abonado.agregarContrato(contrato2);
        abonado.agregarDomicilio(contrato.getDomicilio());

        Facturable factura = new Factura(abonado, abonado.getContratos());
        factura = new Efectivo(factura);

        System.out.println(factura.getDetalles());

    }
}
