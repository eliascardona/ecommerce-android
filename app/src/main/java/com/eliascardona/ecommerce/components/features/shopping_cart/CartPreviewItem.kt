package com.eliascardona.ecommerce.components.features.shopping_cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct

@Composable
fun CartPreviewItem(
    product: SelectedProduct
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.spacedBy(12.dp),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Image(
            painter =
                painterResource(product.imageRes),

            contentDescription =
                product.name,

            modifier =
                Modifier.size(56.dp)
        )

        Column {

            Text(
                text = product.name,
                style =
                    MaterialTheme
                        .typography
                        .titleSmall
            )

            Text(
                text =
                    "Qty: ${product.quantity}",

                style =
                    MaterialTheme
                        .typography
                        .bodySmall
            )

            Text(
                text =
                    "$ ${product.unitPrice}",

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium
            )
        }
    }
}