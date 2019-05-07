package com.beyondbell.currencyconverter.api.rates

import kotlinx.serialization.Serializable

@Serializable
internal data class RatesResponse(
	val success: Boolean,
	val rates: Rates? = null
)