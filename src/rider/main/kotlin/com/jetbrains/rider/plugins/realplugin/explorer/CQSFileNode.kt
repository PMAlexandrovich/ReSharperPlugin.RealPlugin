package com.jetbrains.rider.plugins.realplugin.explorer

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.SimpleTextAttributes
import com.jetbrains.rider.projectView.calculateFileSystemIcon
import com.jetbrains.rider.projectView.views.FileSystemNodeBase
import com.jetbrains.rider.projectView.views.NestingNode

class CQSFileNode(project: Project, file: VirtualFile, private val nestedVFiles: List<CQSFileNode>) :
    FileSystemNodeBase(project, file, emptyList())
{
    override fun createNode(virtualFile: VirtualFile, nestedFiles: List<NestingNode<VirtualFile>>): FileSystemNodeBase {
        return CQSFileNode(myProject, virtualFile, emptyList())
    }

    override fun calculateChildren(): MutableList<AbstractTreeNode<*>> {
        return nestedVFiles.toMutableList()
    }

    override fun update(presentation: PresentationData) {
        if (!virtualFile.isValid) return

        presentation.addText(name, SimpleTextAttributes.REGULAR_ATTRIBUTES)
        presentation.setIcon(virtualFile.calculateFileSystemIcon(myProject))
    }

}