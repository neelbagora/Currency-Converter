package com.beyondbell.currencyconverter.api

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

internal val json = Json(JsonConfiguration.Stable.copy(strictMode = false, encodeDefaults = false))