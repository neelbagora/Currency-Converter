package com.beyondbell.currencyconverter

import javafx.application.Application
import javafx.stage.Stage

fun main() {
	Application.launch(Main::class.java)
}

class Main : Application() {
	override fun start(primaryStage: Stage) {
		primaryStage.show()
	}
}