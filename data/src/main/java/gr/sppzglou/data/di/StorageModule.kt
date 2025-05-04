package gr.sppzglou.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.data.local.AppDatabase
import gr.sppzglou.data.local.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(appDB: AppDatabase): LocalDataSource {
        return LocalDataSource(appDB)
    }

}