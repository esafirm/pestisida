package nolambada.pestisida

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nolambada.pestisida.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val error = intent.getStringExtra(KEY_ERROR)
        val logs = intent.getStringExtra(KEY_LOG)

        binding.txtError.text = error
        binding.txtLogs.text = logs

        binding.btnSendReport.setOnClickListener {
            val message = """
                Error: $error
                Logs: $logs
            """.trimIndent()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    companion object {

        private const val KEY_ERROR = "Err.Error"
        private const val KEY_LOG = "Err.Log"

        fun start(context: Context, err: Throwable) {

            val logs = Pestisida.logger.asString()
            val error = "=> ${err.message}\n\n${err.stackTrace.joinToString("\n")}"

            val intent = Intent(context, ErrorActivity::class.java).apply {
                putExtra(KEY_ERROR, error)
                putExtra(KEY_LOG, logs)
            }
            context.startActivity(intent)
        }
    }
}