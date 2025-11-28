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
//
//    companion object {
////        @Provides
////        @Singleton
////        fun provideGetWordsByLevelUseCase(
////            learnRepository: LearnRepository
////        ): GetWordsByLevelUseCase {
////            return GetWordsByLevelUseCase(learnRepository)
////        }
//
//        @Provides
//        @Singleton
//        fun provideLoadNextWordUseCase(
//            learnRepository: LearnRepository
//        ): LoadNextWordUseCase {
//            return LoadNextWordUseCase(learnRepository)
//        }
//
//        @Provides
//        @Singleton
//        fun provideGetWordDetailsUseCase(
//            learnRepository: LearnRepository
//        ): GetWordDetailsUseCase {
//            return GetWordDetailsUseCase(learnRepository)
//        }
//
//        @Provides
//        @Singleton
//        fun provideUpdateWordProgressUseCase(
//            learnRepository: LearnRepository
//        ): UpdateWordProgressUseCase {
//            return UpdateWordProgressUseCase(learnRepository)
//        }
//
//        @Provides
//        @Singleton
//        fun provideMarkWordLearnedUseCase(
//            learnRepository: LearnRepository
//        ): MarkWordLearnedUseCase {
//            return MarkWordLearnedUseCase(learnRepository)
//        }
//    }
}
