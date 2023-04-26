fun main() {

    var input : String = ""
    var buffer = readLine()
    while (buffer != null) {
        input += buffer
        buffer = readLine()
    }

    var mapOfFrequencyChar: MutableMap<Int,FrequencyChar> = LinkedHashMap()
    //<Int,FrequencyChar>(Pair(input[0].code, FrequencyChar(input[0])))

    var currentChar : Char
    var currentIndexOfList : Int
    var currentElementOfList : FrequencyChar?
    var maxFrequency : Int = 0
    val maxIndex = input.length - 1
    for (index in 0..maxIndex) {
        currentChar = input[index]
        if (!currentChar.isWhitespace()) {
            currentIndexOfList = currentChar.code
            currentElementOfList = mapOfFrequencyChar[currentIndexOfList]
            if (currentElementOfList == null) {
                mapOfFrequencyChar[currentIndexOfList] = FrequencyChar(currentChar)
                currentElementOfList = mapOfFrequencyChar[currentIndexOfList]
            } else currentElementOfList.addFrequency()

            if (maxFrequency < currentElementOfList!!.getFrequency()) maxFrequency = currentElementOfList.getFrequency()
        }
    }

    mapOfFrequencyChar = mapOfFrequencyChar.toSortedMap()

    var outputLine : String
    for (countLines in 1..maxFrequency) {
        outputLine = ""
        mapOfFrequencyChar.forEach {
            if (it.value.getFrequency() > maxFrequency - countLines) outputLine += "#"
            else outputLine += " "
        }
        println(outputLine)
    }

    outputLine = ""
    mapOfFrequencyChar.forEach { outputLine += it.value.char}
    println(outputLine)
}


class FrequencyChar(val char:Char) {
    private var frequency : Int = 1

    fun addFrequency() {
        frequency += 1
    }

    fun getFrequency(): Int {
        return frequency
    }
}