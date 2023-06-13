package controlador;

import modelo.*;
import modelo.excepciones.*;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;
import persistencia.PersistenciaSistema;
import vista.InterfazVistaPrincipal;
import vista.abonados.tabla.NuevoAbonadoDTO;
import vista.abonados.detalle.contratos.NuevoContratoDTO;
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
        this.vistaPrincipal.actualizarFecha(modelo.getFecha());
        this.vistaPrincipal.actualizarComboboxTecnicos(modelo.getTecnicos());
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
            IAbonado abonado = this.modelo.getAbonado(dni);
            if (abonado.isFisico()) {
                AbonadoFisico abonadoFisico = (AbonadoFisico) abonado;
                if (abonadoFisico.getEstado().toString().equalsIgnoreCase("Moroso"))
                    this.vistaPrincipal.mostrarDialogoAlertaEstado("No puede crear un nuevo contrato por ser Moroso." + "\n" + "Actualice su estado.");
            }
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

            String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
            try {
                IAbonado abonado = this.modelo.getAbonado(dni);
                if (abonado.isFisico()) {
                    AbonadoFisico abonadoFisico = (AbonadoFisico) abonado;
                    if (abonadoFisico.getEstado().toString().equals("Moroso") || abonadoFisico.getEstado().toString().equalsIgnoreCase("Sin contratación"))
                        this.vistaPrincipal.mostrarDialogoAlertaEstado("No puede borrar el contrato por ser Moroso!" + "\n" + "Actualice su situación.");
                }
                IContrato contrato = abonado.buscaContrato(domicilio);
                assert contrato != null;
                abonado.bajaDeServicio(contrato); // State
                this.modelo.actualizadorEstado();
                this.vistaPrincipal.actualizarTablaContratos(this.modelo.getAbonado(dni).getContratos());
                this.vistaPrincipal.actualizarDetallesAbonado(this.modelo.getAbonado(dni));

            } catch (AbonadoNoExisteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void manejarPagarFactura(String medioDePago, int idFactura) {
        String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();

        try {
            IAbonado abonado = this.modelo.getAbonado(dni);
            IFactura factura = buscaFactura(idFactura);
            assert factura != null;
            if (factura.isPagada())
                this.vistaPrincipal.mostrarAlertaFacturaPagada();
            else {
                IFactura facturaVieja = factura;
                factura = FacturaFactory.getFactura(factura, medioDePago);
                abonado.actualizarFactura(factura, facturaVieja);
                abonado.pagarFactura(factura);
                this.vistaPrincipal.actualizarTablaFacturas(abonado.getFacturasEmitidas());
                this.vistaPrincipal.actualizarDetallesAbonado(abonado);
            }
        } catch (AbonadoNoExisteException e) {

        }
    }

    private void manejarPagarFacturaCheque() {
        int facturaSeleccionado = this.vistaPrincipal.obtenerFacturaSeleccionado();
        if (facturaSeleccionado != -1)
            this.manejarPagarFactura("cheque", facturaSeleccionado);
        else
            this.vistaPrincipal.mostrarAlertaFacturaPagada();
    }

    private void manejarPagarFacturaTarjeta() {
        int facturaSeleccionado = this.vistaPrincipal.obtenerFacturaSeleccionado();
        if (facturaSeleccionado != -1)
            this.manejarPagarFactura("tarjeta", facturaSeleccionado);
        else
            this.vistaPrincipal.mostrarAlertaFacturaPagada();
    }

    private void manejarPagarFacturaEfectivo() {
        int facturaSeleccionado = this.vistaPrincipal.obtenerFacturaSeleccionado();
        if (facturaSeleccionado != -1)
            this.manejarPagarFactura("efectivo", facturaSeleccionado);
        else
            this.vistaPrincipal.mostrarAlertaFacturaPagada();
    }

    private void manejarMostrarFactura(int idFactura) {
        IFactura factura = buscaFactura(idFactura);
        if (factura != null)
            this.vistaPrincipal.mostrarDialogoFactura(factura);
    }

    private IFactura buscaFactura(int idFactura) {
        for (IFactura factura : this.modelo.getFacturasEmitidas()) {
            if (factura.getId() == idFactura)
                return factura;
        }
        return null;
    }

    private void manejarQuitarPromocion() {
        this.modelo.setPromocion(PromocionFactory.getPromocion("Sin Promocion"));
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }

    private void manejarPromocionDorada() {
        this.modelo.setPromocion(PromocionFactory.getPromocion("Promocion Dorada"));
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }

    private void manejarPromocionPlatino() {
        this.modelo.setPromocion(PromocionFactory.getPromocion("Promocion Platino"));
        this.vistaPrincipal.actualizarBotonesPromocion(this.modelo.getPromocion());
    }

    private void manejarMostrarTecnicos() {
        this.dialogoTecnicos.actualizar(this.modelo.getTecnicos());
        this.dialogoTecnicos.setVisible(true);
    }

    private void manejarNuevoTecnico() {
        NuevoTecnicoDTO nuevoTecnico = this.dialogoTecnicos.pedirNuevoTecnico();

        if (nuevoTecnico != null) {
            try {
                this.modelo.agregarTecnico(nuevoTecnico.getNombre());
                List<ITecnico> tecnicos = this.modelo.getTecnicos();
                this.vistaPrincipal.actualizarComboboxTecnicos(tecnicos);
                this.dialogoTecnicos.actualizar(tecnicos);
            } catch (TecnicoYaExisteException e) {
                this.dialogoTecnicos.mostrarAlertaTecnicoYaExiste();
            }
        }
    }

    private void manejarAvanzarMes() {
        boolean deberiaAvanzar = this.vistaPrincipal.confirmarAvanzarMes();

        if (deberiaAvanzar) {
            this.modelo.generadorFacturas();
            this.modelo.actualizadorEstado();
            LocalDate fecha = this.modelo.getFecha();
            LocalDate nuevaFecha = fecha.plusMonths(1);
            this.modelo.setFecha(nuevaFecha);
            this.vistaPrincipal.actualizarFecha(this.modelo.getFecha());
            try {
                String dni = this.vistaPrincipal.obtenerAbonadoSeleccionado();
                IAbonado abonado = this.modelo.getAbonado(dni);
                this.vistaPrincipal.actualizarTablaFacturas(abonado.getFacturasEmitidas());
                this.vistaPrincipal.actualizarDetallesAbonado(abonado);
            } catch (AbonadoNoExisteException e) {
                throw new RuntimeException(e);
            }
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
            this.dialogoTecnicos.actualizar(this.modelo.getTecnicos());
        } catch (ServicioEnCursoException e) {
            throw new RuntimeException(e);
        } catch (AbonadoNoExisteException e) {
            throw new RuntimeException(e);
        }
    }

    private void manejarCierre() {
        PersistenciaSistema.persistir();
    }


    @Override
    public void actionPerformed(ActionEvent evento) {
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

            case InterfazVistaPrincipal.CIERRE_VENTANA_PRINCIPAL -> manejarCierre();
        }
    }
}
