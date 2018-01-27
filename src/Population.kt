import java.awt.Color

/**
 * Created by Aditya on January 27, 2018.
 */
class Population(populationSize: Int, private val mutationRate: Float, private val target: Color) {
    private val population = Array(populationSize) { DNA(mutationRate) }

    fun getFittest(): DNA {
        return population.minBy { it.fitness } as DNA
    }

    fun getFitness(): Float = population.minBy { it.fitness }!!.fitness

    fun calculateFitness() {
        population.forEach { it.calculateFitness(target) }
    }

    fun generateNextGeneration() {
        val matingPool = population.copyOf()
        matingPool.sortBy { it.fitness }

        for (i in 0 until population.size)
            population[i] = matingPool[0] crossover matingPool[1]

    }

}