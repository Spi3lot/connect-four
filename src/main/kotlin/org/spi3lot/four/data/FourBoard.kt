package org.spi3lot.four.data

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
class FourBoard(
    private val config: FourConfig,
    private val board: Array<Array<Int?>> = Array(config.height) { Array(config.width) { null } },
) {

    fun dropToken(column: Int): Int? {
        for (row in 0..<config.height) {
            if (board[row][column] == null) {
                return row
            }
        }

        return null
    }

    fun countBidirectional(player: Int, direction: FourDirection, x: Int, y: Int): Int {
        if (this[x, y] != player) {
            return 0
        }

        return countUnidirectional(player, direction.dx, direction.dy, x, y) +
                countUnidirectional(player, -direction.dx, -direction.dy, x, y) -
                1
    }

    private fun countUnidirectional(player: Int, dx: Int, dy: Int, x: Int, y: Int): Int {
        var count = 0

        while (isInBounds(x + count * dx, y + count * dy) && this[x + count * dx, y + count * dy] == player) {
            count++
        }

        return count
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