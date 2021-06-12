package nolambda.pestisida.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import nolambada.pestisida.Pestisida

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Pestisida.log("Testing")
        Pestisida.log("Testing #2")
        Pestisida.log("Testing #3")

        findViewById<Button>(R.id.btn_crash).setOnClickListener {
            throw NullPointerException("Haha crash!")
        }

        findViewById<Button>(R.id.btn_report).setOnClickListener {
            Pestisida.report(this)
        }
    }
}