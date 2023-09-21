fun main() {

    val maxPlayerHealth = 30

    val player = Player("Developer", 14, 13, maxPlayerHealth, 1..6)
    val monster1 = Monster("Procrastination", 15, 13, 20, 1..6)
    val monster2 = Monster("Another Zoom call", 14, 12, 15, 1..6)

    while (player.isAlive() and (monster1.isAlive() or monster2.isAlive())) {

        player.hit(monster1)
        monster1.hit(player)

        player.hit(monster2)
        monster2.hit(player)

        if (player.health <= (maxPlayerHealth / 2)) player.heal()

        player.printIsAliveOrDead()
        monster1.printIsAliveOrDead()
        monster2.printIsAliveOrDead()
        println()

        Thread.sleep(2000)
    }
}