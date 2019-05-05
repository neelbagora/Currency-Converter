package com.beyondbell.currencyconverter.api.latest

import com.beyondbell.currencyconverter.api.API
import com.beyondbell.currencyconverter.api.client
import io.ktor.client.request.get

/**
 * @param date YYYY-MM-DD
 */
internal suspend fun requestLatestRates(date: String = "latest") = client.get<LatestRatesResponse>("$API/$date")

suspend fun main() = println(requestLatestRates())