package iv.vas.learnwords.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import iv.vas.learnwords.data.database.model.WordDbModel

@Database(entities = [WordDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {


    companion object{

        private const val DB_NAME = "learn_db"

        private val lock = Any()

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            INSTANCE?.let {
                return it
            }
            synchronized(lock){

                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                     context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
    abstract fun getLearnDao() : LearnDao
}