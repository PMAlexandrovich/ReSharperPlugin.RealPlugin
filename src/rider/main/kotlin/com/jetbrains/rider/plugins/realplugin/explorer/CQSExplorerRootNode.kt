package com.jetbrains.rider.plugins.realplugin.explorer

import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.ui.tree.LeafState
import com.jetbrains.rd.ide.model.testModel
import com.jetbrains.rd.util.reactive.adviseEternal
import com.jetbrains.rd.util.reactive.valueOrDefault
import com.jetbrains.rider.projectView.solution
import com.jetbrains.rider.projectView.views.NestingNode
import com.jetbrains.rider.projectView.views.SolutionViewRootNodeBase
import java.io.File

class CQSExplorerRootNode(project: Project)
    : SolutionViewRootNodeBase(project) {

    override fun calculateChildren(): MutableList<AbstractTreeNode<*>> {
        val files = myProject.solution.testModel.cQSFiles.valueOrDefault(emptyArray())

        fun iterate(children: Array<String>) : List<CQSFileNode>{
            return children.map { x ->
                val file = VfsUtil.findFileByIoFile(File(x), false)
                CQSFileNode(myProject, file!!,
                    iterate(files.firstOrNull(predicate = {y -> y.filePath == x})?.nestedFiles ?: emptyArray())) }
        }
        val root = files.firstOrNull();

        val nodes = if(root != null){
            val rootVFile = VfsUtil.findFileByIoFile(File(root.filePath), false)
            mutableListOf<AbstractTreeNode<*>>(
                CQSFileNode(myProject, rootVFile!!,  iterate(root.nestedFiles))
            )
        }
        else
            mutableListOf<AbstractTreeNode<*>>()

        return nodes
    }

    override fun createComparator(): Comparator<AbstractTreeNode<*>> {
        val comparator = super.createComparator()
        return Comparator { node1, node2 ->
            val sortKey1 = getSortKey(node1)
            val sortKey2 = getSortKey(node2)

            if (sortKey1 != sortKey2) {
                return@Comparator sortKey1.compareTo(sortKey2)
            }

            comparator.compare(node1, node2)
        }
    }

    private fun getSortKey(node: AbstractTreeNode<*>): Int {
        // Nodes of the same type should be sorted as the same. Different types should be in this order (although some
        // are in different levels of the hierarchy)
        return when (node) {
            is CQSFileNode -> 1
            else -> 10000
        }
    }
}