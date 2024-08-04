
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private String servidor = "localhost";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String db = "pendientes";
    private String usuario = "root";
    private String clave = "";
    private String url = "jdbc:mysql://" + servidor + ":3306/" + db + "?useUnicode=true&characterEncoding=utf8";
    public String mensaje = "";

    public Connection conexion;

    public Conexion() {
        try {
            // Cargar el driver
            Class.forName(this.driver);
            
            // Estableciendo conexión
            this.conexion = DriverManager.getConnection(this.url, this.usuario, this.clave);

            // Verificar si la conexión fue exitosa
            if (this.conexion != null) {
                this.mensaje = "Se conectó correctamente a la base de datos";
            }
        } catch (ClassNotFoundException e) {
            this.mensaje = "Error al cargar el driver de la base de datos: " + e.getMessage();
        } catch (SQLException e) {
            this.mensaje = "Fallo al conectarse a la base de datos: " + e.getMessage();
        }
    }

    public Connection getConexion() {
        return this.conexion;
    }
}