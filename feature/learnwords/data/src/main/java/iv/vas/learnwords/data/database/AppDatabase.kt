package iv.vas.learnwords.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import iv.vas.learnwords.data.database.model.CefrWordEntity
import iv.vas.learnwords.data.database.model.WordDbModel

@Database(
    entities = [CefrWordEntity::class, WordDbModel::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = db
                return db
            }
        }
    }
    abstract fun getLearnDao() : LearnDao
}