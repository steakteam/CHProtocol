package kr.entree.chprotocol.build

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

/**
 * Created by JunHyung Im on 2020-07-02
 */
class VersionTask extends DefaultTask {
    static File getVersionFile(Project project) {
        return new File("${project.projectDir}/version.txt")
    }

    static String readVersion(Project project) {
        return getVersionFile(project).text
    }

    @Input
    @Option(option = "build-version", description = "Configure the version of CHProtocol.")
    def version = project.objects.property(String)

    @TaskAction
    def execute() {
        getVersionFile(project).text = version
        project.version = version
    }
}
