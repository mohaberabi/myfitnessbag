package com.myfitnessbag.order.features.product_details.presentation.viewmodel

import com.myfitnessbag.order.features.product_details.domain.model.ProductAttrModel

sealed interface ProductDetailActions {
    data class GetData(
        val id: Int,
    ) : ProductDetailActions

    data class OnVariantClicked(
        val variant: ProductAttrModel,
    ) : ProductDetailActions

    data class OnVariantSaved(
        val selectedValue: String,
    ) : ProductDetailActions


    data class UpdateCartItem(
        val decrement: Boolean,
    ) : ProductDetailActions

    data object DismissVariantSheet : ProductDetailActions
    data object AddToCart : ProductDetailActions
}