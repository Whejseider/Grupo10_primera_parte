package persistencia;

import modelo.Factura;
import modelo.Sistema;
import modelo.interfaces.IFactura;
import modelo.tecnicos.ITecnico;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase con métodos estáticos para persistir con archivos binarios
 * el singleton de sistema.
 */
public class PersistenciaSistema {
    /**
     * Crea un DTO para ser persistido a partir del singleton Sistema.
     * @return El DTO creado.
     */
    private static SistemaDTO crearDTO() {
        SistemaDTO dto = new SistemaDTO();
        Sistema sistema = Sistema.getInstance();

        for (ITecnico tecnico : sistema.getTecnicos())
            tecnico.setDisponible(true);

        dto.setAbonados(sistema.getAbonados());
        dto.setFecha(sistema.getFecha());
        dto.setTecnicos(sistema.getTecnicos());
        dto.setPromocionActiva(sistema.getPromocion());

        return dto;
    }

    /**
     * Inicializa el estado del singleton a partir del DTO de persistencia.
     * @param dto El dto a utilizar
     */
    private static void inicializarSingleton(SistemaDTO dto) {
        Sistema sistema = Sistema.getInstance();

        sistema.setAbonados(dto.getAbonados());
        sistema.setFecha(dto.getFecha());
        sistema.setTecnicos(dto.getTecnicos());
        sistema.setPromocion(dto.getPromocionActiva());

        int maxId = 0;
        for (IFactura factura : sistema.getFacturasEmitidas()) {
            if (factura.getId() > maxId)
                maxId = factura.getId();
        }
        Factura.actualizarID(maxId);
    }

    /**
     * Persiste el sistema
     */
    public static void persistir() {
        OutputSistema output = new OutputSistema();
        try {
            output.abrir();
            output.escribir(crearDTO());
            output.cerrar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Depersiste el sistema
     */
    public static void depersistir() {
        InputSistema input = new InputSistema();
        try {
            input.abrir();
            SistemaDTO dto = input.leer();
            if (dto != null) {
                inicializarSingleton(dto);
            }
            input.cerrar();
        } catch (FileNotFoundException e) {
            persistir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
