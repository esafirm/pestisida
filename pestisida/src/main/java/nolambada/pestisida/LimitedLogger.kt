package nolambada.pestisida

internal class LimitedLogger(private val capacity: Int) {

    private val logHolder = mutableListOf<String>()
    private var pos = 0

    fun log(log: String) {
        if (logHolder.getOrNull(pos) == null) {
            logHolder.add(log)
        } else {
            logHolder[pos] = log
        }

        pos += 1

        if (pos >= capacity) {
            pos = 0
        }
    }

    fun asList(): List<String> {
        val lastPost = pos - 1
        val isOnLastIndex = lastPost == logHolder.size - 1

        if (isOnLastIndex) {
            return logHolder.toList()
        }

        // fetch next position as first
        val backSize = logHolder.size - (lastPost + 1)
        val frontSize = logHolder.size - backSize
        return logHolder.takeLast(backSize) + logHolder.take(frontSize)
    }

    fun asString(separator: String = "\n"): String {
        return asList().joinToString(separator)
    }
}