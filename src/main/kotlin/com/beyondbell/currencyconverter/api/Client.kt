package com.beyondbell.currencyconverter.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

internal val client = HttpClient {
	install(JsonFeature) {
		serializer = KotlinxSerializer(json)
	}
}