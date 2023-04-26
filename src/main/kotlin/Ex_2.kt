fun main() {
    val numberOfChanges: Int = readLine()!!.toInt()
    val inputString: String = readLine()!!

    val listOfCharPosition: MutableList<CharPosition> = mutableListOf()
    val mapOfPosition: MutableMap<Char, MutableList<Int>> = mutableMapOf()
    val mapOfDistance: MutableMap<Char, MutableList<Int>> = mutableMapOf()

    var currentChar: Char
    val maxIndex = inputString.length - 1
    for (index in 0..maxIndex) {
        listOfCharPosition.add(CharPosition(inputString, index))
    }

    listOfCharPosition.forEach {

        if (it.leftDistance != 0) {
            it.leftCharPosition = listOfCharPosition[it.index - it.leftDistance]
            if (it.leftDistance <= numberOfChanges) {
                it.leftPotential = it.repeatLength + it.leftDistance + it.leftCharPosition!!.repeatLength
            }
        }
        if (it.rightDistance != 0) {
            it.rightCharPosition = listOfCharPosition[it.index + it.rightDistance]
            if (it.rightDistance <= numberOfChanges) {
                it.rightPotential = it.repeatLength + it.rightDistance + it.rightCharPosition!!.repeatLength
            }

        }

        if (it.leftDistance + it.rightDistance <= numberOfChanges) it.isTogether = true
    }

    //listOfCharPosition.sortBy { it. }

    println(listOfCharPosition.toString())

}

class CharPosition(originString: String, val index: Int) {
    val char: Char = originString[index]
    var repeatLength: Int = 1
    var leftDistance: Int = 0
    var leftCharPosition: CharPosition? = null
    var rightDistance: Int = 0
    var rightCharPosition: CharPosition? = null
    var leftPotential = 0
    var rightPotential = 0
    var isTogether = false

    init {
        var isLeftOngoing = true
        for (l in 1..index) {
            if (originString[index - l] == char) {
                if (isLeftOngoing) repeatLength += 1
                else {
                    leftDistance = l
                    break
                }
            } else isLeftOngoing = false
        }

        var isRightOngoing = true
        for (r in 1..originString.length - index) {
            if (originString[index + r] == char) {
                if (isRightOngoing) repeatLength += 1
                else {
                    rightDistance = r
                    break
                }
            } else isRightOngoing = false
        }
    }
}