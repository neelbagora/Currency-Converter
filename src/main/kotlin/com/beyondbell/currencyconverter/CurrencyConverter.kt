package com.beyondbell.currencyconverter

import com.beyondbell.currencyconverter.api.rates.Rates
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

fun main() = Application.launch(CurrencyConverter::class.java)

internal class CurrencyConverter : Application() {
	lateinit var textfield1: TextField
	lateinit var textfield2: TextField

	lateinit var currency1: ChoiceBox<String>
	lateinit var currency2: ChoiceBox<String>

	lateinit var graph: LineChart<String, Double>

	init {
		val currencies = ArrayList<String>()
		Rates::class.declaredMemberProperties.forEach {
			currencies.add(it.name)
		}

		GlobalScope.launch (Dispatchers.JavaFx){
			while (!this@CurrencyConverter::currency1.isInitialized) {
				delay(10)
			}
			currency1.items.addAll(currencies)
			currency1.value = currency1.items[0]
		}
		GlobalScope.launch (Dispatchers.JavaFx){
			while (!this@CurrencyConverter::currency2.isInitialized) {
				delay(10)
			}
			currency2.items.addAll(currencies)
			currency2.value = currency2.items[0]
		}
	}

	override fun start(primaryStage: Stage) {
		primaryStage.scene = Scene(FXMLLoader(javaClass.getResource("/currency_converter.fxml")).load())
		primaryStage.show()
	}

	fun handleTextFieldOne(keyEvent: KeyEvent) {
		val field = keyEvent.target as TextField
		if (field.text.toDoubleOrNull() == null  || keyEvent.character == "d" || keyEvent.character == "f") {
			field.text = field.text.dropLast(1)
			field.positionCaret(field.text.length)
		} else {
			TODO()
		}
	}

	fun handleTextFieldTwo(keyEvent: KeyEvent) {
		val field = keyEvent.target as TextField
		if (field.text.toDoubleOrNull() == null || keyEvent.character == "d" || keyEvent.character == "f") {
			field.text = field.text.dropLast(1)
			field.positionCaret(field.text.length)
		} else {
			TODO()
		}
	}

	fun handleChoiceBoxOne(actionEvent: ActionEvent) {
		val input = currency1.value
		val output = currency2.value

		repeat(10) {
			val data = CurrencyData[it]
			graph.data.add(XYChart.Series(data.second.toString(), data.first::class.memberProperties))
		}
	}

	fun handleChoiceBoxTwo(actionEvent: ActionEvent) {
		val input = currency1.value
		val output = currency2.value
	}
}