package org.spi3lot.four.data

/**
 * @author Emilio Zottel
 * @since 15.08.2024, Do.
 **/
enum class FourDirection(val dx: Int, val dy: Int) {

    HORIZONTAL(1, 0),
    VERTICAL(0, 1),
    DIAGONAL(1, 1),
    ANTIDIAGONAL(1, -1)

}