package org.example.list

class CustomArrayList(startSize: Int) : CustomList {
    var inner = IntArray(startSize)
    
    override var size: Int = 0
        private set

    override fun get(index: Int): Int {
        if (index !in 0 until size) throw IndexOutOfBoundsException()
        return inner[index]
    }

    override fun set(index: Int, value: Int) {
        if (index !in 0 until size) throw IndexOutOfBoundsException()
        inner[index] = value
    }

    override fun add(element: Int) {
        if (size == inner.size) resize(if (inner.isEmpty()) 10 else inner.size * 2)
        inner[size++] = element
    }

    override fun addFirst(element: Int) {
        if (size == inner.size) resize(if (inner.isEmpty()) 10 else inner.size * 2)
        System.arraycopy(inner, 0, inner, 1, size)
        inner[0] = element
        size++
    }

    override fun remove(element: Int): Boolean {
        val index = indexOf(element)
        if (index == -1) return false
        removeAt(index)
        return true
    }

    fun removeAt(index: Int): Int {
        if (index !in 0 until size) throw IndexOutOfBoundsException()
        val removed = inner[index]
        System.arraycopy(inner, index + 1, inner, index, size - index - 1)
        size--
        return removed
    }

    override fun indexOf(element: Int): Int {
        for (i in 0 until size) {
            if (inner[i] == element) return i
        }
        return -1
    }

    private fun resize(newSize: Int) {
        val newArray = IntArray(newSize)
        System.arraycopy(inner, 0, newArray, 0, size)
        inner = newArray
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var currentIndex = 0
            override fun hasNext(): Boolean = currentIndex < size
            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                return inner[currentIndex++]
            }
        }
    }

    companion object {
        fun customArrayListOf(vararg items: Int) =
            items.fold(CustomArrayList(items.size)) { list, item ->
                list.also { it.add(item) }
            }
    }
}