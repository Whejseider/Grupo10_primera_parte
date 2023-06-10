package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.PromocionDorada;
import modelo.PromocionPlatino;
import modelo.SinPromocion;
import modelo.Sistema;
import modelo.excepciones.AbonadoDuplicadoException;
import modelo.excepciones.AbonadoNoExisteException;
import modelo.excepciones.ContratoDuplicadoException;
import modelo.excepciones.SinContratosException;
import modelo.interfaces.IFactura;
import vista.InterfazVistaPrincipal;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.NuevoContratoDTO;
import vista.tecnicos.InterfazVentanaTecnicos;
import vista.tecnicos.NuevoTecnicoDTO;
import vista.tecnicos.VentanaTecnicos;

public class Controlador implements ActionListener {
    private final Sistema modelo;
    private final InterfazVistaPrincipal vistaPrincipal;
    private InterfazVentanaTecnicos dialogoTecnicos;

    public Controlador(InterfazVistaPrincipal vistaPrincipal, Sistema modelo) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.setActionListener(this);
        this.vistaPrincipal.actualizarTablaAbonados(modelo.getAbonados());
        this.vistaPrincipal.actualizarBotonesPromocion(modelo.getPromocion());
        this.dialogoTecnicos = new VentanaTecnicos(this.vistaPrincipal.getFrame());
        this.dialogoTecnicos.setActionListener(this);
        this.modelo = modelo;
    }
    
    private void manejarNuevoAbonado() {
        NuevoAbonadoDTO dto = this.vistaPrincipal.pedirNuevoAbonado();
        if (dto != null) {
            try {
                this.modelo.agregarAbonado(dto.getTipo(), dto.getNombre(), dto.getDni());
                this.vistaPrincipal.actualizarTablaAbonados(this.modelo.getAbonados());
            } catch (AbonadoDuplicadoException e) {
                this.vistaPrincipal.mostrarAlertaAbonadoYaExiste();
            }
        }
    }
    
    private void manejarNuevoContrato() {
        NuevoContratoDTO dto = this.vistaPrincipal.pedirNuevoContrato();
        
        if (dto == null) {
            return;
        }
        
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
                
        try {
            this.modelo.agregarContrato(dni, dto.getTipo(), dto.getDomicilio(), dto.getTieneMovil(), dto.getCantCamaras(), dto.getCantBotones());
            this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));
        } catch (AbonadoNoExisteException e) {
            
        } catch (ContratoDuplicadoException e) {
            this.vistaPrincipal.mostrarAlertaDomicilioDuplicado();
        }
    }
    
    private void manejarBorrarAbonado() {
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
        //Si no hay un abonado seleccionado no se hace nada
        if (dni == null) {
            return;
        }
        
        if (this.vistaPrincipal.confirmarBorrarAbonado()) {
            this.modelo.eliminarAbonado(dni);
            this.vistaPrincipal.actualizarTablaAbonados(this.modelo.getAbonados());
            this.vistaPrincipal.actualizarDetallesAbonado(null);
        }
    }
    
    private void manejarSeleccionAbonado() {
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();

        try {                
            this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));
        } catch (AbonadoNoExisteException e) {
            
        }
    }
    
    private void manejarPagarFactura(String medioDePago) {
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();

        try {                
            this.modelo.generarFactura(dni, medioDePago);
            this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));
        } catch (SinContratosException e) {
            this.vistaPrincipal.mostrarAlertaPagarSinContratos();
        } catch (AbonadoNoExisteException e) {
            
        }
    }
    
    private void manejarPagarFacturaCheque() {
        this.manejarPagarFactura("cheque");
    }
    
    private void manejarPagarFacturaTarjeta() {
        this.manejarPagarFactura("tarjeta");
    }
    
    private void manejarPagarFacturaEfectivo() {
        this.manejarPagarFactura("efectivo");
    }
    
    private void manejarMostrarFactura(int idFactura) {
        for (IFactura factura : this.modelo.getFacturasEmitidas()) {
            if (factura.getId() == idFactura) {
                this.vistaPrincipal.mostrarDialogoFactura(factura);
            }
        }
    }
    
    private void manejarQuitarPromocion() {
        //TODO: Deberia hacerse un factory o algo asi no creo directamente el objeto
        this.modelo.setPromocion(new SinPromocion());
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }
    
    private void manejarPromocionDorada() {
        this.modelo.setPromocion(new PromocionDorada());
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }
    
    private void manejarPromocionPlatino() {
        this.modelo.setPromocion(new PromocionPlatino());
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }

    private void manejarMostrarTecnicos() {
        this.dialogoTecnicos.setVisible(true);
    }

    private void manejarNuevoTecnico() {
        NuevoTecnicoDTO nuevoTecnico = this.dialogoTecnicos.pedirNuevoTecnico();

        if (nuevoTecnico != null) {

        }
    }

    private void manejarBorrarTecnico() {
        //TODO: completar
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        String comando = evento.getActionCommand();
        System.out.println("ACTION: " + comando);

        switch (comando) {
            case InterfazVistaPrincipal.NUEVO_ABONADO -> manejarNuevoAbonado();
            case InterfazVistaPrincipal.BORRAR_ABONADO -> manejarBorrarAbonado();
            case InterfazVistaPrincipal.SELECCION_ABONADO -> manejarSeleccionAbonado();
            case InterfazVistaPrincipal.NUEVO_CONTRATO -> manejarNuevoContrato();
            case InterfazVistaPrincipal.PAGAR_FACTURA_EFECTIVO -> manejarPagarFacturaEfectivo();
            case InterfazVistaPrincipal.PAGAR_FACTURA_TARJETA -> manejarPagarFacturaTarjeta();
            case InterfazVistaPrincipal.PAGAR_FACTURA_CHEQUE -> manejarPagarFacturaCheque();
            case InterfazVistaPrincipal.MOSTRAR_FACTURA -> manejarMostrarFactura((int) evento.getSource());
            case InterfazVistaPrincipal.PROMOCION_NINGUNA -> manejarQuitarPromocion();
            case InterfazVistaPrincipal.PROMOCION_DORADA -> manejarPromocionDorada();
            case InterfazVistaPrincipal.PROMOCION_PLATINO -> manejarPromocionPlatino();
            case InterfazVistaPrincipal.MOSTRAR_TECNICOS -> manejarMostrarTecnicos();

            case InterfazVentanaTecnicos.NUEVO_TECNICO -> manejarNuevoTecnico();
            case InterfazVentanaTecnicos.BORRAR_TECNICO -> manejarBorrarTecnico();
        }
    }
}
