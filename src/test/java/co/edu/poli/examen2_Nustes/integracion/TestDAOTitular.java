package co.edu.poli.examen2_Nustes.integracion;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Nustes.modelo.Titular;
import co.edu.poli.examen2_Nustes.servicios.DAOTitular;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestDAOTitular {

    @Test
    void readAll_noDebeRetornarNull() throws Exception {
        DAOTitular dao = new DAOTitular();

        List<Titular> lista = dao.readall();

        assertNotNull(lista);
    }

    @Test
    void readAll_listaInicializada() throws Exception {
        DAOTitular dao = new DAOTitular();

        List<Titular> lista = dao.readall();

        assertTrue(lista.size() >= 0);
    }

    @Test
    void readAll_objetosValidos() throws Exception {
        DAOTitular dao = new DAOTitular();

        List<Titular> lista = dao.readall();

        if (!lista.isEmpty()) {
            Titular t = lista.get(0);

            assertNotNull(t.getId());
            assertNotNull(t.getNombre());
        }
    }
}