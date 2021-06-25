fun bill(priceList: Map<String, Int>, shoppingList: Array<String>): Int {
    var bill = 0
    shoppingList.forEach {
        bill += priceList[it] ?: 0
    }
    return bill
}