package gr.sppzglou.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.domain.Repository
import gr.sppzglou.domain.cases.FindWordUseCase
import gr.sppzglou.domain.cases.GetFavsUseCase
import gr.sppzglou.domain.cases.GetWordUseCase
import gr.sppzglou.domain.cases.SaveWordUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideFindWordUseCase(repository: Repository) = FindWordUseCase(repository)

    @Provides
    fun provideGetWordUseCase(repository: Repository) = GetWordUseCase(repository)

    @Provides
    fun provideSaveWordUseCase(repository: Repository) = SaveWordUseCase(repository)

    @Provides
    fun provideGetFavsUseCase(repository: Repository) = GetFavsUseCase(repository)
}