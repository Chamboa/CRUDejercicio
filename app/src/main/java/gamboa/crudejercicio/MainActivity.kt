// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gamboa.crudejercicio.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reporteAdapter: ReporteAdapter
    private lateinit var reportes: List<Reporte>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        reportes = obtenerDatosDeReportes() // Aquí deberías cargar los datos de tus reportes desde alguna fuente

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
}
