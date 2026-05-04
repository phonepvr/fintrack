package com.fintrack.data

import com.fintrack.data.db.seed.SeedData
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Locks the spec §3.1-3.2 default catalog (3 asset classes, 9 sub-buckets,
 * 14 holdings). Catalog changes require a DB migration once the v3 build
 * is shipping.
 */
class SeedDataTest {

    @Test
    @DisplayName("Default asset classes match the spec §3.1 set")
    fun assetClasses() {
        val names = SeedData.defaultAssetClasses.sortedBy { it.displayOrder }.map { it.name }
        assertThat(names).containsExactly("Market Linked", "Fixed Return", "Crypto").inOrder()
    }

    @Test
    @DisplayName("Default sub-buckets match the spec §3.1 set")
    fun subBuckets() {
        val grouped = SeedData.defaultSubBuckets
            .groupBy { it.assetClassId }
            .mapValues { it.value.sortedBy { sb -> sb.displayOrder }.map { sb -> sb.name } }
        assertThat(grouped[SeedData.MARKET_LINKED_ID]).containsExactly("MF", "NPS", "Stocks").inOrder()
        assertThat(grouped[SeedData.FIXED_RETURN_ID]).containsExactly(
            "PF", "PPF", "FD", "Wint Wealth", "Bank",
        ).inOrder()
        assertThat(grouped[SeedData.CRYPTO_ID]).containsExactly("Crypto").inOrder()
    }

    @Test
    @DisplayName("Default holdings contain the 14 rows from spec §3.2")
    fun holdingsCatalog() {
        val expectedNames = listOf(
            "Degree212", "Kuvera", "Tata Capital",  // MF
            "NPS",                                   // NPS
            "Stocks (Direct)",                       // Stocks
            "EPF",                                   // PF
            "PPF",                                   // PPF
            "FD",                                    // FD
            "Wint Wealth",                           // Wint Wealth
            "HDFC", "Kotak", "AU", "ICICI",          // Bank
            "CoinDCX",                               // Crypto
        )
        assertThat(SeedData.defaultHoldings.map { it.name })
            .containsExactlyElementsIn(expectedNames).inOrder()
    }

    @Test
    @DisplayName("Default aim percentages sum to 100 per spec §3.3 (70/25/5)")
    fun defaultAimSums() {
        val total = SeedData.defaultAimPercentByAssetClass.values.sum()
        assertThat(total).isEqualTo(100)
        assertThat(SeedData.defaultAimPercentByAssetClass[SeedData.MARKET_LINKED_ID]).isEqualTo(70)
        assertThat(SeedData.defaultAimPercentByAssetClass[SeedData.FIXED_RETURN_ID]).isEqualTo(25)
        assertThat(SeedData.defaultAimPercentByAssetClass[SeedData.CRYPTO_ID]).isEqualTo(5)
    }

    @Test
    @DisplayName("Holding ids are deterministic across rebuilds")
    fun deterministicIds() {
        val first = SeedData.defaultHoldings.map { it.id }
        val second = SeedData.defaultHoldings.map { it.id }
        assertThat(first).isEqualTo(second)
    }

    @Test
    @DisplayName("Bank holdings live under the Fixed Return → Bank sub-bucket")
    fun banksUnderBankSubBucket() {
        val bankHoldings = SeedData.defaultHoldings.filter { it.subBucketId == SeedData.BANK_ID }
        assertThat(bankHoldings.map { it.name })
            .containsExactly("HDFC", "Kotak", "AU", "ICICI")
    }
}
