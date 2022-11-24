package developer.behzod.passport.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import developer.behzod.passport.Models.User


@Dao
interface MyDao {

    @Insert
    fun addPerson(user: User)

    @Query
        ("select *from user")
    fun getAllPerson(): List<User>

}
