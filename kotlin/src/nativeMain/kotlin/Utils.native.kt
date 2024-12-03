import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CC_MD5
import platform.CoreCrypto.CC_MD5_DIGEST_LENGTH
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.componentsSeparatedByCharactersInSet
import platform.Foundation.create
import platform.posix.getcwd

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual fun readInput(name: String): List<String> {
    val dir = memScoped {
        val tmp = allocArray<ByteVar>(512)
        getcwd(tmp, 512.convert())
        tmp.toKString()
    }
    val data = NSString.create(
        NSURL(fileURLWithPath = "$dir/../inputs/$name.txt"),
    )
    return data!!.componentsSeparatedByCharactersInSet(NSCharacterSet.newlineCharacterSet)
        .map { it as String }
}

@OptIn(ExperimentalForeignApi::class, ExperimentalStdlibApi::class)
actual fun String.md5(): String {
    val input = this.encodeToByteArray()
    val digest = UByteArray(CC_MD5_DIGEST_LENGTH)
    input.usePinned { inputPinned ->
        digest.usePinned { digestPinned ->
            CC_MD5(inputPinned.addressOf(0), input.size.convert(), digestPinned.addressOf(0))
        }
    }
    val digestString = digest.toHexString()
    return digestString
}