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
    var min: Int = 4,
) {

    init {
        require(colors.size == playerCount) { "Amount of colors must match playerCount" }
    }

}