package org.spi3lot.four.board

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
data class FourBoardPosition(var x: Int, var y: Int) {

    fun move(direction: FourBoardDirection) {
        x += direction.dx
        y += direction.dy
    }

}