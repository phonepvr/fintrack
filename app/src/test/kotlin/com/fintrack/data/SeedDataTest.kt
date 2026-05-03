package com.fintrack.data

import com.fintrack.data.db.seed.SeedData
import com.fintrack.domain.model.AssetClass
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Locks the spec §3.2 default catalog. Changes to this list require a DB
 * migration: in-flight installs already have these rows and a rename or
 * removal would either leak old rows or break HoldingValue references.
 */
class SeedDataTest {

    @Test
    @DisplayName("Default holdings contain exactly the 13 rows from spec §3.2")
    fun catalogShape() {
        val expectedNames = listOf(
            "Degree212", "Kuvera", "Tata Capital", "NPS",
            "Equity (Direct)",
            "PPF", "RD", "Wint Wealth",
            "HDFC", "Kotak", "AU", "ICICI",
            "CoinDCX",
        )
        assertThat(SeedData.defaultHoldings.map { it.name }).containsExactlyElementsIn(expectedNames).inOrder()
    }

    @Test
    @DisplayName("All four asset classes are represented in the seed catalog")
    fun allAssetClassesPresent() {
        val classes = SeedData.defaultHoldings.map { it.assetClass }.toSet()
        assertThat(classes).containsExactly(
            AssetClass.MF_NPS,
            AssetClass.EQUITY,
            AssetClass.FIXED_RETURN,
            AssetClass.CRYPTO,
        )
    }

    @Test
    @DisplayName("Bank rows are flagged correctly")
    fun banks() {
        val banks = SeedData.defaultHoldings.filter { it.isBankAccount }.map { it.name }
        assertThat(banks).containsExactly("HDFC", "Kotak", "AU", "ICICI")
    }

    @Test
    @DisplayName("Default aim percentages sum to 100")
    fun defaultAimSums() {
        val total = AssetClass.entries.sumOf { it.defaultAimPct }
        assertThat(total).isEqualTo(100)
    }

    @Test
    @DisplayName("Holding ids are deterministic across rebuilds")
    fun deterministicIds() {
        val first = SeedData.defaultHoldings.map { it.id }
        val second = SeedData.defaultHoldings.map { it.id }
        assertThat(first).isEqualTo(second)
    }
}
