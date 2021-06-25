fun summator(map: Map<Int, Int>): Int {
    var sum = 0
    map.forEach {
        if (it.key % 2 == 0) {
            sum += it.value
        }
    }
    return sum
}