// ClaseConexion.kt
package Modelo

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ClaseConexion {
    fun cadenaConexion(): Connection? {
        return try {
            val ip = "jdbc:oracle:thin:@192.168.0.21:1521:xe"
            val usuario = "ejercicio1"
            val contrasena = "."

            DriverManager.getConnection(ip, usuario, contrasena)
        } catch (e: SQLException) {
            println("Error en la conexión: ${e.message}")
            return null
        }
    }
}
