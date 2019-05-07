package com.beyondbell.currencyconverter

import com.beyondbell.currencyconverter.api.rates.Rates
import javafx.application.Application
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

		GlobalScope.launch(Dispatchers.JavaFx) {
			while (!this@CurrencyConverter::currency1.isInitialized) {
				delay(10)
			}
			currency1.items.addAll(currencies)
			currency1.value = currency1.items[0]
		}
		GlobalScope.launch(Dispatchers.JavaFx) {
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
		val field = textfield1
		if (field.text.toDoubleOrNull() == null || keyEvent.character == "d" || keyEvent.character == "f") {
			field.text = field.text.dropLast(1)
			field.positionCaret(field.text.length)
		} else {
			updateOutput()
		}
	}

	fun handleTextFieldTwo(keyEvent: KeyEvent) {
		val field = textfield2
		if (field.text.toDoubleOrNull() == null || keyEvent.character == "d" || keyEvent.character == "f") {
			field.text = field.text.dropLast(1)
			field.positionCaret(field.text.length)
		} else {
			updateInput()
		}
	}

	private fun updateInput() {
		val output = textfield2.text.toIntOrNull() ?: 1
		val ratio = graph.data.first().data.last().YValueProperty().get()
		textfield1.text = (output / ratio).toString()
	}

	private fun updateOutput() {
		val input = textfield1.text.toIntOrNull() ?: 1
		val ratio = graph.data.first().data.last().YValueProperty().get()
		textfield2.text = (input * ratio).toString()
	}


	fun handleChoiceBoxOne() {
		handleChoiceBox()
		if (graph.data.firstOrNull()?.data?.lastOrNull() != null) {
			updateOutput()
		}
	}

	fun handleChoiceBoxTwo() {
		handleChoiceBox()
		if (graph.data.firstOrNull()?.data?.lastOrNull() != null) {
			updateInput()
		}
	}

	private fun handleChoiceBox() {
		graph.animated = false

		val input = currency1.value
		val output = currency2.value

		if (input == null || output == null) return

		val series = graph.data.firstOrNull() ?: run {
			val series = XYChart.Series<String, Double>()
			graph.data.add(series)
			series
		}

		series.data.clear()
		repeat(10) {
			val data = CurrencyData[9 - it]
			val reflectedGetters = data.first::class.memberProperties
			val reflectedInput = reflectedGetters.first { it.name == input }.getter.call(data.first) as Double
			val reflectedOutput = reflectedGetters.first { it.name == output }.getter.call(data.first) as Double
			val ratio = reflectedInput / reflectedOutput
			series.data.add(XYChart.Data(data.second.toString(), ratio))
		}
	}
}