package org.spi3lot.four

import org.spi3lot.four.data.FourConfig
import org.spi3lot.four.data.FourState
import org.spi3lot.four.management.DrawManager.drawGrid
import org.spi3lot.four.management.DrawManager.drawTokens
import processing.core.PApplet

fun main(args: Array<String>) {
    PApplet.main(ConnectFour::class.java, *args)
}

/**
 * @author Emilio Zottel
 * @since 10.08.2024, Sa.
 **/
class ConnectFour : PApplet() {

    val config = FourConfig(7, 7, intArrayOf(color(255, 0, 0), color(255, 255, 0)))

    val state = FourState(config)

    override fun settings() {
        size(displayWidth / 3, displayHeight / 2)
    }

    override fun setup() {
        noLoop()
    }

    override fun draw() {
        val cellWidth = width / config.width
        val cellHeight = height / config.height
        var backgroundColorIndex = state.player

        if (isGameOver()) {
            backgroundColorIndex = state.winningLines
                .mapIndexed { i, line -> i to line }
                .maxBy { it.second.size }
                .first
        }

        background(config.colors[backgroundColorIndex])
        stroke(0)
        strokeWeight(1f)
        drawGrid(cellWidth, cellHeight)
        strokeWeight(5f)
        drawTokens(cellWidth, cellHeight)
    }

    override fun mousePressed() {
        if (isGameOver()) {
            return
        }

        val column = (config.width * mouseX / width.toFloat()).toInt()
        val row = state.placeToken(column) ?: return
        state.updateWon(state.player, column, row)
        state.nextPlayer()
        redraw()
    }

    override fun keyPressed() {
        if (key.uppercase() == "R") {
            state.reset()
            redraw()
        }
    }

    fun isGameOver(): Boolean = config.continueAfterFinalizedRanking || state.isRankingFinalized()

}