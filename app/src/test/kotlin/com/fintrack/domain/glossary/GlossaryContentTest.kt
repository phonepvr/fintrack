package com.fintrack.domain.glossary

import com.fintrack.data.db.seed.SeedData
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class GlossaryContentTest {

    @Test
    @DisplayName("Every seeded asset class has a glossary entry")
    fun assetClassesCovered() {
        val seededIds = listOf(
            SeedData.MARKET_LINKED_ID,
            SeedData.FIXED_RETURN_ID,
            SeedData.CRYPTO_ID,
        )
        for (id in seededIds) {
            val entry = GlossaryContent.forAssetClassId(id)
            assertThat(entry).isNotNull()
            assertThat(entry!!.title).isNotEmpty()
            assertThat(entry.body).isNotEmpty()
        }
    }

    @Test
    @DisplayName("Every seeded sub-bucket has a glossary entry")
    fun subBucketsCovered() {
        val seededIds = listOf(
            SeedData.MF_ID, SeedData.NPS_ID, SeedData.STOCKS_ID,
            SeedData.PF_ID, SeedData.PPF_ID, SeedData.FD_ID,
            SeedData.WINT_WEALTH_ID, SeedData.BANK_ID,
            SeedData.CRYPTO_SB_ID,
        )
        for (id in seededIds) {
            val entry = GlossaryContent.forSubBucketId(id)
            assertThat(entry).isNotNull()
            assertThat(entry!!.title).isNotEmpty()
            assertThat(entry.body).isNotEmpty()
        }
    }

    @Test
    @DisplayName("Random UUIDs miss the seeded lookup and the caller falls back to generic copy")
    fun unknownIdsFallBack() {
        val random = UUID.randomUUID()
        assertThat(GlossaryContent.forAssetClassId(random)).isNull()
        assertThat(GlossaryContent.forSubBucketId(random)).isNull()
        // Generic fallbacks exist for the caller to substitute.
        assertThat(GlossaryContent.genericAssetClassFallback.body).isNotEmpty()
        assertThat(GlossaryContent.genericSubBucketFallback.body).isNotEmpty()
    }

    @Test
    @DisplayName("Wealth / engagement / loans lists are non-empty and have unique titles")
    fun listsPopulated() {
        val all = GlossaryContent.wealthMetrics + GlossaryContent.engagementTerms + GlossaryContent.loans
        assertThat(all).isNotEmpty()
        val titles = all.map { it.title }
        assertThat(titles).containsNoDuplicates()
        for (e in all) {
            assertThat(e.body).isNotEmpty()
        }
    }
}
