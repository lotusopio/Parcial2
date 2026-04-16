package co.edu.poli.examen2_Nustes.servicios;

import co.edu.poli.examen2_Nustes.modelo.Asegurado;
import co.edu.poli.examen2_Nustes.modelo.Seguro;
import co.edu.poli.examen2_Nustes.modelo.SeguroVida;
import co.edu.poli.examen2_Nustes.modelo.SeguroVehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOSeguro implements CRUD<Seguro> {

    @Override
    public String create(Seguro t) throws Exception {

        Connection con = ConexionBD.getInstancia().getConexion();
        con.setAutoCommit(false);

        String SQL_INSERT_SEGURO = "INSERT INTO seguro (numero, fecha_expedicion, estado, asegurado_id) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(SQL_INSERT_SEGURO);
        ps.setString(1, t.getNumero());
        ps.setString(2, t.getFechaExpedicion());
        ps.setBoolean(3, t.isEstado());
        ps.setString(4, t.getAsegurado().getId());
        ps.executeUpdate();

        String SQL_INSERT_VIDA = "INSERT INTO seguro_vida (numero, beneficiario) VALUES (?, ?)";
        String SQL_INSERT_VEHICULO = "INSERT INTO seguro_vehiculo (numero, marca) VALUES (?, ?)";

        String sql = (t instanceof SeguroVida) ? SQL_INSERT_VIDA : SQL_INSERT_VEHICULO;
        ps = con.prepareStatement(sql);
        ps.setString(1, t.getNumero());
        if (t instanceof SeguroVida)
            ps.setString(2, ((SeguroVida) t).getBeneficiario());
        else
            ps.setString(2, ((SeguroVehiculo) t).getMarca());

        try {
            ps.executeUpdate();
            con.commit();
            return "✔ " + t.getClass().getSimpleName() + " [" + t.getNumero() + "] guardado correctamente.";
        } catch (Exception e) {
            con.rollback();
            return e.getMessage();
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public <K> Seguro readone(K num) throws Exception {

        Connection con = ConexionBD.getInstancia().getConexion();

        String SQL_SELECT_VIDA = "SELECT s.numero, s.fecha_expedicion, s.estado, "
                + "a.id AS asegurado_id, a.nombre AS asegurado_nombre, sv.beneficiario "
                + "FROM seguro_vida sv "
                + "INNER JOIN seguro s ON sv.numero = s.numero "
                + "INNER JOIN asegurado a ON s.asegurado_id = a.id "
                + "WHERE sv.numero = ?";

        PreparedStatement ps = con.prepareStatement(SQL_SELECT_VIDA);
        ps.setString(1, (String) num);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new SeguroVida(rs.getString("numero"), rs.getString("fecha_expedicion"),
                    rs.getBoolean("estado"),
                    new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")),
                    rs.getString("beneficiario"));
        }

        String SQL_SELECT_VEHICULO = "SELECT s.numero, s.fecha_expedicion, s.estado, "
                + "a.id AS asegurado_id, a.nombre AS asegurado_nombre, sv.marca "
                + "FROM seguro_vehiculo sv "
                + "INNER JOIN seguro s ON sv.numero = s.numero "
                + "INNER JOIN asegurado a ON s.asegurado_id = a.id "
                + "WHERE sv.numero = ?";

        ps = con.prepareStatement(SQL_SELECT_VEHICULO);
        ps.setString(1, (String) num);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new SeguroVehiculo(rs.getString("numero"), rs.getString("fecha_expedicion"),
                    rs.getBoolean("estado"),
                    new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")),
                    rs.getString("marca"));
        }

        return null;
    }

    @Override
    public List<Seguro> readall() {
        return null;
    }
}