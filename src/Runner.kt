import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame


/**
 * Created by Aditya on January 27, 2018.
 */
var width = 0
var height = 0
val window = Window()
val path = "C:\\Users\\Aditya\\Desktop\\monalisa.png"
fun main(args: Array<String>) {


    val img = ImageIO.read(File(path))
    width = img.width
    height = img.height
    initWindow()

    val random = Random(System.nanoTime())

    val populationSize = 3
    val mutationRate = 0.1f


    val populationArray = Array(width) {
        Array(height) { Population(populationSize, mutationRate, Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))) }
    }
    for (i in 0 until width)
        for (j in 0 until height) {
            populationArray[i][j] = Population(populationSize, mutationRate, Color(img.getRGB(i, j)))
        }

    var gen = 1
    while (true) {
        val targetPixels = Array(width) {
            Array(height) { Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)) }
        }
        var fitness = 0f
        for (i in 0 until width)
            for (j in 0 until height) {
                targetPixels[i][j] = populationArray[i][j].getFittest().pixel
                fitness += populationArray[i][j].getFitness()
                populationArray[i][j].generateNextGeneration()
                populationArray[i][j].calculateFitness()
            }
        saveAsImg(targetPixels, gen)
        println("Saved generation $gen")
        window.generated.icon = ImageIcon("C:\\Users\\Aditya\\Desktop\\gen\\gen-$gen.png")
        gen++
    }


}

fun initWindow() {
    window.original.icon = ImageIcon(path)
    window.generated.icon = ImageIcon("C:\\Users\\Aditya\\Desktop\\gen\\gen-1.png")
    val jFrame = JFrame()
    jFrame.contentPane = window.rootPanel
    jFrame.setSize(width * 2 + 100, height + 50)
    jFrame.setLocationRelativeTo(null)
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    jFrame.isVisible = true
}

fun saveAsImg(fittest: Array<Array<Color>>, generation: Int) {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    for (i in 0 until width)
        for (j in 0 until height) {
            image.setRGB(i, j, fittest[i][j].rgb)
        }
    ImageIO.write(image, "png", File("C:\\Users\\Aditya\\Desktop\\gen\\gen-$generation.png"))
}

