package finnish.parliament.data.worker
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* Refreshing database, calls MembersRepository to update database with new values
 */
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import finnish.parliament.data.repository.MembersRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "parliament.data.worker.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val repository = MembersRepository
        try {
            repository.refreshData()
        }catch (e:HttpException){
            return Result.retry()
        }
        return Result.success()
    }
}