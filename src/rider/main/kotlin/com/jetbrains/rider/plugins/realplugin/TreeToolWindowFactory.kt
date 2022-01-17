package com.jetbrains.rider.plugins.realplugin

import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.projectView.impl.ProjectViewImpl
import com.intellij.ide.projectView.impl.ProjectViewToolWindowFactory
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.layout.PropertyBinding
import com.intellij.ui.layout.panel
import com.jetbrains.rd.ide.model.TestModel
import com.jetbrains.rd.ide.model.testModel
import com.jetbrains.rider.plugins.realplugin.explorer.CQSExplorer
import com.jetbrains.rider.projectView.solution
import java.io.File

private var pb: PropertyBinding<String>? = null
private var testModel : TestModel? = null

class TreeToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentManager = toolWindow.contentManager

        testModel = project.solution.testModel
        //val content = contentManager.factory.createContent(CQSExplorer(project).createComponent(), null, false)
//        var pv = ProjectViewImpl(project);
//        pv.setupImpl(toolWindow);
//        pv.removeProjectPane(pv.getProjectViewPaneById("FileSystemExplorer"))
//        pv.removeProjectPane(pv.getProjectViewPaneById("SolutionExplorer"))
//        pv.removeProjectPane(pv.getProjectViewPaneById("AssemblyExplorer"))
        //contentManager.addContent(createDialogPanel(project))
    }

    fun createDialogPanel(project: Project): DialogPanel = panel {
        row {
            button("hello"){
                var manager = FileEditorManager.getInstance(project)
                val file = VfsUtil.findFileByIoFile(File("C:\\Users\\Максим\\RiderProjects\\UnitSystem\\UnitSystem\\Second.cs"), false);
                if(file == null){
                    return@button;
                }
                manager.openEditor(OpenFileDescriptor(project, file, 0, 0), true)
            }
        }
    }
}



data class MyData(var myString: String, var subData: List<MyData>? = null)