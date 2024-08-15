package org.spi3lot.four.board

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
data class FourBoardDirection(val dx: Int, val dy: Int) {

    companion object {

        val HORIZONTAL = FourBoardDirection(1, 0)

        val VERTICAL = FourBoardDirection(0, 1)

        val DIAGONAL = FourBoardDirection(1, 1)

        val ANTIDIAGONAL = FourBoardDirection(1, -1)

        val ALL = arrayOf(HORIZONTAL, VERTICAL, DIAGONAL, ANTIDIAGONAL)

    }

    operator fun unaryMinus(): FourBoardDirection {
        return FourBoardDirection(-dx, -dy)
    }

}