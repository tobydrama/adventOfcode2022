import java.io.File
import java.util.Scanner

fun main(){
    part1(Scanner( File("day4.txt")))
    part2(Scanner( File("day4.txt")))
}

fun part1(fileObj: Scanner){
    var score = 0
    while (fileObj.hasNextLine()){
        val list = fileObj.nextLine().split(',')
        val l1 = list[0].split('-')
        val l2 = list[1].split('-')
        val c1 = (l1[0].toInt() .. l1[1].toInt()).count()
        val c2 = (l2[0].toInt() .. l2[1].toInt()).count()
        val p1 :List<String> = if(c2>c1 || l2[1].toInt()>l1[1].toInt()) {l1} else {l2}
        val p2 :List<String> = if(c2>c1 || l2[1].toInt()>l1[1].toInt()) {l2} else {l1}
        var containsAll = true
        for (it in (p1[0].toInt() .. p1[1].toInt())) {
            if (!(p2[0].toInt() .. p2[1].toInt()).contains(it)){
                containsAll = false
                break;
            }
        }
        if (containsAll){score++}
    }
    println("there are a total $score fully overlapping sections")
}

fun part2(fileObj: Scanner){
    var score = 0
    while (fileObj.hasNextLine()){
        val list = fileObj.nextLine().split(',')
        val l1 = list[0].split('-')
        val l2 = list[1].split('-')
        val c1 = (l1[0].toInt() .. l1[1].toInt()).count()
        val c2 = (l2[0].toInt() .. l2[1].toInt()).count()
        val p1 :List<String> = if(c2>c1 || l2[1].toInt()>l1[1].toInt()) {l1} else {l2}
        val p2 :List<String> = if(c2>c1 || l2[1].toInt()>l1[1].toInt()) {l2} else {l1}
        for (it in (p1[0].toInt() .. p1[1].toInt())) {
            if ((p2[0].toInt() .. p2[1].toInt()).contains(it)){
                score++
                break;
            }
        }
    }
    println("there are a total $score any overlapping sections")
}
