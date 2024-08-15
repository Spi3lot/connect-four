package org.spi3lot.four

import org.spi3lot.four.data.FourConfig
import org.spi3lot.four.data.FourState
import processing.core.PApplet

fun main(args: Array<String>) {
    PApplet.main(ConnectFour::class.java, *args)
}

/**
 * @author Emilio Zottel
 * @since 10.08.2024, Sa.
 **/
class ConnectFour : PApplet() {

    private val config = FourConfig(7, 7, intArrayOf(color(255, 0, 0), color(255, 255, 0)))

    private val state = FourState(config)

    override fun settings() {
        size(displayWidth / 3, displayHeight / 2)
    }

    override fun setup() {
        noLoop()
        strokeWeight(5f)
    }

    override fun draw() {
        background(config.colors[state.player])
        val cellWidth = width / config.width
        val cellHeight = height / config.height

        for (j in 0..<config.height) {
            for (i in 0..<config.width) {
                val player = state.board[i, j] ?: continue
                fill(config.colors[player])
                stroke(if (state.won[player]) color(0, 255, 0) else 255)

                ellipse(i * cellWidth + cellWidth * 0.5f,
                    height - 1 - (j * cellHeight + cellHeight * 0.5f),
                    cellWidth * 0.8f,
                    cellHeight * 0.8f)
            }
        }
    }

    override fun mousePressed() {
        val column = (config.width * mouseX / width.toFloat()).toInt()
        state.placeToken(column) ?: return
        state.updateWon(state.player)
        state.nextPlayer()
        redraw()
    }

}