package co.edu.poli.examen2_Nustes.unitaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Nustes.modelo.Asegurado;
import co.edu.poli.examen2_Nustes.modelo.Seguro;
import co.edu.poli.examen2_Nustes.modelo.SeguroVida;

public class TestSeguro {

    @Test
    void bloquear_cambiaEstadoAFalso() {
        Asegurado asegurado = new Asegurado("1", "Test");
        Seguro s = new SeguroVida("123", "25/12/2025", true, asegurado, "Beneficiario");
        String mensaje = s.bloquear();
        assertFalse(s.isEstado());
        assertTrue(mensaje.contains("BLOQUEADA"));
    }

    @Test
    void activar_cambiaEstadoAVerdadero() {
        Asegurado asegurado = new Asegurado("1", "Test");
        Seguro s = new SeguroVida("123", "25/12/2025", false, asegurado, "Beneficiario");
        String mensaje = s.activar();
        assertTrue(s.isEstado());
        assertTrue(mensaje.contains("ACTIVADA"));
    }

    @Test
    void getters_retornaValoresCorrectos() {
        Asegurado asegurado = new Asegurado("1", "Test");
        Seguro s = new SeguroVida("123", "25/12/2025", true, asegurado, "Beneficiario");
        assertEquals("123", s.getNumero());
        assertEquals("25/12/2025", s.getFechaExpedicion());
        assertTrue(s.isEstado());
        assertEquals(asegurado, s.getAsegurado());
    }

    @Test
    void setters_modificanValores() {
        Asegurado asegurado = new Asegurado("1", "Test");
        Asegurado nuevo = new Asegurado("2", "Nuevo");
        Seguro s = new SeguroVida("123", "25/12/2025", true, asegurado, "Beneficiario");
        s.setNumero("999");
        s.setFechaExpedicion("01/01/2030");
        s.setEstado(false);
        s.setAsegurado(nuevo);
        assertEquals("999", s.getNumero());
        assertEquals("01/01/2030", s.getFechaExpedicion());
        assertFalse(s.isEstado());
        assertEquals(nuevo, s.getAsegurado());
    }

    @Test
    void toString_contieneDatos() {
        Asegurado asegurado = new Asegurado("1", "Test");
        Seguro s = new SeguroVida("123", "25/12/2025", true, asegurado, "Beneficiario");
        String texto = s.toString();
        assertTrue(texto.contains("123"));
        assertTrue(texto.contains("25/12/2025"));
    }
}