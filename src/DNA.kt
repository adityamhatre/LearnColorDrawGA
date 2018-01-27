import java.awt.Color
import java.util.*


class DNA(private val mutationRate: Float) {
    private val random = Random(System.nanoTime())
    var pixel = Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))
    var fitness = 0f

    init {
        pixel = Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))
    }

    fun calculateFitness(target: Color) {
        fitness += Math.abs(pixel.red - target.red)
        fitness += Math.abs(pixel.green - target.green)
        fitness += Math.abs(pixel.blue - target.blue)
    }

    infix fun crossover(partner: DNA): DNA {
        val child = DNA(mutationRate)
        val red = if (random.nextBoolean()) pixel.red else partner.pixel.red
        val green = if (random.nextBoolean()) pixel.green else partner.pixel.green
        val blue = if (random.nextBoolean()) pixel.blue else partner.pixel.blue
        child.pixel = Color(red, green, blue)
        child.mutate()
        return child
    }

    private fun mutate() {
        var red = pixel.red
        var green = pixel.green
        var blue = pixel.blue

        if (random.nextFloat() < mutationRate)
            red = random.nextInt(255)
        if (random.nextFloat() < mutationRate)
            green = random.nextInt(255)
        if (random.nextFloat() < mutationRate)
            blue = random.nextInt(255)

        pixel = Color(red, green, blue)
    }

}