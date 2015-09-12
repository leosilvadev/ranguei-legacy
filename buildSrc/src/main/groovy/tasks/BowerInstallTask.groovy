package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class BowerInstallTask extends DefaultTask {
	@TaskAction
	def install() {
		println './node_modules/bower/bin/bower install'.execute().text
	}
}