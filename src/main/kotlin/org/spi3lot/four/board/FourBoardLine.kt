package org.spi3lot.four.board

import kotlin.math.abs
import kotlin.math.max

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
data class FourBoardLine(
    val start: FourBoardPosition,
    val end: FourBoardPosition,
) {

    val length: Int
        get() = 1 + max(abs(end.x - start.x), abs(end.y - start.y))

    operator fun contains(point: FourBoardPosition): Boolean {
        return point.x in start.x..end.x && point.y in start.y..end.y
    }

}