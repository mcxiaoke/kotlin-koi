package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.ext.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 20:53
 */

class CollectionExtensionSample {

    fun collectionToString1() {
        // collection to string extension
        val pets = listOf<String>("Cat", "Dog", "Rabbit", "Fish")
        val string1 = pets.asString(delim = " ") // "Cat Dog Rabbit Fish"
        val string2 = pets.asString() // "Cat,Dog,Rabbit,Fish"
        val numbers = arrayOf(2016, 2, 2, 20, 57, 40)
        val string3 = numbers.asString() // "2016,2,2,20,57,40"
        val string4 = numbers.asString(delim = "-") // 2016-2-2-20-57-40
    }

    fun collectionToString2() {
        // collection to string extension
        val map = mapOf<String, Int>(
                "John" to 30,
                "Smith" to 50,
                "Alice" to 22
        )
        val string1 = map.asString() // "John=30,Smith=50,Alice=22"
        val string2 = map.asString(delim = "/") // "John=30/Smith=50/Alice=22"
    }

    fun collectionElement() {
        val numbers = (1..8).toArrayList()
        println(numbers.joinToString()) // "1, 2, 3, 4, 5, 6, 7"
        numbers.head() // .dropLast(1)
        numbers.tail() //.drop(1)
        val numbers2 = 100.appendTo(numbers) //
        val numbers3 = 2016.prependTo(numbers)

    }
}
