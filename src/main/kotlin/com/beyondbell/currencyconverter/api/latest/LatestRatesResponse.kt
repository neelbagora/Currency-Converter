package com.beyondbell.currencyconverter.api.latest

import kotlinx.serialization.Serializable

@Serializable
internal data class LatestRatesResponse(
	val success: Boolean,
	val timestamp: Long,
	val date: String,
	val base: String,
	val rates: LatestRates
)