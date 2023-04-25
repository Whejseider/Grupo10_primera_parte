package modelo.factura;

import modelo.abonado.IAbonado;
import modelo.contrato.IContrato;

import java.util.ArrayList;
import java.util.List;

public class Factura implements Facturable {
    private IAbonado abonado;
    private List<IContrato> contrataciones;
    private double totalAPagar;
    private double totalAPagarMdP;
    private String medioDePago;
    private static int _ID = 0;
    private final int ID;

    /**
     * Constructor de Factura que genera una factura dado un abonado<br>
     * y una lista de contratos
     * @param abonado abonado
     * @param contrataciones lista de contratos del abonado
     */
    public Factura(IAbonado abonado, List<IContrato> contrataciones) {
        this.abonado = abonado;
        this.contrataciones = contrataciones;
        this.totalAPagar = getPagoDeServicios();
        this.totalAPagarMdP = this.totalAPagar;
        this.ID = ++_ID;
    }

    /**
     * Devuelve el valor a pagar en bruto de todos los servicios del contrato
     * @return el valor a pagar en bruto de todos los servicios del contrato
     */
    private double getPagoDeServicios() {
        double pago = 0;

        for (int i = 0; i < contrataciones.size(); i++)
            pago += abonado.getPagoDeServicio(contrataciones.get(i), i);

        return pago;
    }

    @Override
    public double getTotalAPagar() {
        return this.totalAPagar;
    }

    @Override
    public double getTotalAPagarMdP() {
        return this.totalAPagarMdP;
    }

    /**
     * Detalles de la factura
     * @return devuelve los detalles de la factura con sus precios
     */
    @Override
    public String getDetalles() {
        double pago;

        StringBuilder detalles = new StringBuilder();
        String separator = "-----------\n";
        String separatorService = "_________\n";

        detalles.append("Factura N°: ").append(this.ID).append("\n").append("\n");
        detalles.append("Datos del Abonado:").append("\n");
        detalles.append("Nombre: ").append(this.abonado.getNombre()).append("\n");
        detalles.append("DNI: ").append(this.abonado.getDni()).append("\n");
        detalles.append("Tipo de Abonado: Persona ").append(this.abonado.getTipo()).append("\n").append("\n");

        detalles.append("Detalles de Servicios Contratados:").append("\n").append("\n");

        for (int i = 0; i < contrataciones.size(); i++) {
            IContrato contrato = contrataciones.get(i);

            pago = abonado.getPagoDeServicio(contrato, i);

            detalles.append(contrato.getServicioAlarma().getTipoAlarma()).append("\n");
            detalles.append("- Direccion: ").append(contrato.getDomicilio().getCalle()).append(" ").append(contrato.getDomicilio().getNumero()).append("\n");
            detalles.append("- Valor del servicio: ").append(contrato.getServicioAlarma().getPrecio()).append("\n");
            detalles.append("- Promocion Aplicada: ").append("\n");
            detalles.append("- Descuento Aplicado: ").append("\n");

            if (contrato.cantCamaras() > 0) {
                detalles.append("- Camaras Agregadas: ").append(contrato.cantCamaras()).append("\n");
                detalles.append("\t Precio Camara Unitario: ").append(IContrato.precioCamara).append("\n");
                detalles.append("\t Precio Total Camaras: ").append(contrato.cantCamaras() * IContrato.precioCamara).append("\n");
            }

            if (contrato.cantBAPs() > 0) {
                detalles.append("- Botones AntiPanico Agregados: ").append(contrato.cantBAPs()).append("\n");
                detalles.append("\t Precio Boton AntiPanico Unitario: ").append(IContrato.precioBAP).append("\n");
                detalles.append("\t Precio Total Botones AntiPanico: ").append(contrato.cantBAPs() * IContrato.precioBAP).append("\n");
            }

            if (contrato.isTieneMovil()) {
                detalles.append("- Movil de Seguimiento: Contratado").append("\n");
                detalles.append("\t Precio Movil de Seguimiento: ").append(IContrato.precioMovil).append("\n");
            }

            if (i > 1 && abonado.getTipo().equalsIgnoreCase("Juridica"))
                detalles.append("- Descuento de Bonificación Jurídica: ").append((pago / IAbonado.porcBonificacionJuridica) - pago).append("\n");
            detalles.append("- Precio total del Servicio: ").append(pago).append("\n");

            detalles.append(separatorService);
        }

        detalles.append(separator);

        detalles.append("Total a pagar: ").append(getTotalAPagar()).append("\n");


        return detalles.toString();
    }

    @Override
    public IAbonado getAbonado() {
        return abonado;
    }

    @Override
    public void setAbonado(IAbonado abonado) {
        assert abonado != null : "El abonado no existe o es null!";
        this.abonado = abonado;
    }

    @Override
    public List<IContrato> getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(List<IContrato> contrataciones) {
        assert contrataciones.isEmpty() : "Debe existir almenos una contrato!";
        this.contrataciones = contrataciones;
    }
@Override
    public void setTotalAPagar(double totalAPagar) {
        assert totalAPagar >= 0: "El monto debe ser positivo!";
        this.totalAPagar = totalAPagar;
    }
@Override
    public void setTotalAPagarMdP(double totalAPagarMdP) {
    assert totalAPagarMdP >= 0: "El monto debe ser positivo!";

        this.totalAPagarMdP = totalAPagarMdP;
    }

    @Override
    public String getMedioDePago() {
        return this.medioDePago;
    }

    @Override
    public void setMedioDePago(String medioDePago) {
        assert medioDePago.isEmpty(): "El medio de pago no puede ser vacio!";

        this.medioDePago = medioDePago;
    }

    public static int getId() {
        return _ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public Object clone() {
        Factura nuevaFactura;
        try {
            nuevaFactura = (Factura) super.clone();
            nuevaFactura.contrataciones = new ArrayList<>(this.contrataciones.size());
            for (IContrato contrato : this.contrataciones) {
                nuevaFactura.contrataciones.add((IContrato) contrato.clone());
            }
            nuevaFactura.abonado = (IAbonado) this.abonado.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }

        return nuevaFactura;
    }
}
