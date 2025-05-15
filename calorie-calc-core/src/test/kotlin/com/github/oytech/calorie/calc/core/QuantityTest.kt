package com.github.oytech.calorie.calc.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class QuantityTest {

    @Test
    fun `2kg times 2 is equal comparing to 4kg`() {
        assertThat(Quantity(2, Unit.KG) * 2)
            .isEqualByComparingTo(Quantity(4, Unit.KG))
    }

    @Test
    fun `2,02kg plus 103g is equal comparing to 2123g`() {
        assertThat(Quantity(BigDecimal("2.02"), Unit.KG) + Quantity(103, Unit.G))
            .isEqualByComparingTo(Quantity(2123, Unit.G))
    }

    @Test
    fun `1kg is equal comparing to 1000g`() {
        assertThat(Quantity(1, Unit.KG))
            .isEqualByComparingTo(Quantity(1000, Unit.G))
    }

    @Test
    fun `1kg is less than 1001g`() {
        assertThat(Quantity(1, Unit.KG))
            .isLessThan(Quantity(1001, Unit.G))
    }

    @Test
    fun `1kg is greater than 999g`() {
        assertThat(Quantity(1, Unit.KG))
            .isGreaterThan(Quantity(999, Unit.G))
    }

    @Test
    fun `comparing different unit types throws`() {
        assertThatThrownBy { Quantity(1, Unit.KG).compareTo(Quantity(2, Unit.ML)) }
            .isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun `adding different unit types throws`() {
        assertThatThrownBy { Quantity(1, Unit.KG) + (Quantity(2, Unit.ML)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `initializing negative Quantity throws`() {
        assertThatThrownBy { Quantity(-1, Unit.KG) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `toString strips trailing zeroes`() {
        assertThat(Quantity(BigDecimal("1.0200"), Unit.KG).toString())
            .isEqualTo("1.02kg")
    }
}