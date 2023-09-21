private val CORRECT_FIELD_RANGE = 1..30
private val SUCCESS_HIT_VALUES = listOf(5, 6)
private val DICE_RANGE = 1..6
private const val MIN_HEALTH = 0

abstract class Creature {
    val name: String
    var health: Int
        private set
    val maxHealth: Int
    private var attack = 0
    private var defense: Int = 0
    private var damageRange = 1..6

    constructor(name: String, attack: Int, defense: Int, maxHealth: Int, damageRange: IntRange) {
        this.name = name
        this.attack = setValueInRange(attack, "Attack")
        this.defense = setValueInRange(defense, "Defence")
        this.maxHealth = maxHealth
        this.health = maxHealth
        this.damageRange = damageRange
    }

    fun hit(other: Creature) {
        if (!isAlive() or !other.isAlive()) return
        var attackModifier = attack - other.defense + 1
        if (attackModifier < 1) attackModifier = 1
        val diceValues = List(attackModifier) { DICE_RANGE.random() }
        if (diceValues.any { value -> SUCCESS_HIT_VALUES.contains(value) }) {
            val damage = damageRange.random()
            other.health -= damage
            println(
                "$name hits ${other.name} successfully." +
                        " ${other.name}'s health decreased by $damage and equals ${other.health}"
            )
        } else {
            println("${name}'s hit was not successful.")
        }
    }

    fun isAlive(): Boolean {
        return health > 0
    }

    fun printIsAliveOrDead() {
        if (isAlive()) println("$name is alive.")
        else println("$name is dead.")
    }

    protected fun increaseHealth(value: Int) {
        val newHealth = health + value
        health = if (newHealth < 0) MIN_HEALTH
        else if (health > maxHealth) maxHealth
        else newHealth
    }

    private fun setValueInRange(value: Int, fieldName: String): Int {
        if (value < CORRECT_FIELD_RANGE.min()) {
            println("$fieldName must be in range $CORRECT_FIELD_RANGE. $fieldName is set to lowest possible by default")
            return CORRECT_FIELD_RANGE.min()
        } else if (value > CORRECT_FIELD_RANGE.max()) {
            println("$fieldName must be in range $CORRECT_FIELD_RANGE. $fieldName is set to highest possible by default")
            return CORRECT_FIELD_RANGE.max()
        }
        return value
    }
}