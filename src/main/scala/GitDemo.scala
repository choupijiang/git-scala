import org.eclipse.jgit.api._
import scala.collection.JavaConverters._

object GitDemo {
  def main(args: Array[String]): Unit = {
    val cloneCommand = new CloneCommand()
      .setURI("https://github.com/fpinscala/fpinscala.git")

    val git = cloneCommand.call()
    val logs = git.log.call()
    logs.asScala.take(10).foreach { l => println(l) }

  }
}
