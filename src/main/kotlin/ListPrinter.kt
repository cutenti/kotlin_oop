package org.example

import org.example.list.CustomList

object ListPrinter {
    fun printList(list: CustomList) {
        val elements = mutableListOf<Int>()
        for (item in list) {
            elements.add(item)
        }
        println(elements.joinToString(prefix = "[", postfix = "]", separator = ", "))
    }
}