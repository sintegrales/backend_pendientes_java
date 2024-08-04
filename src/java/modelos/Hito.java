package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hito {
    
    private Connection conexion;

    // Método constructor
    public Hito(Connection conexion) {
        this.conexion = conexion;
    }
    
    // Métodos
    public ArrayList<Map<String, Object>> consulta() throws SQLException {
        String con = "SELECT * FROM hito ORDER BY fecha_entrega";
        Statement stmt = conexion.createStatement();
        ResultSet res = stmt.executeQuery(con);
        ArrayList<Map<String, Object>> vec = new ArrayList<>();

        while (res.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("id_hito", res.getInt("id_hito"));
            row.put("fecha", res.getDate("fecha"));
            row.put("nombre", res.getString("nombre"));
            row.put("fecha_entrega", res.getDate("fecha_entrega"));
            vec.add(row);
        }

        return vec;
    }
    
    public Map<String, String> eliminar(int id) throws SQLException {
        String del = "DELETE FROM hito WHERE id_hito = ?";
        PreparedStatement pstmt = conexion.prepareStatement(del);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        
        Map<String, String> vec = new HashMap<>();
        vec.put("resultado", "OK");
        vec.put("mensaje", "Dato eliminado");
        return vec;
    }
    
    public Map<String, String> insertar(HitoParams params) throws SQLException {
        String ins = "INSERT INTO hito(fecha, nombre, fecha_entrega) VALUES(?, ?, ?)";
        PreparedStatement pstmt = conexion.prepareStatement(ins);
        pstmt.setDate(1, Date.valueOf(params.fecha));
        pstmt.setString(2, params.nombre);
        pstmt.setDate(3, Date.valueOf(params.fecha_entrega));
        pstmt.executeUpdate();
        
        Map<String, String> vec = new HashMap<>();
        vec.put("resultado", "OK");
        vec.put("mensaje", "Dato guardado");
        return vec;
    }
    
    public Map<String, String> editar(int id, HitoParams params) throws SQLException {
        String editar = "UPDATE hito SET nombre = ?, fecha = ?, fecha_entrega = ? WHERE id_hito = ?";
        PreparedStatement pstmt = conexion.prepareStatement(editar);
        pstmt.setString(1, params.nombre);
        pstmt.setDate(2, Date.valueOf(params.fecha));
        pstmt.setDate(3, Date.valueOf(params.fecha_entrega));
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
        
        Map<String, String> vec = new HashMap<>();
        vec.put("resultado", "OK");
        vec.put("mensaje", "Dato editado");
        return vec;
    }
}

