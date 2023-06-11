package persistencia;

import modelo.Factura;
import modelo.Sistema;
import modelo.interfaces.IFactura;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PersistenciaSistema {
    private static SistemaDTO crearDTO() {
        SistemaDTO dto = new SistemaDTO();
        Sistema sistema = Sistema.getInstance();

        dto.setAbonados(sistema.getAbonados());
        dto.setFecha(sistema.getFecha());
        dto.setTecnicos(sistema.getTecnicos());
        dto.setPromocionActiva(sistema.getPromocionActiva());

        return dto;
    }

    private static void inicializarSingleton(SistemaDTO dto) {
        Sistema sistema = Sistema.getInstance();

        sistema.setAbonados(dto.getAbonados());
        sistema.setFecha(dto.getFecha());
        sistema.setTecnicos(dto.getTecnicos());
        sistema.setPromocionActiva(dto.getPromocionActiva());

        int maxId = 0;
        for (IFactura factura : sistema.getFacturasEmitidas()) {
            if (factura.getId() > maxId)
                maxId = factura.getId();
        }
        Factura.actualizarID(maxId);
    }

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
