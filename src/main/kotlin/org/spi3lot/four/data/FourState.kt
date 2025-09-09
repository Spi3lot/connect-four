package org.spi3lot.four.data

import org.spi3lot.four.board.FourBoard
import org.spi3lot.four.board.FourBoardDirection
import org.spi3lot.four.board.FourBoardLine

/**
 * @author Emilio Zottel
 * @since 14.08.2024, Mi.
 **/
class FourState(
    private val config: FourConfig,
    val board: FourBoard = FourBoard(config),
    val winningLines: Array<MutableList<FourBoardLine>> = Array(config.playerCount) { mutableListOf() },
) {

    var player: Int = 0
        private set

    fun reset() {
        board.reset()
        player = 0

        for (i in 0..<config.playerCount) {
            winningLines[i].clear()
        }
    }

    fun placeToken(column: Int): Int? {
        val row = board.dropToken(column) ?: return null
        board[column, row] = player
        return row
    }

    fun nextPlayer() {
        for (i in 1..<config.playerCount) {
            val candidate = (player + i) % config.playerCount

            if (config.continueAfterFinalizedRanking || winningLines[candidate].isEmpty()) {
                player = candidate
                return
            }
        }

        error("There is no next player, all players have a rank already.")
    }

    fun updateWon(player: Int, column: Int, row: Int, returnOnFirstLine: Boolean = false) {
        for (direction in FourBoardDirection.ALL) {
            val line = board.bidirectionalWinningLine(player, direction, column, row)

            if (line != null && line.length >= config.minWinningLineLength) {
                winningLines[player].add(line)

                if (returnOnFirstLine) {
                    return
                }
            }
        }
    }

    fun isRankingFinalized(): Boolean {
        return winningLines.count { it.isEmpty() } <= 1
    }

}