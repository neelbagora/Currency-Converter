package com.beyondbell.currencyconverter.api

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.parameter

internal val client = HttpClient {
	install(JsonFeature) {
		serializer = KotlinxSerializer(json)
	}
	defaultRequest {
		parameter("access_key", KEY)
	}
}