package com.and.dev.homeservice.model

import com.google.gson.annotations.SerializedName

data class UserData (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("email"  ) var email  : String? = null,
    @SerializedName("photo"  ) var photo  : String? = null,
    @SerializedName("phone"  ) var phone  : String? = null,
    @SerializedName("active" ) var active : String? = null,
    @SerializedName("token"  ) var token  : String? = null

)