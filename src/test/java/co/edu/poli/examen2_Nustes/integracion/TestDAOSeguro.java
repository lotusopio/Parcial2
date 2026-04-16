package co.edu.poli.examen2_Nustes.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Nustes.modelo.Asegurado;
import co.edu.poli.examen2_Nustes.modelo.Seguro;
import co.edu.poli.examen2_Nustes.modelo.SeguroVida;
import co.edu.poli.examen2_Nustes.modelo.SeguroVehiculo;
import co.edu.poli.examen2_Nustes.servicios.DAOSeguro;

public class TestDAOSeguro {

    DAOSeguro dao = new DAOSeguro();

    @Test
    void create_seguroVida_y_readone() throws Exception {
        Asegurado asegurado = new Asegurado("A001", "Test");
        SeguroVida vida = new SeguroVida(
                "999001",
                "25/12/2025",
                true,
                asegurado,
                "Beneficiario Test"
        );
        String result = dao.create(vida);
        assertTrue(result.contains("guardado"));
        Seguro s = dao.readone("999001");
        assertNotNull(s);
        assertTrue(s instanceof SeguroVida);
        SeguroVida sv = (SeguroVida) s;
        assertEquals("Beneficiario Test", sv.getBeneficiario());
    }

    @Test
    void create_seguroVehiculo_y_readone() throws Exception {
        Asegurado asegurado = new Asegurado("A001", "Test");
        SeguroVehiculo vehiculo = new SeguroVehiculo(
                "999002",
                "25/12/2025",
                true,
                asegurado,
                "Toyota"
        );
        String result = dao.create(vehiculo);
        assertTrue(result.contains("guardado"));
        Seguro s = dao.readone("999002");
        assertNotNull(s);
        assertTrue(s instanceof SeguroVehiculo);
        SeguroVehiculo sv = (SeguroVehiculo) s;
        assertEquals("Toyota", sv.getMarca());
    }

    @Test
    void readone_noExiste() throws Exception {
        Seguro s = dao.readone("000000");
        assertNull(s);
    }
}