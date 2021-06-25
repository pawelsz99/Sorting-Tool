package sorting


import java.io.File
import java.util.*
import kotlin.math.roundToInt

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)

    var dataType = ""
    var sortingType = "natural"
    var inputFile = ""
    var outputFile = ""

    for (i in args.indices) {
        when (args[i]) {
            "-dataType" -> try {
                dataType = args[i + 1]
            } catch (e: ArrayIndexOutOfBoundsException) {
                println("No data type defined!")
                return
            }
            "-sortingType" -> try {
                sortingType = args[i + 1]
            } catch (e: ArrayIndexOutOfBoundsException) {
                println("No sorting type defined!")
                return
            }
            "-inputFile" -> try {
                inputFile = args[i + 1]
            } catch (e: ArrayIndexOutOfBoundsException) {
                println("No input file defined!")
                return
            }
            "-outputFile" -> try {
                outputFile = args[i + 1]
            } catch (e: ArrayIndexOutOfBoundsException) {
                println("No output file defined!")
                return
            }
        }
    }


    when (dataType) {
        "long" -> long(scanner, sortingType, inputFile, outputFile)
        "line" -> line(scanner, sortingType, inputFile, outputFile)
        "word" -> word(scanner, sortingType, inputFile, outputFile)
        else -> long(scanner, sortingType, inputFile, outputFile)
    }

}


fun word(scanner: Scanner, sortingType: String, inputFile: String, outputFile: String) {

    val listOfWords = mutableListOf<String>()
    var total = 0
    while (scanner.hasNext()) {
        total++
        listOfWords.add(scanner.next())
    }
    println("Total numbers: $total.")

    when (sortingType) {
        "natural" -> {

            listOfWords.sort()
            var info = ""
            listOfWords.forEach { info += "$it " }

            println("Sorted data: $info")
        }
        "byCount" -> {
            listOfWords.sort()
            var mapWords = mutableMapOf<String, Int>() // word to count
            listOfWords.forEach {
                if (mapWords.containsKey(it)) {
                    mapWords[it] = mapWords[it]!! + 1
                } else {
                    mapWords[it] = 1
                }
            }
            mapWords = mapWords.toList().sortedBy { (_, value) -> value }.toMap() as MutableMap<String, Int>
            mapWords.forEach { (key, value) ->
                println("$key: $value time(s), ${percentage(value, total)}%")
            }
        }
        else -> {
            println("Incorrect sortingType! ")
        }
    }
}


fun line(scanner: Scanner, sortingType: String, inputFile: String, outputFile: String) {

    val listOfWords = mutableListOf<String>()
    var total = 0
    while (scanner.hasNext()) {
        total++
        listOfWords.add(scanner.nextLine())
    }
    println("Total numbers: $total.")

    when (sortingType) {
        "natural" -> {

            listOfWords.sort()
            var info = ""
            listOfWords.forEach { info += "$it " }

            println("Sorted data: $info")
        }
        "byCount" -> {
            listOfWords.sort()
            var mapWords = mutableMapOf<String, Int>() // word to count
            listOfWords.forEach {
                if (mapWords.containsKey(it)) {
                    mapWords[it] = mapWords[it]!! + 1
                } else {
                    mapWords[it] = 1
                }
            }
            mapWords = mapWords.toList().sortedBy { (_, value) -> value }.toMap() as MutableMap<String, Int>
            mapWords.forEach { (key, value) ->
                println("$key: $value time(s), ${percentage(value, total)}%")
            }
        }
        else -> {
            println("Incorrect sortingType! ")
        }
    }
}

private fun long(scanner: Scanner, sortingType: String, inputFile: String, outputFile: String) {

    val listLong = mutableListOf<Long>()
    var total = 0

    if (inputFile == "") {
        while (scanner.hasNext()) {
            total++
            listLong.add(scanner.nextLong())
        }
    } else {
        val fileScanner = Scanner(File(inputFile))
        while (fileScanner.hasNext()) {
            total++
            listLong.add(fileScanner.nextLong())
        }
        fileScanner.close()
    }


    var info = "Total numbers: $total.\n"

    when (sortingType) {
        "natural" -> {
            listLong.sort()
            info += "Sorted data: "
            listLong.forEach { info += "$it " }
        }
        "byCount" -> {
            listLong.sort()
            var mapLong = mutableMapOf<Long, Int>() // number to count
            listLong.forEach {
                if (mapLong.containsKey(it)) {
                    mapLong[it] = mapLong[it]!! + 1
                } else {
                    mapLong[it] = 1
                }
            }
            mapLong = mapLong.toList().sortedBy { (_, value) -> value }.toMap() as MutableMap<Long, Int>
            mapLong.forEach { (key, value) ->
                info += "$key: $value time(s), ${percentage(value, total)}%\n"
            }
        }
        else -> {
            println("Incorrect sortingType! ")
        }
    }

    if (outputFile == "") {
        println(info)
    } else {
        val output = File(outputFile)
        output.writeText(info)
    }


}

private fun percentage(count: Int, total: Int): Int {
    return ((count.toDouble() / total.toDouble()) * 100).roundToInt()
}


