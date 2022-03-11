package finnish.parliament
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* setups periodic work request daily
 */

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*
import finnish.parliament.data.worker.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApp: Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("CREATED APP!!!!!!!", "MyApp onCreate()")
        appContext = applicationContext
        delayedInit()
    }
    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }
    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(appContext).enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}


