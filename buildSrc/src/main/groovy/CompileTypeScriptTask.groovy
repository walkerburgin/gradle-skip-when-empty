import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction

public abstract class CompileTypeScriptTask extends DefaultTask {
    @InputFiles
    @SkipWhenEmpty
    @PathSensitive(PathSensitivity.RELATIVE)
    public abstract ConfigurableFileTree getSourceFiles()

    @OutputDirectory
    public abstract DirectoryProperty getOutputDirectory()

    @TaskAction
    public final void run() {
        Project project = getProject()

        project.delete(getOutputDirectory())

        def sourceRoot = sourceFiles.dir.toPath()
        sourceFiles.files.each { it ->
            def outputFile = outputDirectory.get().file(sourceRoot.relativize(it.toPath()).toString().replaceAll('.ts$', '.js'))
            outputFile.asFile.parentFile.mkdirs()
            outputFile.asFile << it.text
        }
    }
}

