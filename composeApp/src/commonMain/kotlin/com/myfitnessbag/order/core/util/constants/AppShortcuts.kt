package com.myfitnessbag.order.core.util.constants


enum class AppShortcuts(
    val id: String,
    val label: String,
    val uri: String,
) {
    Cart(
        id = "shortcut_cart",
        label = "View Cart",
        uri = "${AppConst.APP_PACKAGE}.shortcut_cart"
    ),
    Shop(
        id = "shortcut_shop",
        label = "View Items",
        uri = "${AppConst.APP_PACKAGE}.shortcut_shop"
    ),
    Account(
        id = "shortcut_account",
        label = "View Account",
        uri = "${AppConst.APP_PACKAGE}.shortcut_account"
    )
}