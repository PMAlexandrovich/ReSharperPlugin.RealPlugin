package model.rider

import com.jetbrains.rider.model.nova.ide.SolutionModel
import com.jetbrains.rd.generator.nova.*
import com.jetbrains.rd.generator.nova.PredefinedType.*

@Suppress("unused")
object TestModel : Ext(SolutionModel.Solution) {

    private val CQSFile = structdef {
        field("filePath", string)
        field("nestedFiles", array(string))
    }

    init {
        property("myString", string)
        property("CQSFiles", array(CQSFile))
        callback("CQSFilesSelected", array(CQSFile), void)
    }
}