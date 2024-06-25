import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gamboa.crudejercicio.R

class ReporteAdapter(private val context: Context, private val reportes: List<Reporte>) :
    RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reporte_card, parent, false)
        return ReporteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val reporte = reportes[position]
        holder.textTitulo.text = reporte.titulo
        holder.textDetalle.text = reporte.detalle

        // Configurar el botón para cambiar el estado activo/no activo del reporte
        holder.btnEstado.setOnClickListener {
            reporte.activo = !reporte.activo // Cambiar el estado activo/no activo
            // Aquí puedes implementar la lógica adicional, como guardar el estado en la base de datos o enviar una solicitud a una API
            notifyItemChanged(position) // Actualizar la vista del RecyclerView en esta posición
        }
    }

    override fun getItemCount(): Int {
        return reportes.size
    }

    inner class ReporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitulo: TextView = itemView.findViewById(R.id.text_titulo)
        val textDetalle: TextView = itemView.findViewById(R.id.text_detalle)
        val btnEstado: Button = itemView.findViewById(R.id.btn_estado)
    }
}
