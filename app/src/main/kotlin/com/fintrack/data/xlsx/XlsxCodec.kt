package com.fintrack.data.xlsx

import kotlinx.datetime.LocalDate
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream
import java.io.OutputStream
import java.math.BigDecimal

/**
 * In-memory shape of an XLSX template, used by the codec and the import
 * preview screen. Rows are pre-validated by [XlsxCodec.read] but not
 * cross-referenced against the user's catalog yet — that happens at
 * persist time so the user can still see "Unknown holding XYZ" warnings
 * on the preview screen.
 */
data class XlsxSnapshotRow(
    val date: LocalDate,
    val earningsInCr: BigDecimal,
    val notes: String?,
)

data class XlsxHoldingValueRow(
    val snapshotDate: LocalDate,
    val holdingName: String,
    val invested: BigDecimal?,
    val current: BigDecimal,
    val sip: BigDecimal?,
)

data class XlsxLoanRow(
    val name: String,
    val originalAmount: BigDecimal,
    val takenDate: LocalDate,
    val monthlyEmi: BigDecimal,
)

data class XlsxLoanValueRow(
    val snapshotDate: LocalDate,
    val loanName: String,
    val outstanding: BigDecimal,
)

data class XlsxGoalRow(
    val name: String,
    val goalType: String,                   // "NET_WORTH" | "DEBT_FREE"
    val targetNetWorth: BigDecimal,
    val targetDate: LocalDate,
)

data class XlsxWorkbookData(
    val snapshots: List<XlsxSnapshotRow>,
    val holdingValues: List<XlsxHoldingValueRow>,
    val loans: List<XlsxLoanRow>,
    val loanValues: List<XlsxLoanValueRow>,
    val goals: List<XlsxGoalRow>,
)

/**
 * Pure-JVM XLSX codec. Produces a 5-sheet workbook (Snapshots,
 * HoldingValues, Loans, LoanValues, Goals) and reads the same shape back.
 *
 * The template generator includes column headers + a tiny instructions
 * sheet at the front so the user has context when they open the file.
 *
 * No Android dependencies — testable on `:app:test`.
 */
object XlsxCodec {

    private const val SHEET_INSTRUCTIONS = "Instructions"
    private const val SHEET_SNAPSHOTS = "Snapshots"
    private const val SHEET_HOLDING_VALUES = "HoldingValues"
    private const val SHEET_LOANS = "Loans"
    private const val SHEET_LOAN_VALUES = "LoanValues"
    private const val SHEET_GOALS = "Goals"

    fun writeEmptyTemplate(out: OutputStream) {
        write(
            data = XlsxWorkbookData(
                snapshots = emptyList(),
                holdingValues = emptyList(),
                loans = emptyList(),
                loanValues = emptyList(),
                goals = emptyList(),
            ),
            out = out,
        )
    }

    fun write(data: XlsxWorkbookData, out: OutputStream) {
        XSSFWorkbook().use { wb ->
            writeInstructions(wb)
            writeSnapshots(wb, data.snapshots)
            writeHoldingValues(wb, data.holdingValues)
            writeLoans(wb, data.loans)
            writeLoanValues(wb, data.loanValues)
            writeGoals(wb, data.goals)
            wb.write(out)
        }
    }

    fun read(input: InputStream): XlsxWorkbookData {
        XSSFWorkbook(input).use { wb ->
            return XlsxWorkbookData(
                snapshots = readSnapshots(wb),
                holdingValues = readHoldingValues(wb),
                loans = readLoans(wb),
                loanValues = readLoanValues(wb),
                goals = readGoals(wb),
            )
        }
    }

    // ---- Writers ------------------------------------------------------------

    private fun writeInstructions(wb: Workbook) {
        val s = wb.createSheet(SHEET_INSTRUCTIONS)
        val rows = listOf(
            "FinTrack — Excel template",
            "",
            "Fill in one row per snapshot in the Snapshots sheet.",
            "Holding values reference the snapshot date and the holding name " +
                "as they appear in the app.",
            "Loan values reference the snapshot date and the loan name.",
            "Goals: GoalType is NET_WORTH or DEBT_FREE. DEBT_FREE goals " +
                "ignore the Target column.",
            "Dates use yyyy-MM-dd format. Amounts in rupees (no commas, no symbol).",
        )
        rows.forEachIndexed { i, txt ->
            s.createRow(i).createCell(0).setCellValue(txt)
        }
        s.setColumnWidth(0, 20_000)
    }

    private fun writeSnapshots(wb: Workbook, rows: List<XlsxSnapshotRow>) {
        val s = wb.createSheet(SHEET_SNAPSHOTS)
        s.createHeader("Date", "EarningsInCr", "Notes")
        rows.forEachIndexed { i, r ->
            val row = s.createRow(i + 1)
            row.createCell(0).setCellValue(r.date.toString())
            row.createCell(1).setCellValue(r.earningsInCr.toPlainString())
            row.createCell(2).setCellValue(r.notes.orEmpty())
        }
    }

    private fun writeHoldingValues(wb: Workbook, rows: List<XlsxHoldingValueRow>) {
        val s = wb.createSheet(SHEET_HOLDING_VALUES)
        s.createHeader("SnapshotDate", "HoldingName", "Invested", "Current", "SIP")
        rows.forEachIndexed { i, r ->
            val row = s.createRow(i + 1)
            row.createCell(0).setCellValue(r.snapshotDate.toString())
            row.createCell(1).setCellValue(r.holdingName)
            row.createCell(2).setCellValue(r.invested?.toPlainString().orEmpty())
            row.createCell(3).setCellValue(r.current.toPlainString())
            row.createCell(4).setCellValue(r.sip?.toPlainString().orEmpty())
        }
    }

    private fun writeLoans(wb: Workbook, rows: List<XlsxLoanRow>) {
        val s = wb.createSheet(SHEET_LOANS)
        s.createHeader("Name", "OriginalAmount", "TakenDate", "MonthlyEMI")
        rows.forEachIndexed { i, r ->
            val row = s.createRow(i + 1)
            row.createCell(0).setCellValue(r.name)
            row.createCell(1).setCellValue(r.originalAmount.toPlainString())
            row.createCell(2).setCellValue(r.takenDate.toString())
            row.createCell(3).setCellValue(r.monthlyEmi.toPlainString())
        }
    }

    private fun writeLoanValues(wb: Workbook, rows: List<XlsxLoanValueRow>) {
        val s = wb.createSheet(SHEET_LOAN_VALUES)
        s.createHeader("SnapshotDate", "LoanName", "Outstanding")
        rows.forEachIndexed { i, r ->
            val row = s.createRow(i + 1)
            row.createCell(0).setCellValue(r.snapshotDate.toString())
            row.createCell(1).setCellValue(r.loanName)
            row.createCell(2).setCellValue(r.outstanding.toPlainString())
        }
    }

    private fun writeGoals(wb: Workbook, rows: List<XlsxGoalRow>) {
        val s = wb.createSheet(SHEET_GOALS)
        s.createHeader("Name", "GoalType", "Target", "TargetDate")
        rows.forEachIndexed { i, r ->
            val row = s.createRow(i + 1)
            row.createCell(0).setCellValue(r.name)
            row.createCell(1).setCellValue(r.goalType)
            row.createCell(2).setCellValue(r.targetNetWorth.toPlainString())
            row.createCell(3).setCellValue(r.targetDate.toString())
        }
    }

    // ---- Readers ------------------------------------------------------------

    private fun readSnapshots(wb: Workbook): List<XlsxSnapshotRow> {
        val s = wb.getSheet(SHEET_SNAPSHOTS) ?: return emptyList()
        return s.dataRows().map { row ->
            XlsxSnapshotRow(
                date = LocalDate.parse(row.text(0)),
                earningsInCr = row.decimal(1),
                notes = row.text(2).takeIf { it.isNotBlank() },
            )
        }
    }

    private fun readHoldingValues(wb: Workbook): List<XlsxHoldingValueRow> {
        val s = wb.getSheet(SHEET_HOLDING_VALUES) ?: return emptyList()
        return s.dataRows().map { row ->
            XlsxHoldingValueRow(
                snapshotDate = LocalDate.parse(row.text(0)),
                holdingName = row.text(1),
                invested = row.decimalOrNull(2),
                current = row.decimal(3),
                sip = row.decimalOrNull(4),
            )
        }
    }

    private fun readLoans(wb: Workbook): List<XlsxLoanRow> {
        val s = wb.getSheet(SHEET_LOANS) ?: return emptyList()
        return s.dataRows().map { row ->
            XlsxLoanRow(
                name = row.text(0),
                originalAmount = row.decimal(1),
                takenDate = LocalDate.parse(row.text(2)),
                monthlyEmi = row.decimal(3),
            )
        }
    }

    private fun readLoanValues(wb: Workbook): List<XlsxLoanValueRow> {
        val s = wb.getSheet(SHEET_LOAN_VALUES) ?: return emptyList()
        return s.dataRows().map { row ->
            XlsxLoanValueRow(
                snapshotDate = LocalDate.parse(row.text(0)),
                loanName = row.text(1),
                outstanding = row.decimal(2),
            )
        }
    }

    private fun readGoals(wb: Workbook): List<XlsxGoalRow> {
        val s = wb.getSheet(SHEET_GOALS) ?: return emptyList()
        return s.dataRows().map { row ->
            XlsxGoalRow(
                name = row.text(0),
                goalType = row.text(1).uppercase(),
                targetNetWorth = row.decimalOrNull(2) ?: BigDecimal.ZERO,
                targetDate = LocalDate.parse(row.text(3)),
            )
        }
    }

    // ---- Cell helpers -------------------------------------------------------

    private fun Sheet.createHeader(vararg labels: String) {
        val r = createRow(0)
        labels.forEachIndexed { i, l -> r.createCell(i).setCellValue(l) }
    }

    private fun Sheet.dataRows(): List<Row> {
        val out = mutableListOf<Row>()
        val it = rowIterator()
        var skippedHeader = false
        while (it.hasNext()) {
            val r = it.next()
            if (!skippedHeader) {
                skippedHeader = true
                continue
            }
            if (r.firstCellNum < 0) continue
            // Skip empty rows.
            if ((0 until r.lastCellNum.toInt()).all { r.text(it).isBlank() }) continue
            out += r
        }
        return out
    }

    private fun Row.text(col: Int): String {
        val cell = getCell(col, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) ?: return ""
        return when (cell.cellType) {
            CellType.STRING -> cell.stringCellValue
            CellType.NUMERIC -> cell.numericCellValue.toBigDecimal().toPlainString()
            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            CellType.FORMULA -> when (cell.cachedFormulaResultType) {
                CellType.STRING -> cell.stringCellValue
                CellType.NUMERIC -> cell.numericCellValue.toBigDecimal().toPlainString()
                else -> cell.toString()
            }
            else -> cell.toString()
        }.trim()
    }

    private fun Row.decimal(col: Int): BigDecimal =
        decimalOrNull(col) ?: error("Missing required numeric cell at row ${rowNum + 1}, col ${col + 1}")

    private fun Row.decimalOrNull(col: Int): BigDecimal? {
        val raw = text(col)
        if (raw.isBlank()) return null
        return BigDecimal(raw)
    }
}
