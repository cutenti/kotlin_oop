package org.example

class SingleLinkedList : CustomList {

    private class Node(
        var value: Int,
        var next: Node? = null
    )

    private var head: Node? = null
    private var tail: Node? = null

    override var size: Int = 0

    override fun add(element: Int) {
        val newNode = Node(element)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    override operator fun set(index: Int, value: Int) {
        val node = getNode(index)
        node.value = value
    }

    override fun addFirst(element: Int) {
        val newNode = Node(element, head)
        head = newNode
        if (tail == null) {
            tail = newNode
        }
        size++
    }

    override operator fun get(index: Int): Int {
        return getNode(index).value
    }

    private fun getNode(index: Int): Node {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        var current = head
        for (i in 0 until index) {
            current = current?.next
        }
        return current!!
    }

    override fun indexOf(element: Int): Int {
        var current = head
        var index = 0
        while (current != null) {
            if (current.value == element) {
                return index
            }
            current = current.next
            index++
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        if (head == null) return false

        if (head?.value == element) {
            head = head?.next
            if (head == null) {
                tail = null
            }
            size--
            return true
        }

        var current = head
        while (current?.next != null) {
            if (current.next?.value == element) {
                if (current.next == tail) {
                    tail = current
                }
                current.next = current.next?.next
                size--
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = head

            override fun hasNext(): Boolean {
                return current != null
            }

            override fun next(): Int {
                val node = current ?: throw NoSuchElementException()
                current = node.next
                return node.value
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also { it.add(item) }
            }
    }
}