import java.io.File

fun main(){
    partOne(File("day6.txt").readLines()[0])
    partTwo(File("day6.txt").readLines()[0])
}

fun partOne(input:String){
    var temp = ""
    var index = 0
    for(a in input.indices){
        if(!temp.contains(input[a])){
            temp += input[a]
            index++
        }else{
            temp = ""+input[a]
            index++
        }
        if (temp.length==4){
            println("par one index: $index with ket value: $temp")
            break
        }
    }
}

fun partTwo(word:String){
    var input = word
    var temp = ""
    var index = 0
    val length = word.indices
    for(a in length){
        if(!temp.contains(input[0])){
            temp += input[0]
            input = input.drop(1)
            index++
        }else{
            temp = ""+temp.takeLastWhile{ it!=input[0] }+input[0]
            input = input.drop(1)
            index++
        }
        if (temp.length==14){
            println("par one index: $index with ket value: $temp")
            break
        }
    }
}