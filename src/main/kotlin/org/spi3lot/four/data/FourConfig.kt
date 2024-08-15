package org.spi3lot.four.data

/**
 * @author Emilio Zottel
 * @since 14.08.2024, Mi.
 **/
class FourConfig(
    var width: Int,
    var height: Int,
    var colors: IntArray,
    var playerCount: Int = 2,
    var minWinningLineLength: Int = 4,
    var continueAfterFinalizedRanking: Boolean = false,
) {

    init {
        require(colors.size == playerCount) { "Amount of colors must match playerCount" }
    }

    inline fun forEachCell(action: (Int, Int) -> Unit) {
        for (j in 0..<height) {
            for (i in 0..<width) {
                action(i, j)
            }
        }
    }

}