package co.edu.poli.examen2_Nustes.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.examen2_Nustes.modelo.Asegurado;

public class DAOAsegurado implements CRUD<Asegurado> {

    @Override
    public String create(Asegurado t) {
        return null;
    }

    @Override
    public <K> Asegurado readone(K id) throws Exception {
        return null;
    }

    @Override
    public List<Asegurado> readall() throws Exception {

        List<Asegurado> lista = new ArrayList<>();

        Connection con = ConexionBD.getInstancia().getConexion();

        String sql = "SELECT id, nombre FROM asegurado";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Asegurado a = new Asegurado(
                rs.getString("id"),
                rs.getString("nombre")
            );
            lista.add(a);
        }

        return lista; // ✅ SIEMPRE lista (nunca null)
    }
}