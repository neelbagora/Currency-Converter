package com.beyondbell.currencyconverter.api.latest

import kotlinx.serialization.Serializable

@Serializable
internal data class LatestRates(
	val EUR: Double
)