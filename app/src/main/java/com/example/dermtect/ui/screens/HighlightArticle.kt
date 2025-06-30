package com.example.dermtect.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R


@Composable
fun HighlightArticle(navController: NavController) {
    ArticleTemplate(
        title = "Skin Tone & Cancer",
        imageResId = R.drawable.skin_tone_and_cancer_risk,
        source = "The Skin Cancer Foundation",
        timestamp = "5 hours ago",
        subtitle = "Find out how your skin tone affects your risk of sun damage and skin cancer.",
        body = """
            Your skin tone plays a big role in how much UV damage you may get from the sun. While darker skin has more melanin—which offers some natural protection—it doesn’t make you immune to sunburn or skin cancer.

            Lighter skin burns more easily, increasing the risk of sun damage and skin cancer. But even people with darker skin tones can develop skin cancer, especially if they don’t use protection or check their skin regularly.

            Why Sunburn and Tanning Are Dangerous:
            Sunburn doesn’t just cause pain—it causes long-lasting skin damage. Even after redness fades, the harm stays. Sunburn and tanning damage your skin's DNA.
            Your skin tone plays a big role in how much UV damage you may get from the sun. While darker skin has more melanin—which offers some natural protection—it doesn’t make you immune to sunburn or skin cancer.

            Lighter skin burns more easily, increasing the risk of sun damage and skin cancer. But even people with darker skin tones can develop skin cancer, especially if they don’t use protection or check their skin regularly.

            Why Sunburn and Tanning Are Dangerous:
            Sunburn doesn’t just cause pain—it causes long-lasting skin damage. Even after redness fades, the harm stays. Sunburn and tanning damage your skin's DNA.
        """.trimIndent(),
        onBackClick = { navController.popBackStack() }
    )
}

@Preview(showBackground = true)
@Composable
fun HighlightArticlePreview() {
    HighlightArticle(navController = rememberNavController())
}

