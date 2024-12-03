import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

actual fun readInput(name: String): List<String> = Path("../inputs/$name.txt").readLines()

actual fun String.md5(): String = BigInteger(
    1,
    MessageDigest.getInstance("MD5").digest(toByteArray()),
).toString(16)
    .padStart(32, '0')

