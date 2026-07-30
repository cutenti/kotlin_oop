package org.example.stack

import org.example.list.CustomArrayList
import org.example.list.CustomList

class ArrayListStack(
    private val innerList: CustomArrayList = CustomArrayList(10)
) : Stack, CustomList by innerList {

    override fun push(value: Int) {
        innerList.add(value)
    }

    override fun pop(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return innerList.removeAt(innerList.size - 1)
    }

    override fun peek(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return innerList.get(innerList.size - 1)
    }

    override val isEmpty: Boolean
        get() = innerList.size == 0
}