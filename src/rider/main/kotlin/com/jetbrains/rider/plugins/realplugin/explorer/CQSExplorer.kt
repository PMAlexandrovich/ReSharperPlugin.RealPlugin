package com.jetbrains.rider.plugins.realplugin.explorer

import com.intellij.icons.AllIcons
import com.intellij.ide.SelectInContext
import com.intellij.ide.SelectInTarget
import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.project.Project
import com.jetbrains.rd.ide.model.testModel
import com.jetbrains.rd.util.reactive.adviseEternal
import com.jetbrains.rider.projectView.solution
import com.jetbrains.rider.projectView.views.SolutionViewPaneBase
import com.jetbrains.rider.projectView.views.SolutionViewVisitor
import com.jetbrains.rider.projectView.views.impl.SolutionViewSelectInTargetBase
import javax.swing.Icon

class CQSExplorer(project: Project) : SolutionViewPaneBase(project, createRootNode(project)) {

    init {
        myProject.solution.testModel.cQSFiles.adviseEternal {
            this.updateFromRoot()
        }
    }

    companion object {
        const val ID = "CQSExplorer"
        const val Title = "CQSTree"
        const val Weight = 1
        const val ShowProjectNamesOption = "show-project-names"
        const val ShowTildeFoldersOption = "show-tilde-folders"
        const val DefaultProjectPrefix = "Assembly-CSharp"

        val Icon = AllIcons.Toolwindows.WebToolWindow
        val IgnoredExtensions = hashSetOf("meta", "tmp")

        fun getInstance(project: Project) = tryGetInstance(project)!!

        fun tryGetInstance(project: Project): CQSExplorer? {
            return ProjectView.getInstance(project).getProjectViewPaneById(ID) as? CQSExplorer
        }

        private fun createRootNode(project: Project): CQSExplorerRootNode {
            return CQSExplorerRootNode(project)
        }
    }

    override fun createSelectInTarget() =  object : SolutionViewSelectInTargetBase(project) {

        // We have to return true here, because a file might be from a local package, which could be almost anywhere on
        // the filesystem
        override fun canSelect(context: SelectInContext) = true

        override fun selectIn(context: SelectInContext?, requestFocus: Boolean) {
            context?.let { select(it, null, requestFocus) }
        }

        override fun toString() = Title
        override fun getMinorViewId() = ID
        override fun getWeight() = Weight.toFloat()
    }



    override fun getTitle() = Title
    override fun getIcon() = Icon
    override fun getId() = ID
    override fun getWeight() = Weight
}