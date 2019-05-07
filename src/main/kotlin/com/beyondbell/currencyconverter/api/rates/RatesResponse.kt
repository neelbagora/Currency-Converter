package com.beyondbell.currencyconverter.api.rates

import kotlinx.serialization.Serializable

@Serializable
internal data class RatesResponse(
	val rates: Rates? = null
)