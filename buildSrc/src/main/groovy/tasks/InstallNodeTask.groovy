package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class InstallNodeTask extends DefaultTask {
	@TaskAction
	def download() {
		println 'curl --silent --location https://deb.nodesource.com/setup_0.12 | sudo bash -'.execute().text
	}
}
