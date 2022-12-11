import java.io.File
import java.util.Scanner

fun main(){
    val fileObj = Scanner( File("day3.txt"))
    var score = 0
    while (fileObj.hasNextLine()){
        val next = fileObj.nextLine()
        val compartment1 = next.subSequence(0,next.length/2)
        val compartment2 = next.subSequence(next.length/2,next.length)

        var seen = mutableListOf<Char>()
        compartment1.forEach { if (compartment2.contains(it)&&(!seen.contains(it))){
            seen.add(it)
            score += getValue(it)} }
    }
    println("part one $score")

    val fileObjPart2 = Scanner( File("day3.txt"))
    var scorePart2 = 0
    while (fileObjPart2.hasNextLine()){
        val sack1 = fileObjPart2.nextLine()
        val sack2 = fileObjPart2.nextLine()
        val sack3 = fileObjPart2.nextLine()

        val seenPart2 = mutableListOf<Char>()
        sack1.forEach { if (sack2.contains(it) &&(sack3.contains(it)) &&(!seenPart2.contains(it)) ) {
                seenPart2.add(it)
                scorePart2 += getValue(it)
            }
        }
    }
    println("part two $scorePart2")

}

fun getValue(char: Char):Int{
    val alphabet = mutableListOf<Char>('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                       'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
    return alphabet.indexOf(char)+1
}