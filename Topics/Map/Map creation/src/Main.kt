fun stringToMap(first: String, second: String, third: String): Map<String, Int> {
    val array = listOf<String>(first, second, third)
    return array.associateWith { it.length }
}