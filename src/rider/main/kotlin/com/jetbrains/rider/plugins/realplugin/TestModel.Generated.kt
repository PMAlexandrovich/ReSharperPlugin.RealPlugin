@file:Suppress("EXPERIMENTAL_API_USAGE","EXPERIMENTAL_UNSIGNED_LITERALS","PackageDirectoryMismatch","UnusedImport","unused","LocalVariableName","CanBeVal","PropertyName","EnumEntryName","ClassName","ObjectPropertyName","UnnecessaryVariable","SpellCheckingInspection")
package com.jetbrains.rd.ide.model

import com.jetbrains.rd.framework.*
import com.jetbrains.rd.framework.base.*
import com.jetbrains.rd.framework.impl.*

import com.jetbrains.rd.util.lifetime.*
import com.jetbrains.rd.util.reactive.*
import com.jetbrains.rd.util.string.*
import com.jetbrains.rd.util.*
import kotlin.reflect.KClass



/**
 * #### Generated from [TestModel.kt:8]
 */
class TestModel private constructor(
    private val _myString: RdOptionalProperty<String>,
    private val _cQSFiles: RdOptionalProperty<Array<CQSFile>>,
    private val _cQSFilesSelected: RdCall<Array<CQSFile>, Unit>
) : RdExtBase() {
    //companion
    
    companion object : ISerializersOwner {
        
        override fun registerSerializersCore(serializers: ISerializers)  {
            serializers.register(CQSFile)
        }
        
        
        
        private val __CQSFileArraySerializer = CQSFile.array()
        
        const val serializationHash = -460322887783238512L
        
    }
    override val serializersOwner: ISerializersOwner get() = TestModel
    override val serializationHash: Long get() = TestModel.serializationHash
    
    //fields
    val myString: IOptProperty<String> get() = _myString
    val cQSFiles: IOptProperty<Array<CQSFile>> get() = _cQSFiles
    val cQSFilesSelected: IRdEndpoint<Array<CQSFile>, Unit> get() = _cQSFilesSelected
    //methods
    //initializer
    init {
        _myString.optimizeNested = true
        _cQSFiles.optimizeNested = true
    }
    
    init {
        bindableChildren.add("myString" to _myString)
        bindableChildren.add("cQSFiles" to _cQSFiles)
        bindableChildren.add("cQSFilesSelected" to _cQSFilesSelected)
    }
    
    //secondary constructor
    internal constructor(
    ) : this(
        RdOptionalProperty<String>(FrameworkMarshallers.String),
        RdOptionalProperty<Array<CQSFile>>(__CQSFileArraySerializer),
        RdCall<Array<CQSFile>, Unit>(__CQSFileArraySerializer, FrameworkMarshallers.Void)
    )
    
    //equals trait
    //hash code trait
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("TestModel (")
        printer.indent {
            print("myString = "); _myString.print(printer); println()
            print("cQSFiles = "); _cQSFiles.print(printer); println()
            print("cQSFilesSelected = "); _cQSFilesSelected.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    override fun deepClone(): TestModel   {
        return TestModel(
            _myString.deepClonePolymorphic(),
            _cQSFiles.deepClonePolymorphic(),
            _cQSFilesSelected.deepClonePolymorphic()
        )
    }
    //contexts
}
val Solution.testModel get() = getOrCreateExtension("testModel", ::TestModel)



/**
 * #### Generated from [TestModel.kt:10]
 */
data class CQSFile (
    val filePath: String,
    val nestedFiles: Array<String>
) : IPrintable {
    //companion
    
    companion object : IMarshaller<CQSFile> {
        override val _type: KClass<CQSFile> = CQSFile::class
        
        @Suppress("UNCHECKED_CAST")
        override fun read(ctx: SerializationCtx, buffer: AbstractBuffer): CQSFile  {
            val filePath = buffer.readString()
            val nestedFiles = buffer.readArray {buffer.readString()}
            return CQSFile(filePath, nestedFiles)
        }
        
        override fun write(ctx: SerializationCtx, buffer: AbstractBuffer, value: CQSFile)  {
            buffer.writeString(value.filePath)
            buffer.writeArray(value.nestedFiles) { buffer.writeString(it) }
        }
        
        
    }
    //fields
    //methods
    //initializer
    //secondary constructor
    //equals trait
    override fun equals(other: Any?): Boolean  {
        if (this === other) return true
        if (other == null || other::class != this::class) return false
        
        other as CQSFile
        
        if (filePath != other.filePath) return false
        if (!(nestedFiles contentDeepEquals other.nestedFiles)) return false
        
        return true
    }
    //hash code trait
    override fun hashCode(): Int  {
        var __r = 0
        __r = __r*31 + filePath.hashCode()
        __r = __r*31 + nestedFiles.contentDeepHashCode()
        return __r
    }
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("CQSFile (")
        printer.indent {
            print("filePath = "); filePath.print(printer); println()
            print("nestedFiles = "); nestedFiles.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    //contexts
}
