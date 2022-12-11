import java.io.File

fun main(){
    partOne( File("day5.txt", ).readLines(),prettyPrint = true)
    partTwo( File("day5.txt").readLines(),prettyPrint = false)

}

fun partTwo(input:List<String>, prettyPrint:Boolean = false){
    val topList = getListes(input)
    if (prettyPrint){prittyPrint(topList)}
    val listWithoutTop = (input.dropWhile{ it != "" }).drop(1)
    listWithoutTop.forEach {
        val temp = getMoveInstructions(it)
        val tempList = mutableListOf<Char>()
        for (a in 1 .. (temp[0])){
            tempList.add( topList[temp[1]-1].removeFirst())
        }
        tempList.reverse()
        tempList.forEach {topList[temp[2]-1].add(0,it)  }

        if (prettyPrint){prittyPrint(topList)}
    }
    println("Part2")
    topList.forEach{print(it[0])}
    println()
}

fun partOne(input:List<String>, prettyPrint:Boolean = false){
    val topList = getListes(input)
    if (prettyPrint){prittyPrint(topList)}
    val listWithoutTop = (input.dropWhile{ it != "" }).drop(1)
    listWithoutTop.forEach {
        val temp = getMoveInstructions(it)
        for (a in 1 .. (temp[0])){
            topList[temp[2]-1].add(0,topList[temp[1]-1].removeFirst())
        }
        if (prettyPrint){prittyPrint(topList)}
    }
    println("partOne")
    topList.forEach{print(it[0])}
    println()
}

fun getListes(input: List<String>):MutableList<MutableList<Char>>{
    val toplvlstuff = mutableListOf<MutableList<Char>>()
    val numOfLists = ((input.find { !it.contains('[') })!!).takeLastWhile { it != ' ' }.toInt()
    for (a in 1 ..numOfLists){
        toplvlstuff.add(mutableListOf())
    }
    run breaking@{
        input.forEach {
            if(!it.contains('[')) return@breaking
            for (a in 1..it.length step 4){
                if (it.get(a)!=' '){
                    toplvlstuff[a.div(4)].add(it.get(a))
                }
            }
        }
    }
    return toplvlstuff
}

fun getMoveInstructions(input :String):List<Int>{
    val returnList = mutableListOf<Int>()
    input.split(' ').filter { it != "move" }.filter { it != "from" }.filter { it != "to" }.forEach { returnList.add(it.toInt()) }
    return returnList
}

fun prittyPrint(input:MutableList<MutableList<Char>>){
    var biggestList = 0
    var current = 0
    input.forEach { if(it.size>biggestList) {biggestList = it.size; current= it.size} }
    for (a in 0 until biggestList){
        for (b in 0 until input.size){
            if(input[b].size<current){
                print("    ")
            }else{
                print("["+ input[b][input[b].size-current] +"] ")
            }
        }
        current--
        println()
    }
    println(" 1   2   3   4   5   6   7   8   9")

}