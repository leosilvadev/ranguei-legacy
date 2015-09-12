package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class NpmInstallTask extends DefaultTask {
	
	@TaskAction
	def install() {
		println 'npm install'.execute().text
	}
	
}