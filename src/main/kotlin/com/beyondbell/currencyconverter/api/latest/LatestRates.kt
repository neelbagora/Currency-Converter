package com.beyondbell.currencyconverter.api.latest

import kotlinx.serialization.Serializable

@Serializable
internal data class LatestRates(
	val EUR: Double,
	val USD: Double
)

suspend fun main() {
	println(requestLatestRates())
}