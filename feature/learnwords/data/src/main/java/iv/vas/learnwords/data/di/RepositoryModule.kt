package iv.vas.learnwords.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iv.vas.learnwords.data.repository.LearnRepositoryImpl
import iv.vas.learnwords.domain.repository.LearnRepository
import iv.vas.learnwords.domain.usecase.learnwords.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
     abstract fun bindLearnRepository(
        learnRepositoryImpl: LearnRepositoryImpl
    ): LearnRepository

    companion object {
        @Provides
        @Singleton
        fun providePhoneticDataProvider(): iv.vas.learnwords.data.repository.PhoneticDataProvider {
            return iv.vas.learnwords.data.repository.PhoneticDataProvider
        }

        @Provides
        @Singleton
        fun provideExampleDataProvider(): iv.vas.learnwords.data.repository.ExampleDataProvider {
            return iv.vas.learnwords.data.repository.ExampleDataProvider
        }

        @Provides
        @Singleton
        fun provideDefinitionDataProvider(): iv.vas.learnwords.data.repository.DefinitionDataProvider {
            return iv.vas.learnwords.data.repository.DefinitionDataProvider
        }
    }
}
