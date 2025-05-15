package com.github.oytech.calorie.calc.core

import java.math.BigDecimal

data class Quantity(val value: BigDecimal, val unit: Unit) : Comparable<Quantity> {

    constructor(value: Int, unit: Unit) : this(BigDecimal(value), unit)

    init {
        require(value >= BigDecimal.ZERO) { "Quantity value must be non-negative" }
    }

    operator fun times(multiplier: BigDecimal): Quantity = Quantity(value * multiplier, unit)

    operator fun times(multiplier: Int): Quantity = times(BigDecimal(multiplier))

    operator fun plus(other: Quantity): Quantity {
        require(this.unit.baseUnit == other.unit.baseUnit) { "Base unit must be the same" }

        return if (this.unit == other.unit) {
            Quantity(this.value + other.value, this.unit)
        } else {
            this.withBaseUnit() + other.withBaseUnit()
        }
    }

    private fun withBaseUnit(): Quantity =
        if (unit.isBaseUnit()) this else Quantity(value * unit.prefix, unit.baseUnit)

    override fun compareTo(other: Quantity): Int {
        require(this.unit.baseUnit == other.unit.baseUnit) { "Base unit must be the same" }

        return this.withBaseUnit().value compareTo other.withBaseUnit().value
    }

    override fun toString(): String = value.stripTrailingZeros().toPlainString() + unit.toString()
}