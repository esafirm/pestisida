package nolambada.pestisida

import android.app.Application
import android.content.Context

@Suppress("SpellCheckingInspection")
object Pestisida {

    internal val logger = LimitedLogger(20)

    fun report(context: Context, err: Throwable = Exception("Bug Report")) {
        ErrorActivity.start(context, err)
    }

    fun log(message: String) {
        logger.log(message)
    }

    fun attach(app: Application) {
        val context = app.applicationContext
        val default = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            ErrorActivity.start(context, e)
            default?.uncaughtException(t, e)
        }
    }
}