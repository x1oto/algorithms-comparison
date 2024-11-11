import Constants.CHARS
import Constants.TIMES
import kotlin.random.Random

class FastHillClimbingAlgorithm {

    fun run(target: String) {
        var current = randomString(target)

        for (i in 0..TIMES) {
            val bestFitness = fitness(target, current)
            val newNeighbor = generateNeighbor(current, target)
            val newFitness = fitness(target, newNeighbor)

            if (newFitness > bestFitness) {
                current = newNeighbor

                // If the current string matches the target, stop the algorithm
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

    private fun generateNeighbor(current: String, target: String): String {
        val charArray = current.toCharArray()
        val newChar = Random.nextInt(from = 0, until = CHARS.length)

        for(i in charArray.indices) {
            if(charArray[i] != target[i]) charArray[i] = CHARS[newChar]
        }

        return charArray.joinToString("")
    }

    // Compare each character in the target and current strings and count matches
    private fun fitness(target: String, current: String) = target.zip(current).count { it.first == it.second }

    private fun randomString(target: String) =
        // Generate a random string of the same length as the target, using random characters
        target.map { CHARS.random() }.joinToString("")
}