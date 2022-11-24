package developer.behzod.passport.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import developer.behzod.passport.Models.User


@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun myDao(): MyDao


    companion object {
        private var appDataBase: AppDataBase? = null


        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (appDataBase == null) {
                appDataBase = Room.databaseBuilder(context, AppDataBase::class.java, "my_user")

                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            }

            return appDataBase!!
        }

    }
}