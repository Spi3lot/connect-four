package org.spi3lot.four.board

import org.spi3lot.four.data.FourConfig

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
class FourBoard(
    private val config: FourConfig,
    private val board: Array<Array<Int?>> = Array(config.height) { Array(config.width) { null } },
) {

    fun reset() {
        for (y in 0..<config.height) {
            for (x in 0..<config.width) {
                board[y][x] = null
            }
        }
    }

    fun dropToken(column: Int): Int? {
        for (row in 0..<config.height) {
            if (board[row][column] == null) {
                return row
            }
        }

        return null
    }

    fun bidirectionalWinningLine(
        player: Int,
        direction: FourBoardDirection,
        x: Int,
        y: Int,
    ): FourBoardLine? {
        if (this[x, y] != player) {
            return null
        }

        val start = unidirectionalWinningLine(player, direction, x, y)
        val end = unidirectionalWinningLine(player, -direction, x, y)
        return FourBoardLine(start, end)
    }

    private fun unidirectionalWinningLine(
        player: Int,
        direction: FourBoardDirection,
        x: Int,
        y: Int,
    ): FourBoardPosition {
        val point = FourBoardPosition(x, y)

        while (isInBounds(point.x, point.y) && this[point.x, point.y] == player) {
            point.move(direction)
        }

        point.move(-direction)
        return point
    }

    private fun isInBounds(x: Int, y: Int): Boolean {
        return x in 0..<config.width && y in 0..<config.height
    }

    operator fun get(x: Int, y: Int): Int? {
        return board[y][x]
    }

    operator fun set(x: Int, y: Int, value: Int) {
        board[y][x] = value
    }

}