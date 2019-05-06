package com.beyondbell.currencyconverter.api.latest

import kotlinx.serialization.Serializable

@Serializable
internal data class LatestRatesResponse(
	val success: Boolean,
	val rates: LatestRates
)