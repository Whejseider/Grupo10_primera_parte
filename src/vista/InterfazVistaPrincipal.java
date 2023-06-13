package vista;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Observer;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;

import vista.abonados.tabla.NuevoAbonadoDTO;
import vista.abonados.detalle.contratos.NuevoContratoDTO;

import javax.swing.*;

public interface InterfazVistaPrincipal extends Observer {
    static final String NUEVO_ABONADO = "NUEVO_ABONADO";
    static final String BORRAR_ABONADO = "BORRAR_ABONADO";
    static final String SELECCION_ABONADO = "SELECCION_ABONADO";

    static final String NUEVO_CONTRATO = "NUEVO_CONTRATO";
    static final String SELECCION_CONTRATO = "SELECCION_CONTRATO";

    static final String PAGAR_FACTURA_CHEQUE = "PAGAR_FACTURA_CHEQUE";
    static final String PAGAR_FACTURA_TARJETA = "PAGAR_FACTURA_TARJETA";
    static final String PAGAR_FACTURA_EFECTIVO = "PAGAR_FACTURA_EFECTIVO";

    static final String MOSTRAR_FACTURA = "MOSTRAR_FACTURA";

    static final String PROMOCION_NINGUNA = "PROMOCION_NINGUNA";
    static final String PROMOCION_PLATINO = "PROMOCION_PLATINO";
    static final String PROMOCION_DORADA = "PROMOCION_DORADA";

    static final String MOSTRAR_TECNICOS = "MOSTRAR_TECNICOS";
    static final String BORRAR_CONTRATO = "BORRAR_CONTRATO";
    static final String ENVIAR_TECNICO = "ENVIAR_TECNICO";

    static final String AVANZAR_MES = "AVANZAR_MES";


    static final String CIERRE_VENTANA_PRINCIPAL = "CIERRE_VENTANA_PRINCIPAL";

    public void setActionListener(ActionListener actionListener);

    public void mostrarAlertaAbonadoYaExiste();

    public boolean confirmarBorrarAbonado();

    public String obtenerAbonadoSeleccionado();

    public NuevoAbonadoDTO pedirNuevoAbonado();

    public void actualizarTablaAbonados(List<IAbonado> abonados);

    public void actualizarDetallesAbonado(IAbonado abonado);

    public void mostrarAlertaDomicilioDuplicado();

    public void mostrarDialogoAlertaEstado(String msg);

    public NuevoContratoDTO pedirNuevoContrato();

    public boolean confirmarBorrarContrato();

    public String obtenerContratoSeleccionado();

    public Integer obtenerFacturaSeleccionado();

    boolean confirmarPagarFactura();

    public void actualizarTablaContratos(List<IContrato> contratos);

    public void actualizarTablaFacturas(List<IFactura> facturas);

    public void mostrarAlertaPagarSinFacturas();

    public void mostrarDialogoFactura(IFactura factura);

    public void actualizarBotonesPromocion(IPromocion promo);

    public JFrame getFrame();

    boolean confirmarAvanzarMes();

    public void actualizarComboboxTecnicos(List<ITecnico> tecnicos);

    public String obtenerTecnicoSeleccionado();

    public void actualizarProgresoServicio(ServicioTecnico service);

    void actualizarFecha(LocalDate fecha);

    void mostrarAlertaFacturaPagada();
}
