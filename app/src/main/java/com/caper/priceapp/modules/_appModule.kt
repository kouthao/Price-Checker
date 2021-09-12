package com.caper.priceapp.modules

import android.app.Application
import androidx.room.Room
import com.caper.priceapp.databases.PriceDataBase
import com.caper.priceapp.helper.DB_NAME
import com.caper.priceapp.interfaces.PriceDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/* Define koin module variables */
val databaseModule = module {
    single { provideDatabase(androidApplication())}
    single { providePriceDao(get()) }
}

/**
 * @return [PriceDataBase] instance
 */
fun provideDatabase(application: Application) =
    Room.databaseBuilder(application, PriceDataBase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

/**
 * @return [PriceDao] instance
 */
fun providePriceDao(priceDb: PriceDataBase): PriceDao = priceDb.priceDao()