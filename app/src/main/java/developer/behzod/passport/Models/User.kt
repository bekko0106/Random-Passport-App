package developer.behzod.passport.Models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var image: String? = null
    var birthday = ""
    var seriesNumber = ""
    var surname = ""
    var name= ""

    @Ignore
    constructor(id: Int, image: String?, birthday: String, seriesNumber: String, surname: String) {
        this.id = id
        this.image = image
        this.birthday = birthday
        this.seriesNumber = seriesNumber
        this.surname = surname
        this.name=name
    }

    constructor(image: String?, birthday: String, seriesNumber: String, surname: String) {
        this.image = image
        this.birthday = birthday
        this.seriesNumber = seriesNumber
        this.surname = surname
        this.name=name

    }


}