import java.io.{BufferedInputStream, FileOutputStream}
import java.net.{HttpURLConnection, URL}

object GitHubFileDownloader {
  def downloadFile(fileUrl: String, localPath: String): Unit = {
    var connection: HttpURLConnection = null
    var inputStream: BufferedInputStream = null
    var outputStream: FileOutputStream = null
    try {
      val url = new URL(fileUrl)
      connection = url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("GET")
      val responseCode = connection.getResponseCode
      if (responseCode == HttpURLConnection.HTTP_OK) {
        inputStream = new BufferedInputStream(connection.getInputStream)
        outputStream = new FileOutputStream(localPath)
        val buffer = new Array[Byte](1024ar bytesRead = 0
        while ({ bytesRead = inputStream.read(buffer, 0, 1024); bytesRead != -1 }) {
          outputStream.write(buffer, 0, bytesRead)
        }
        println(s"订阅文件已经下载到 $localPath")
      } else {
        println(s"少女寿命不足，请检查您的网络环境。")
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (inputStream != null) inputStream.close()
      if (outputStream != null) outputStream.close()
      if (connection != null) connection.disconnect()
    }
  }
  def main(args: Array[String]): Unit = {
    val fileUrl = "https://raw.githubusercontent.com/Chihaya-Yuka/free-vpn/main/data/clash.yaml"
    val localPath = "clash.yaml"
    println(s"以 roselia 为名！")
    println(s"少女祈祷中......")
    downloadFile(fileUrl, localPath)
  }
}
