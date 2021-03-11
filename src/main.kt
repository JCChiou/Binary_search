import java.util.concurrent.ThreadLocalRandom

fun main() {

    while (true) {
        println("請輸入要產生多少長度的陣列")
        val asourceLeng = readLine()!!.toInt()
        val newList = createRandomList(asourceLeng)
        println("請輸入模式： 1 = 透過內部函數隨機產生目標值， 2 = 透過外部自行輸入， 3 = 離開")
        val myMode = readLine()?.trim()?.toInt()

        try {
            when (myMode) {
                1 -> {
                    println("mode 1")
                    binarySearch(newList)
                }
                2 -> {
                    println("請輸入目標值")
                    val extTar = readLine()!!.toInt()
                    binarySearch(newList, extTar)
                }
                3 -> {
                    break
                }
                else -> {
                    println("輸入超出預期")
                }
            }
        } catch (e: Exception) {
            println("${e.message} ,請輸入整數")
        }
    }
}

fun binarySearch(source: List<Int>, tar: Int = source.random()): Int {
    var start = 0
    var end = source.size - 1
    val dispList = mutableListOf<String>()
    for (i in source.indices) {
        dispList.add(source[i].toString())
    }
    println("sourceList = $source")
    var executeCount = 1
    println("Tar No. = $tar")
    while (end >= start) {
        val middle = (end + start) shr (1)
//        print("現值 = ${source[middle]} , ")

        dispList[middle] = "->${source[middle]}<-"
        dispList[start] = "(${source[start]})"
        dispList[end] = "(${source[end]})"
        println("執行次數 = ${executeCount}, $dispList")
        dispList[middle] = source[middle].toString()
        dispList[start] = source[start].toString()
        dispList[end] = source[end].toString()

        when {
            tar == source[middle] -> {
                println("找到目標值 = ${source[middle]}")
                return source[middle]
            }
            tar > source[middle] -> {
                start = middle + 1
                if (start >= end) {
                    if (tar > source[end]) {
                        println("目標大於，找不到目標，可能存在${source[end]}之後與${source[end + 1]}")
                    } else {
                        println("目標大於，找不到目標，可能存在${source[start - 1]}~~${source[end]}")
                    }
                    return -1
                }
            }
            else -> {
                end = middle - 1
                if (end <= start) {
                    if (tar < source[start]) {
                        println("找不到目標，可能存在${source[start]}之前與${source[start - 1]}")
                    } else {
                        println("找不到目標，可能存在${source[start]}與${source[end + 1]}之間")
                    }
                    return -1
                }
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
        val randData = ThreadLocalRandom.current().nextInt(1, 10000)
        if (i !in randomList) {
            randomList.add(randData)
            i += 1
        }
    }
    randomList.sort()
    return randomList
}