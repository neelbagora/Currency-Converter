package com.beyondbell.currencyconverter.api.rates

import com.beyondbell.currencyconverter.api.API
import com.beyondbell.currencyconverter.api.KEY
import com.beyondbell.currencyconverter.api.client
import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * @param date YYYY-MM-DD
 */
internal suspend fun requestRates(date: String = "latest"): Rates {
	val response = client.get<RatesResponse>("$API/$date") {
		parameter("access_key", KEY)
	}
	if (response.success) {
		return response.rates!!
	} else {
		error("Failed API Request for $date!")
	}
}