package controlador;

import modelo.PromocionDorada;
import modelo.PromocionPlatino;
import modelo.SinPromocion;
import modelo.Sistema;
import modelo.excepciones.*;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IFactura;
import modelo.tecnicos.ServicioTecnico;
import modelo.tecnicos.Tecnico;
import vista.InterfazVistaPrincipal;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.NuevoContratoDTO;
import vista.tecnicos.InterfazVentanaTecnicos;
import vista.tecnicos.NuevoTecnicoDTO;
import vista.tecnicos.VentanaTecnicos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Controlador implements ActionListener {
    private final Sistema modelo;
    private final InterfazVistaPrincipal vistaPrincipal;
    private final InterfazVentanaTecnicos dialogoTecnicos;

    public Controlador(InterfazVistaPrincipal vistaPrincipal, Sistema modelo) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.setActionListener(this);
        this.vistaPrincipal.actualizarTablaAbonados(modelo.getAbonados());
        this.vistaPrincipal.actualizarBotonesPromocion(modelo.getPromocion());
        this.dialogoTecnicos = new VentanaTecnicos(this.vistaPrincipal.getFrame());
        this.dialogoTecnicos.setActionListener(this);
        this.dialogoTecnicos.actualizar(modelo.getTecnicos());
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
            this.modelo.agregarContrato(dni, dto.getTipo(), dto.getDomicilio(), dto.getTieneMovil(),
                    dto.getCantCamaras(), dto.getCantBotones());
            this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));
        } catch (AbonadoNoExisteException e) {

        } catch (ContratoDuplicadoException e) {
            this.vistaPrincipal.mostrarAlertaDomicilioDuplicado();
        }
    }

    private void manejarBorrarAbonado() {
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
        // Si no hay un abonado seleccionado no se hace nada
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
        this.vistaPrincipal.actualizarComboboxTecnicos(this.modelo.getTecnicos());

        try {
            this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));
        } catch (AbonadoNoExisteException e) {

            this.vistaPrincipal.actualizarDetallesAbonado(null);
        }
    }

    private void manejarSeleccionContrato() {
        //
    }

    private void manejarBorrarContrato() {
        String domicilio = this.vistaPrincipal.obtenerContratoSeleccionado();

        if (domicilio != null) {
            this.modelo.eliminarContrato(domicilio);
            String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
            try {
                this.vistaPrincipal.actualizarTablaContratos(this.modelo.getAbonado(dni).getContratos());
            } catch (AbonadoNoExisteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void manejarPagarFactura(String medioDePago) { // No habria que seleccionar la factura y pagarla?
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();

        try {
            IAbonado abonado = this.modelo.getAbonado(dni);
            //abonado.pagarFactura(this.modelo.generarFactura(dni, medioDePago, this.vistaPrincipal.getFecha())); asi cambia el estado
            //se podria crear otro boton o algo
            this.modelo.generarFactura(dni, medioDePago);
            this.vistaPrincipal.actualizarDetallesAbonado(abonado);
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
            try {
                this.modelo.agregarTecnico(nuevoTecnico.getNombre());
                List<Tecnico> tecnicos = this.modelo.getTecnicos();
                this.vistaPrincipal.actualizarComboboxTecnicos(tecnicos);
                this.dialogoTecnicos.actualizar(tecnicos);
            } catch(TecnicoYaExisteException e) {
                this.dialogoTecnicos.mostrarAlertaTecnicoYaExiste();
            }
        }
    }

    private void manejarAvanzarMes() {
        boolean deberiaAvanzar = this.vistaPrincipal.confirmarAvanzarMes();

        if (deberiaAvanzar) {

            LocalDate fecha = this.modelo.getFecha();
            LocalDate nuevaFecha = fecha.plusMonths(1);
            this.modelo.setFecha(nuevaFecha);
            this.vistaPrincipal.actualizarFecha(this.modelo.getFecha());
        }

    }

    private void manejarBorrarTecnico() {
        String nombreTecnico = this.dialogoTecnicos.obtenerTecnicoSeleccionado();

        if (nombreTecnico != null) {
            try {
                this.modelo.eliminarTecnico(nombreTecnico);
                this.dialogoTecnicos.actualizar(this.modelo.getTecnicos());
                this.vistaPrincipal.actualizarComboboxTecnicos(this.modelo.getTecnicos());
            } catch (TecnicoTrabajandoException e) {
                this.dialogoTecnicos.mostrarAlertaTecnicoNoSePuedeBorrar();
            } catch (TecnicoNoExisteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void manejarEnviarTecnico() {
        String nombreTecnico = this.vistaPrincipal.obtenerTecnicoSeleccionado();
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();

        try {
            ServicioTecnico service = this.modelo.pedirService(dni, nombreTecnico);
            service.addObserver(this.vistaPrincipal);
            this.vistaPrincipal.actualizarProgresoServicio(service);
        } catch (ServicioEnCursoException e) {
            throw new RuntimeException(e);
        } catch (AbonadoNoExisteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent evento){
        String comando = evento.getActionCommand();
        System.out.println("ACTION: " + comando);

        switch (comando) {
            case InterfazVistaPrincipal.NUEVO_ABONADO -> manejarNuevoAbonado();
            case InterfazVistaPrincipal.BORRAR_ABONADO -> manejarBorrarAbonado();
            case InterfazVistaPrincipal.SELECCION_ABONADO -> manejarSeleccionAbonado();

            case InterfazVistaPrincipal.PAGAR_FACTURA_EFECTIVO -> manejarPagarFacturaEfectivo();
            case InterfazVistaPrincipal.PAGAR_FACTURA_TARJETA -> manejarPagarFacturaTarjeta();
            case InterfazVistaPrincipal.PAGAR_FACTURA_CHEQUE -> manejarPagarFacturaCheque();
            case InterfazVistaPrincipal.MOSTRAR_FACTURA -> manejarMostrarFactura((int) evento.getSource());

            case InterfazVistaPrincipal.PROMOCION_NINGUNA -> manejarQuitarPromocion();
            case InterfazVistaPrincipal.PROMOCION_DORADA -> manejarPromocionDorada();
            case InterfazVistaPrincipal.PROMOCION_PLATINO -> manejarPromocionPlatino();

            case InterfazVistaPrincipal.SELECCION_CONTRATO -> manejarSeleccionContrato();
            case InterfazVistaPrincipal.NUEVO_CONTRATO -> manejarNuevoContrato();
            case InterfazVistaPrincipal.BORRAR_CONTRATO -> manejarBorrarContrato();

            case InterfazVistaPrincipal.AVANZAR_MES -> manejarAvanzarMes();

            case InterfazVistaPrincipal.MOSTRAR_TECNICOS -> manejarMostrarTecnicos();
            case InterfazVentanaTecnicos.NUEVO_TECNICO -> manejarNuevoTecnico();
            case InterfazVentanaTecnicos.BORRAR_TECNICO -> manejarBorrarTecnico();
            case InterfazVistaPrincipal.ENVIAR_TECNICO -> manejarEnviarTecnico();
        }
    }
}
