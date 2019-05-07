package com.beyondbell.currencyconverter

import com.beyondbell.currencyconverter.api.rates.Rates
import com.beyondbell.currencyconverter.api.rates.requestRates
import kotlinx.coroutines.runBlocking
import java.util.Calendar

internal typealias Year = Int

internal object CurrencyData {
	private val year = Calendar.getInstance().get(Calendar.YEAR)
	private val month = Calendar.getInstance().get(Calendar.MONTH) + 1
	private val day = Calendar.getInstance().get(Calendar.DATE)

	private val data = HashMap<Year, Rates>()

	init {
		println(year.toString().padStart(4, '0') +
				"-${month.toString().padStart(2, '0')}" +
				"-${day.toString().padStart(2, '0')}")
	}

	operator fun get(yearsBack: Int = 0) = data.getOrPut(year - yearsBack) {
		if (yearsBack == 0) {
			runBlocking {
				requestRates()
			}
		} else {
			runBlocking {
				requestRates(
					(year - yearsBack).toString().padStart(4, '0') +
							"-${month.toString().padStart(2, '0')}" +
							"-${day.toString().padStart(2, '0')}"
				)
			}
		}
	}
}