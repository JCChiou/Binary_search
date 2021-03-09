import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

fun main() {
    println("請輸入要產生多少長度的陣列")
    val a = readLine()!!.toInt()
    val newList = createRandomList(a)
    println(newList)
    val result = binarySearch(newList)
    println(result)
}

fun binarySearch(source: List<Int>): Int {
    var start = 0
    var end = source.size - 1
    val tar = source.random()
    var executeCount = 1
    println("Tar No. = $tar")
    while (end >= start) {
        val middle = (end + start) shr (1)
        print("現值 = ${source[middle]} , ")
        when {
            tar == source[middle] -> {
                return source[middle]
            }
            tar > source[middle] -> {
                println("執行次數 = $executeCount, $tar > 現值${source[middle]} , 修正目前起始值 = ${source[middle + 1]}")
                start = middle + 1
            }
            else -> {
                println("執行次數 = $executeCount, $tar < 現值${source[middle]} , 修正目前結束值 = ${source[middle - 1]}")
                end = middle - 1
            }

        }
        executeCount += 1
    }
    return -1
}

fun createRandomList(count: Int): List<Int> {
    val randomList = arrayListOf<Int>()
    var i = 0
    while (i <= count) {
        val randData = ThreadLocalRandom.current().nextInt(1, 1000)
        if (i !in randomList) {
            randomList.add(randData)
            i += 1
        }
    }
    randomList.sort()
    return randomList
}