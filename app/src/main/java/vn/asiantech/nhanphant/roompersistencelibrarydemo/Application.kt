package vn.asiantech.nhanphant.roompersistencelibrarydemo

import android.app.Application
import android.arch.persistence.room.Room
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local.AppDatabase

/**
 * AsianTech Inc.
 * Created by nhanphant on 27/12/2017.
 */
class Application : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
//                    .addMigrations(object : Migration(1, 2) {
//                        override fun migrate(database: SupportSQLiteDatabase) {
//                            database.execSQL("ALTER TABLE user ADD COLUMN gender INTEGER")
//                        }
//                    })
                .build()
    }
}
