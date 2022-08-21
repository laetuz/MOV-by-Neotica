package neotica.model


/*
private class movies {
    var desc, director, genre, poster, rated, title
}*/

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class movies (
    var desc:String ? = "",
    var director:String ? = "",
    var genre:String ? = "",
    var poster:String ? = "",
    var rated:String ? = "",
    var title:String ? = ""
): Parcelable
