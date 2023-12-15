import org.gradle.api.Project
import java.io.File
import java.util.Properties

fun Project.buildversionCode(): Int = getVersionPropertyInfo().run {
    if(major > 21) throw IllegalArgumentException("major version must be less than 21")
    (major * 100_000_000) + (minor * 1_000_000) + (patch * 10_000) + getBuildNumber()
}

fun Project.buildversionName() = getVersionPropertyInfo().appVersion

fun Project.getVersionPropertyInfo() = VersionPropertyInfo(getVersionProperties())

fun Project.getBetaVersionPropertyInfo() = getVersionProperties().getProperty("betaVersion").toInt()

data class VersionPropertyInfo(val appVersion: String) {
    constructor(properties: Properties) : this(
        properties.getProperty("appVersion")
            ?: throw IllegalArgumentException("appVersion is not found in properties")
    )

    val major: Int
    val minor: Int
    val patch: Int

    init {
        val parts = appVersion.split(".")
        if (parts.size != 3) {
            throw IllegalArgumentException("appVersion must be in the format 'x.x.x'")
        }

        major = parts[0].toIntOrNull()
            ?: throw IllegalArgumentException("Invalid major version number in appVersion")
        minor = parts[1].toIntOrNull()
            ?: throw IllegalArgumentException("Invalid minor version number in appVersion")
        patch = parts[2].toIntOrNull()
            ?: throw IllegalArgumentException("Invalid patch version number in appVersion")
    }
}

private fun Project.getVersionProperties(): Properties {
    val properties = Properties()
    file("version.properties").inputStream().run {
        properties.load(this)
        close()
    }

    if (properties.isEmpty) throw IllegalArgumentException("version.properties is empty")
    return properties
}

val buildNumberFile = File("buildNumber.txt")

fun getBuildNumber(): Int {
    return if (buildNumberFile.exists()) {
        buildNumberFile.readText().trim().toInt()
    } else {
        0
    }
}

fun incrementBuildNumberFile(): Int {
    var buildNumber = if (buildNumberFile.exists()) {
        buildNumberFile.readText().trim().toInt()
    } else {
        0
    }

    buildNumber++
    buildNumberFile.writeText(buildNumber.toString())
    return buildNumber
}

fun resetBuildNumberFile() {
    buildNumberFile.writeText("0")
}
