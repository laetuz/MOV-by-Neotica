package neotica.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class plays (
    var nama: String ?="",
    var url: String ?=""
): Parcelable