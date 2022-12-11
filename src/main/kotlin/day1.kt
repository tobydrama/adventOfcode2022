import java.io.File
import java.util.Scanner

fun main(){
    val fileObj = Scanner( File("day1.txt"))
    var caloriList = mutableListOf<Int>()
    var numcalori = 0
    while (fileObj.hasNextLine()){
        val next = fileObj.nextLine()
        if (next==""){
            caloriList.add(numcalori)
            numcalori = 0
        }else {
            numcalori += next.toInt()
        }
    }
    caloriList = caloriList.sortedDescending() as MutableList<Int>
    println("Elf with the most calories has: "+ caloriList[0])
    println("total top 3 combiened is: "+(caloriList[0] + caloriList[1] + caloriList[2]))
}