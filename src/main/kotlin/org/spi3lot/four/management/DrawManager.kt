package org.spi3lot.four.management

import org.spi3lot.four.ConnectFour
import org.spi3lot.four.board.FourBoardLine
import org.spi3lot.four.board.FourBoardPosition

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
object DrawManager {

    fun ConnectFour.drawGrid(cellWidth: Int, cellHeight: Int) {
        for (i in 1..<config.width) {
            line(i * cellWidth.toFloat(), 0f, i * cellWidth.toFloat(), height.toFloat())
        }

        for (j in 1..<config.height) {
            line(0f, j * cellHeight.toFloat(), width.toFloat(), j * cellHeight.toFloat())
        }
    }

    fun ConnectFour.drawTokens(cellWidth: Int, cellHeight: Int) {
        config.forEachCell { i, j ->
            val player = state.board[i, j] ?: return@forEachCell
            val point = FourBoardPosition(i, j)
            val strokeColor = if (isOnAnyLine(point, state.winningLines[player])) color(0, 255, 0) else 0
            stroke(strokeColor)
            fill(config.colors[player])

            ellipse(i * cellWidth + cellWidth * 0.5f,
                height - 1 - (j * cellHeight + cellHeight * 0.5f),
                cellWidth * 0.8f,
                cellHeight * 0.8f)
        }
    }

    private fun isOnAnyLine(point: FourBoardPosition, winningLines: Collection<FourBoardLine>): Boolean {
        return winningLines.any { point in it }
    }

}

