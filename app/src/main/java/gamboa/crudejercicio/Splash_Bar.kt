package gamboa.crudejercicio

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar

class Splash_Bar : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private val splashTime = 3000L
    private val progressStepTime = 30L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_carga)

        progressBar = findViewById(R.id.pbCarga)
        startProgressBar()
    }

    private fun startProgressBar() {
        val handler = Handler(Looper.getMainLooper())
        val updateRunnable = object : Runnable {
            var progressStatus = 0

            override fun run() {
                if (progressStatus < 100) {
                    progressStatus++
                    progressBar.progress = progressStatus
                    handler.postDelayed(this, progressStepTime)
                } else {
                    // Cambia a la siguiente actividad cuando la ProgressBar se complete
                    val intent = Intent(this@Splash_Bar, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        handler.post(updateRunnable)
    }
}
