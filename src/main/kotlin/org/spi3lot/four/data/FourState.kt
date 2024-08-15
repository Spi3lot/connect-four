package org.spi3lot.four.data

/**
 * @author Emilio Zottel
 * @since 14.08.2024, Mi.
 **/
class FourState(
    private val config: FourConfig,
    val board: FourBoard = FourBoard(config),
    val won: BooleanArray = BooleanArray(config.playerCount),
) {

    var player: Int = 0
        private set

    fun placeToken(column: Int): Int? {
        val row = board.dropToken(column) ?: return null
        board[column, row] = player
        return row
    }

    fun nextPlayer() {
        player = (player + 1) % config.playerCount
    }

    fun updateWon(player: Int) {
        for (y in 0..<config.height) {
            for (x in 0..<config.width) {
                for (direction in FourDirection.entries) {
                    if (board.countBidirectional(player, direction, x, y) >= config.min) {
                        won[player] = true
                        return
                    }
                }
            }
        }
    }

}