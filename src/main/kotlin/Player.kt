private const val HEAL_PERCENT = 0.3
private const val MAX_HEAL_COUNT = 4

class Player(name: String, attack: Int, defence: Int, maxHealth: Int, damageRange: IntRange) :
    Creature(name, attack, defence, maxHealth, damageRange) {

    private var healCount = 0

    fun heal() {
        if (healCount >= MAX_HEAL_COUNT) {
            println("Max $name's healing count reached. Healing is available no more.")
            return
        }

        if (health >= maxHealth) {
            println("Current $name's health ($health) equals maximum health possible ($maxHealth). No healing needed.")
            return
        }
        increaseHealth((HEAL_PERCENT * maxHealth).toInt())
        healCount++
        println("$name uses healing. Current health: $health")

    }
}