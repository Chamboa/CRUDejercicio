// MainActivity.kt
// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gamboa.crudejercicio.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reporteAdapter: ReporteAdapter
    private lateinit var reportes: MutableList<Reporte>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        reportes = obtenerDatosDeReportes().toMutableList() // Convertir la lista a mutable para permitir modificaciones

        reporteAdapter = ReporteAdapter(this, reportes)
        recyclerView.adapter = reporteAdapter
    }

    // Ejemplo de función para obtener datos de reportes (simulación de datos de prueba)
    private fun obtenerDatosDeReportes(): List<Reporte> {
        val lista = mutableListOf<Reporte>()
        lista.add(Reporte("Reporte 1", "Detalles del Reporte 1", true))
        lista.add(Reporte("Reporte 2", "Detalles del Reporte 2", false))
        lista.add(Reporte("Reporte 3", "Detalles del Reporte 3", true))
        return lista
    }

    // Función para actualizar un reporte en la base de datos y en la lista
    private fun actualizarReporte(reporte: Reporte) {
        val conexion = Conexion(this)
        val db = conexion.cadenaConexion()

        val updateQuery = "UPDATE reportes SET titulo = ?, detalle = ?, activo = ? WHERE titulo = ?"
        val statement = db.compileStatement(updateQuery)
        statement.bindString(1, reporte.titulo)
        statement.bindString(2, reporte.detalle)
        statement.bindLong(3, if (reporte.activo) 1 else 0)
        statement.bindString(4, reporte.titulo) // Suponiendo que el título es único y es usado como identificador

        statement.executeUpdateDelete()
        db.close()

        // Actualizar la lista en la interfaz de usuario
        val index = reportes.indexOfFirst { it.titulo == reporte.titulo }
        if (index != -1) {
            reportes[index] = reporte
            reporteAdapter.notifyItemChanged(index)
        }
    }

    // Función para eliminar un reporte en la base de datos y en la lista
    private fun eliminarReporte(titulo: String) {
        val conexion = Conexion(this)
        val db = conexion.cadenaConexion()

        val deleteQuery = "DELETE FROM reportes WHERE titulo = ?"
        val statement = db.compileStatement(deleteQuery)
        statement.bindString(1, titulo)

        statement.executeUpdateDelete()
        db.close()

        // Eliminar de la lista en la interfaz de usuario
        val index = reportes.indexOfFirst { it.titulo == titulo }
        if (index != -1) {
            reportes.removeAt(index)
            reporteAdapter.notifyItemRemoved(index)
        }
    }
}
