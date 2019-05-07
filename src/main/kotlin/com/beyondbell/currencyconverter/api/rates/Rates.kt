package com.beyondbell.currencyconverter.api.rates

import kotlinx.serialization.Serializable

@Serializable
internal data class Rates(
	val EUR: Double,
	val USD: Double,
	val CAD: Double,
	val AUD: Double,
	val JPY: Double,
	val CNY: Double,
	val CHF: Double
)