package gamboa.crudejercicio

import MainActivity
import Modelo.ClaseConexion
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.SQLException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val txtCorreo = findViewById<EditText>(R.id.etUsername)
        val txtContrasenia = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnLogin)

        btnIngresar.setOnClickListener {
            // Preparo el intent para cambiar a la pantalla de bienvenida
            val pantallaPrincipal = Intent(this, MainActivity::class.java)

            // Dentro de una corrutina hago un select en la base de datos
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val objConexion = ClaseConexion().cadenaConexion() ?: throw SQLException("Database connection is null")

                    val comprobarUsuario = objConexion.prepareStatement(
                        "SELECT * FROM usuarios WHERE username = ? AND password = ?"
                    )
                    comprobarUsuario.setString(1, txtCorreo.text.toString())
                    comprobarUsuario.setString(2, txtContrasenia.text.toString())

                    val resultado = comprobarUsuario.executeQuery()

                    if (resultado.next()) {
                        withContext(Dispatchers.Main) {
                            startActivity(pantallaPrincipal)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Usuario o contraseña incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    resultado.close()
                    comprobarUsuario.close()
                    objConexion.close()
                } catch (e: SQLException) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error en la conexión a la base de datos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    e.printStackTrace()
                }
            }
        }
    }
}
