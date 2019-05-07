package com.beyondbell.currencyconverter

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent
import javafx.stage.Stage

fun main() = Application.launch(CurrencyConverter::class.java)

internal class CurrencyConverter : Application() {
	override fun start(primaryStage: Stage) {
		primaryStage.scene = Scene(FXMLLoader(javaClass.getResource("/currency_converter.fxml")).load())
		primaryStage.show()
	}

	lateinit var textfield1: TextField

	fun handleTextFieldOne(keyEvent: KeyEvent) {
		val field = keyEvent.target as TextField
		println(field.text)
		if (field.text.toDoubleOrNull() == null) {
			field.text = field.text.dropLast(1)
			field.positionCaret(field.text.length)
		}
	}
}