package com.myfitnessbag.order.features.product_details.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.UiText
import com.myfitnessbag.order.core.util.constants.CartIdHasher
import com.myfitnessbag.order.features.product_details.domain.model.ProductAttrModel
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel


enum class ProductDetailStatus {
    Initial,
    Loading,
    Error,
    Populated,

}

data class ProductDetailState(
    val state: ProductDetailStatus = ProductDetailStatus.Initial,
    val related: List<ProductListingModel> = listOf(),
    val product: ProductDetailModel? = null,
    val error: UiText = UiText.Empty,
    val foundCartItem: CartItemModel? = null,
    val loadingCart: Boolean = false
) {


    val newCartItem: CartItemModel
        get() = CartItemModel(
            id = product?.product?.let { it.id.toString() } ?: "",
            qty = 1,
            price = product?.product?.price?.toDoubleOrNull() ?: 1.0,
            name = product?.product?.name ?: ""
        )
}
