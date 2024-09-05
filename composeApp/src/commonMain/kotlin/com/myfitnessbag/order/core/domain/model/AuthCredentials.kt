package com.myfitnessbag.order.core.domain.model

import com.myfitnessbag.order.core.util.constants.AppConst


data class AuthCredentials(
    val key: String = AppConst.Consumer_KEY,
    val secret: String = AppConst.Consumer_SECRET,
    val method: AuthCredMethod = AuthCredMethod.Basic,
)


enum class AuthCredMethod {
    Basic,

}