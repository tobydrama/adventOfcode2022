import java.io.File
import java.util.Scanner

fun main(){
    val fileObj = Scanner( File("day2.txt"))
    var score = 0
    var scorepartTwo = 0
    while (fileObj.hasNextLine()) {
        val next = fileObj.nextLine()
        val opponent = next[0]
        val you = next[2]
        if (isDraw(opponent,you)){
            score += valueOfHand(you) + 3
        }
        else if (isWin(opponent,you)){
            score += valueOfHand(you) +6
        }
        else {
            score += valueOfHand(you)
        }
        if (you=='X'){
            scorepartTwo += 0 + valueOfHand(getLose(opponent))
        }
        if (you=='Y'){
            scorepartTwo += 3 + valueOfHand(getDraw(opponent))
        }
        if (you=='Z'){
            scorepartTwo += 6 + valueOfHand(getWin(opponent))
        }
    }
    println("Total score for part one: "+score)
    println("Total score for part two: "+scorepartTwo)

}

fun getWin(opponent: Char):Char{
    if (opponent=='A'){
        return 'Y'
    }
    else if (opponent=='B'){
        return 'Z'
    }
    return 'X'
}

fun getDraw(opponent: Char):Char{
    if (opponent=='A'){
        return 'X'
    }
    else if (opponent=='B'){
        return 'Y'
    }
    return 'Z'
}

fun getLose(opponent: Char):Char{
    if (opponent=='A'){
        return 'Z'
    }
    else if (opponent=='B'){
        return 'X'
    }
    return 'Y'
}


fun isWin(opponent: Char, you: Char):Boolean{
    val rockWin = (opponent=='C' && you=='X')
    val paperWin = (opponent=='A' && you=='Y')
    val scissorWin = (opponent=='B' && you=='Z')
    return (rockWin||paperWin||scissorWin)
}

fun isDraw(opponent: Char, you: Char):Boolean{
    val rockDraw = (opponent=='A' && you=='X')
    val paperDraw = (opponent=='B' && you=='Y')
    val scissorDraw = (opponent=='C' && you=='Z')
    return (rockDraw||paperDraw||scissorDraw)
}

fun valueOfHand(you: Char):Int{
    if (you=='X'){
        return 1
    }
    else if (you=='Y'){
        return 2
    }
    return 3
}