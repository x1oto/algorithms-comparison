import Constants.CHARS
import Constants.TIMES
import kotlin.random.Random

class SimpleHillClimbingAlgorithm {

    fun run(target: String) {
        var current = randomString(target)

        for (i in 0..TIMES) {
            val bestFitness = fitness(target, current)
            val newNeighbor = generateNeighbor(current)
            val newFitness = fitness(target, newNeighbor)

            if (newFitness > bestFitness) {
                current = newNeighbor

                if (current == target) {
                    println("Target string has been found on iteration $i.! Current string --> $current, Target: $target")
                    break
                } else {
                    // Otherwise, print the updated fitness information
                    println("Upgraded fitness has been found on iteration $i.! Current string --> $current, Target: $target")
                }
            }
        }
    }

    private fun generateNeighbor(current: String): String {
        // Convert the current string to a char array for modification
        val charArray = current.toCharArray()

        // Select a random index in the string to change
        val randomIndex = Random.nextInt(from = 0, until = current.length)

        // Select a random character from the list of available characters
        val newChar = Random.nextInt(from = 0, until = CHARS.length)

        // Replace the character at the random index with a new character
        charArray[randomIndex] = CHARS[newChar]

        // Join the char array back into a string and return it
        return charArray.joinToString("")
    }

    private fun fitness(target: String, current: String): Int {
        var match = 0
        // Compare each character in the target and current strings and count matches
        target.forEachIndexed { index, char -> if (current[index] == char) match++ }
        return match
    }

    private fun randomString(target: String) =
        // Generate a random string of the same length as the target, using random characters
        target.map { CHARS.random() }.joinToString("")
}