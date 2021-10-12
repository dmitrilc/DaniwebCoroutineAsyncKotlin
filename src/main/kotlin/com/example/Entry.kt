package com.example

import kotlinx.coroutines.*

fun main() = runBlocking {
    //asyncVerbose()
    asyncNoWait()
}

suspend fun asyncVerbose() = coroutineScope { //1
    val deferred: Deferred<String> = async { //2
        println("waiting") //3
        delay(2000L) //4
        "result" //5
    }

    val result: String = deferred.await() //6

    println(result) //7
}

@OptIn(ExperimentalCoroutinesApi::class) //8
suspend fun asyncNoWait() = coroutineScope { //9
    val deferred: Deferred<String> = async {
        println("waiting")
        delay(5000L)
        "result"
    }

    delay(2000L) //10
    print("waited for too long, timing out...")

    val result: String = deferred.getCompleted() //11

    println(result)
}