package neotica.movie.signin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    var email:String ?="",
    var name:String ?="",
    var password:String ?="",
    var url:String ?="",
    var username:String ?="",
    var saldo:String ?=""
) : Parcelable