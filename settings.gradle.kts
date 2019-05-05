rootProject.name = "currency-converter"
pluginManagement {
	resolutionStrategy {
		eachPlugin {
			when (requested.id.id) {
				"kotlinx-serialization" -> useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
			}
		}
	}
}