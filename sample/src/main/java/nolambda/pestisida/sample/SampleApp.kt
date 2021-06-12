package nolambda.pestisida.sample

import android.app.Application
import nolambada.pestisida.Pestisida

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Pestisida.attach(this)
    }
}