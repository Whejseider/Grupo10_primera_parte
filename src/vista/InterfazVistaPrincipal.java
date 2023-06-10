package vista;

import java.awt.event.ActionListener;
import java.util.List;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.NuevoContratoDTO;

import javax.swing.*;

public interface InterfazVistaPrincipal {
    static final String NUEVO_ABONADO = "NUEVO_ABONADO";
    static final String BORRAR_ABONADO = "BORRAR_ABONADO";
    static final String SELECCION_ABONADO = "SELECCION_ABONADO"; 
    static final String NUEVO_CONTRATO = "NUEVO_CONTRATO";
    
    static final String PAGAR_FACTURA_CHEQUE = "PAGAR_FACTURA_CHEQUE";
    static final String PAGAR_FACTURA_TARJETA = "PAGAR_FACTURA_TARJETA";
    static final String PAGAR_FACTURA_EFECTIVO = "PAGAR_FACTURA_EFECTIVO";
    
    static final String MOSTRAR_FACTURA = "MOSTRAR_FACTURA";
    
    static final String PROMOCION_NINGUNA = "PROMOCION_NINGUNA";
    static final String PROMOCION_PLATINO = "PROMOCION_PLATINO";
    static final String PROMOCION_DORADA = "PROMOCION_DORADA";

    static final String MOSTRAR_TECNICOS = "MOSTRAR_TECNICOS";
    
    public void setActionListener(ActionListener actionListener);
    
    public void mostrarAlertaAbonadoYaExiste();
    public boolean confirmarBorrarAbonado();
    public String obtenerAbonadoSeleccionado();
    public NuevoAbonadoDTO pedirNuevoAbonado();
    public void actualizarTablaAbonados(List<IAbonado> abonados);
    public void actualizarDetallesAbonado(IAbonado abonado);
    
    public void mostrarAlertaDomicilioDuplicado();
    public NuevoContratoDTO pedirNuevoContrato();
    public void actualizarTablaContratos(List<IContrato> contratos);
    
    public void actualizarTablaFacturas(List<IFactura> facturas);
    public void mostrarAlertaPagarSinContratos();
    public void mostrarDialogoFactura(IFactura factura);
    
    public void actualizarBotonesPromocion(IPromocion promo);
    public JFrame getFrame();
}
