package com.example.feature_tours.di

import android.content.Context
import com.example.feature_tours.data.data_source.database.DatabaseToursDataSource
import com.example.feature_tours.data.data_source.database.IDatabaseToursDataSource
import com.example.feature_tours.data.database.ToursDao
import com.example.feature_tours.data.database.ToursDatabase
import com.example.feature_tours.data.data_source.server.IServerToursDataSource
import com.example.feature_tours.data.data_source.server.ServerToursDataSource
import com.example.feature_tours.data.server.ToursService
import com.example.feature_tours.data.repository.IToursRepository
import com.example.feature_tours.data.repository.ToursRepository
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.domain.interactor.ToursInteractor
import com.example.feature_tours.start.IToursStarter
import com.example.feature_tours.start.ToursStarter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ToursModule {

    @Provides
    @ToursScope
    fun provideToursInteractor(
        toursRepository: IToursRepository
    ): IToursInteractor {
        return ToursInteractor(toursRepository)
    }

    @Provides
    @ToursScope
    fun provideToursRepository(
        serverToursDataSource: IServerToursDataSource,
        databaseToursDataSource: IDatabaseToursDataSource
    ): IToursRepository {
        return ToursRepository(serverToursDataSource, databaseToursDataSource)
    }

    @Provides
    @ToursScope
    fun provideServerToursDataSource(toursService: ToursService): IServerToursDataSource {
        return ServerToursDataSource(toursService)
    }

    @Provides
    @ToursScope
    fun provideDatabaseToursDataSource(toursDao: ToursDao): IDatabaseToursDataSource {
        return DatabaseToursDataSource(toursDao)
    }

    @Provides
    @ToursScope
    fun provideToursDatabase(context: Context): ToursDatabase {
        return ToursDatabase.getDatabase(context)
    }

    @Provides
    @ToursScope
    fun provideToursDao(toursDatabase: ToursDatabase): ToursDao {
        return toursDatabase.getDao()
    }

    @Provides
    @ToursScope
    fun provideToursService(retrofit: Retrofit): ToursService {
        return retrofit.create()
    }

    @Provides
    @ToursScope
    fun provideToursStarter(): IToursStarter {
        return ToursStarter()
    }
}