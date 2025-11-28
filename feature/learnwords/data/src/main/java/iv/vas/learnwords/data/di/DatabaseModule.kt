package iv.vas.learnwords.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import iv.vas.core.utils.Constants
import iv.vas.learnwords.data.database.AppDatabase
import iv.vas.learnwords.data.database.LearnDao
import iv.vas.learnwords.data.network.model.LearnApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideLearnDao(appDatabase: AppDatabase): LearnDao {
        return appDatabase.getLearnDao()
    }

    @Provides
    @Singleton
    fun provideLearnApiService(): LearnApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LearnApiService::class.java)
    }
}
