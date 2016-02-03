package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.ext.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 20:53
 */

class CollectionExtensionSample {

    fun collectionToString() {
        val pets = listOf<String>("Cat", "Dog", "Rabbit", "Fish")
        // list to string, delimiter is space
        val string1 = pets.asString(delim = " ") // "Cat Dog Rabbit Fish"
        // default delimiter is comma
        val string2 = pets.asString() // "Cat,Dog,Rabbit,Fish"


        val numbers = arrayOf(2016, 2, 2, 20, 57, 40)
        // array to string, default delimiter is comma
        val string3 = numbers.asString() // "2016,2,2,20,57,40"
        // array to string, delimiter is -
        val string4 = numbers.asString(delim = "-") // 2016-2-2-20-57-40

        // using Kotlin stdlib
        val s1 = pets.joinToString()
        val s2 = numbers.joinToString(separator = "-", prefix = "<", postfix = ">")
    }

    fun mapToString() {
        val map = mapOf<String, Int>(
                "John" to 30,
                "Smith" to 50,
                "Alice" to 22
        )
        // default delimiter is ,
        val string1 = map.asString() // "John=30,Smith=50,Alice=22"
        // using delimiter /
        val string2 = map.asString(delim = "/") // "John=30/Smith=50/Alice=22"


        // using stdlib
        map.asSequence().joinToString { "${it.key}=${it.value}" }

    }

    fun appendAndPrepend() {
        val numbers = (1..6).toArrayList()
        println(numbers.joinToString()) // "1, 2, 3, 4, 5, 6, 7"
        numbers.head() // .dropLast(1)
        numbers.tail() //.drop(1)
        val numbers2 = 100.appendTo(numbers) //
        val numbers3 = 2016.prependTo(numbers)

    }
}
