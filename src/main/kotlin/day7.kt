import java.io.File

fun main(){
    partOne(File("day7.txt").readLines())
}

fun partOne(input:List<String>){
    var startDirectory = Directory("/", mutableListOf(), mutableListOf())
    var current = startDirectory
    for (a in input){
        when{
            a.contains("$ cd ..") -> current = current.parent!!
            a.contains("$ ls") -> continue
            a.contains("$ cd /") -> current = startDirectory
            a.contains("$ cd")-> current = findDric(current, a.takeLastWhile { it!=' ' })
            else -> {lsCommand(a, current)}
        }
    }
    upadteTotalSize(startDirectory)
    //println(startDirectory)
    var sumTotal = 0
    findDircLessThenNum(startDirectory,100000, mutableListOf()).forEach {
        sumTotal+=it.totalSize
        //println("name "+it.name+", size "+it.totalSize)
        }
    println("total from part one: "+sumTotal)
    val totalSpaceLeft = 70000000 - startDirectory.totalSize
    val spaceNeeded = 30000000-totalSpaceLeft
    var biggestDicLessThenNum: Directory? = null
    findDircGreaterThenNum(startDirectory,spaceNeeded, mutableListOf()).forEach {
        if(biggestDicLessThenNum==null){
            biggestDicLessThenNum = it
        }else if (biggestDicLessThenNum!!.totalSize>it.totalSize){
            biggestDicLessThenNum = it
        }
    }
    println("total from part two: "+ (biggestDicLessThenNum?.totalSize ?: 0))
    println(startDirectory)
}

fun findDircGreaterThenNum(dirc: Directory, num:Int, list: MutableList<Directory>):MutableList<Directory>{
    dirc.directorys.forEach { findDircGreaterThenNum(it, num, list)}
    if(dirc.totalSize>=num){
        list.add(dirc)
    }
    return list
}

fun findDircLessThenNum(dirc: Directory, num:Int, list: MutableList<Directory>):MutableList<Directory>{
    dirc.directorys.forEach { findDircLessThenNum(it, num, list)}
    if(dirc.totalSize<=num){
        list.add(dirc)
    }
    return list
}

fun upadteTotalSize(dirc: Directory){
    var dirctorySize = getFileSizes(dirc)
    dirc.directorys.forEach {
        upadteTotalSize(it)
        dirctorySize+=it.totalSize
    }
    dirc.totalSize = dirctorySize
}

fun getFileSizes(dirc: Directory):Int{
    var fileSizes = 0
    dirc.files.forEach { fileSizes+=it.size}
    return fileSizes
}

fun lsCommand(input:String,dirc:Directory){
    if(input.contains("dir")){
        val name = input.takeLastWhile { it!=' ' }
        if (!hasDirc(dirc,name)){
            addDirc(dirc=dirc,name=name)
        }
    }else {
        val name = input.takeLastWhile { it!=' ' }
        val size = input.takeWhile { it!=' ' }.toInt()
        if (!hasFile(dirc,name)){
            addFile(dirc=dirc,name=name,size=size)
        }
    }
}



fun getIndentString(indent:String = "", directory:Directory): String{
    var a = "\n"
    directory.directorys.forEach {
        it.parent=directory
        a+=getIndentString(indent=("$indent             "), directory=it)+"\n"}
    return """    
$indent parent directory: ${directory.parent?.name}        
$indent directory name: ${directory.name}
$indent directory total size: ${directory.totalSize}
$indent files: ${getIndentedfile(indent+"        ",directory.files)}
$indent directorys: $a"""
}


fun getIndentedfile(indent:String = "",files: MutableList<TextFile>):String{
    var a = "\n"
    files.forEach { a+=indent+it+"\n"}
    return a
}

fun findDric(dirc:Directory, fileName:String):Directory{
    dirc.directorys.forEach { if(it.name==fileName){
        return it
    } }
    return dirc
}

fun hasFile(dirc:Directory, fileName:String):Boolean{
    dirc.files.forEach { if(it.name==fileName){return true} }
    return false
}

fun hasDirc(dirc:Directory, dircName:String):Boolean{
    dirc.directorys.forEach { if(it.name==dircName){return true} }
    return false
}
fun addDirc(dirc: Directory, name: String) {
    dirc.directorys.add(Directory(name, mutableListOf(), mutableListOf(),dirc))
}

fun addFile(dirc: Directory, name: String, size:Int) {
    dirc.totalSize += size
    dirc.files.add(TextFile(size = size, name = name))
}

class Directory (val name: String, val files: MutableList<TextFile>,
                  val directorys: MutableList<Directory>,
                  var parent:Directory?=null,
                  var totalSize:Int=0){
    @Override
    override fun toString(): String {
        return getIndentString(directory=this)
    }
}

class TextFile(val size: Int, val name: String){
    @Override
    override fun toString(): String {
        return """File name: $name size: $size"""
    }
}