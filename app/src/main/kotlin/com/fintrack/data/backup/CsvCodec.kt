package com.fintrack.data.backup

/**
 * Tiny CSV codec — handles quoted fields containing commas, quotes, and
 * newlines per RFC 4180. No external dependency.
 */
internal object CsvCodec {

    fun encodeRow(fields: List<String?>): String =
        fields.joinToString(",") { escape(it.orEmpty()) }

    private fun escape(s: String): String {
        val needsQuoting = s.contains(',') || s.contains('"') || s.contains('\n') || s.contains('\r')
        return if (needsQuoting) "\"${s.replace("\"", "\"\"")}\"" else s
    }

    /**
     * Parses a full CSV body and returns rows. Strict: throws on an
     * unterminated quoted field.
     */
    fun parse(text: String): List<List<String>> {
        val rows = mutableListOf<List<String>>()
        val current = StringBuilder()
        val row = mutableListOf<String>()
        var i = 0
        var inQuotes = false
        while (i < text.length) {
            val c = text[i]
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < text.length && text[i + 1] == '"') {
                        current.append('"'); i += 2; continue
                    }
                    inQuotes = false; i++; continue
                }
                current.append(c); i++
            } else {
                when (c) {
                    '"' -> { inQuotes = true; i++ }
                    ',' -> { row += current.toString(); current.clear(); i++ }
                    '\r' -> { i++ /* swallow */ }
                    '\n' -> {
                        row += current.toString(); current.clear()
                        rows += row.toList(); row.clear(); i++
                    }
                    else -> { current.append(c); i++ }
                }
            }
        }
        if (inQuotes) error("Unterminated quoted field in CSV")
        // Trailing field/row.
        if (current.isNotEmpty() || row.isNotEmpty()) {
            row += current.toString(); rows += row.toList()
        }
        return rows
    }
}
