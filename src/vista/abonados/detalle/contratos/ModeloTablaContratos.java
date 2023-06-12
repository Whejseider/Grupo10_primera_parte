package vista.abonados.detalle.contratos;

import modelo.interfaces.IContrato;
import vista.componentes.ModeloTablaBase;

import java.util.List;

public class ModeloTablaContratos extends ModeloTablaBase {
    private static final long serialVersionUID = 1L;

    public ModeloTablaContratos() {
        super(new String[]{"ID", "Domicilio", "Camaras", "Botones", "Tiene movil"});
    }
    /**
     * Actualiza la tabla de contratos con datos nuevos
     * 
     * @param contratos Los contratos a utilizar
     */
    public void actualizar(List<IContrato> contratos) {
        assert contratos != null;
                
        this.setRowCount(0);
        for (int i = 0; i < contratos.size(); i++) {
            IContrato contrato = contratos.get(i);
            
            String identificador = Integer.toString(contrato.getIdentificador());
            String domicilio = contrato.getDomicilio();
            int camaras = contrato.getCantCamaras();
            int botones = contrato.getCantBotones();
            boolean movil = contrato.getTieneMovil();
            
            Object[] row = {identificador, domicilio, camaras, botones, movil ? "Si" : "No"};
            this.addRow(row);
        }
    }
}
