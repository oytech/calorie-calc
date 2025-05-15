package com.github.oytech.calorie.calc.core

import java.math.BigDecimal

private val MICRO = BigDecimal("1.0E-6")
private val MILLI = BigDecimal("1.0E-3")
private val KILO = BigDecimal("1.0E+3")

enum class Unit(val symbol: String, val prefix: BigDecimal = BigDecimal.ONE, baseUnit: Unit? = null) {
    L("L"),
    ML("ml", MILLI, L),

    G("g"),
    MCG("μg", MICRO, G),
    MG("mg", MILLI, G),
    KG("kg", KILO, G),

    IU("IU"),

    CAL("cal"),
    KCAL("kcal", KILO, CAL);

    val baseUnit: Unit = baseUnit ?: this

    fun isBaseUnit(): Boolean = this == baseUnit

    override fun toString(): String = symbol
}