package com.myfitnessbag.order.core.presentation.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.back
import myfitnessbag.composeapp.generated.resources.bookmark
import myfitnessbag.composeapp.generated.resources.cart
import myfitnessbag.composeapp.generated.resources.email
import myfitnessbag.composeapp.generated.resources.empty
import myfitnessbag.composeapp.generated.resources.forward
import myfitnessbag.composeapp.generated.resources.heart
import myfitnessbag.composeapp.generated.resources.home
import myfitnessbag.composeapp.generated.resources.lang
import myfitnessbag.composeapp.generated.resources.location
import myfitnessbag.composeapp.generated.resources.lock
import myfitnessbag.composeapp.generated.resources.lock_open
import myfitnessbag.composeapp.generated.resources.moped
import myfitnessbag.composeapp.generated.resources.privacy
import myfitnessbag.composeapp.generated.resources.question
import myfitnessbag.composeapp.generated.resources.receipt
import myfitnessbag.composeapp.generated.resources.remove
import myfitnessbag.composeapp.generated.resources.search
import myfitnessbag.composeapp.generated.resources.terms
import org.jetbrains.compose.resources.vectorResource


val BookMarkIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.bookmark)

val CartIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.cart)

val HearIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.heart)

val HomeIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.home)

val LangIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.lang)

val LocationIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.location)

val MotoIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.moped)

val OrdersIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.receipt)

val SearchIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.search)

val TermsIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.terms)

val BackIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.back)

val LockIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.lock)

val LockOpenIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.lock_open)

val ForwardIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.forward)
val FAQIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.question)
val EmailIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.email)
val PolicyIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.privacy)
val EmptyIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.empty)

val RemoveIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.remove)
val AccountIcon: ImageVector
    @Composable
    get() = vectorResource(Res.drawable.account)
