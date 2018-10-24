import scalashop.RGBA

def red(c: RGBA): Int = (0xff000000 & c) >>> 24
def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16
def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8
def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0
def rgba(r: Int, g: Int, b: Int, a: Int): RGBA = {
  (r << 24) | (g << 16) | (b << 8) | (a << 0)
}

//val c = 255
//val r = red(c)
//val g = green(c)
//val b = blue(c)
//val a = alpha(c)
//rgba(r,g,b,a)

val splitPoints = 0 to 100 by (100 / 2)
val splitTuples = splitPoints zip splitPoints.tail