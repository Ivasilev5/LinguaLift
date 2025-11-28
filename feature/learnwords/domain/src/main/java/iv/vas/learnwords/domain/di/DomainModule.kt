package iv.vas.learnwords.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iv.vas.learnwords.domain.usecase.learnwords.FakeUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideFakeUseCase() : FakeUseCase{
        return FakeUseCase()
    }
}