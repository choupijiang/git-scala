import org.apache.commons.io.{FileUtils, FilenameUtils}
import org.apache.tika.Tika
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.{Constants, FileMode}
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.treewalk.TreeWalk

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, Paths}
import org.apache.tika.exception.TikaException
import org.apache.tika.metadata.Metadata
import org.apache.tika.metadata.Property.PropertyType
import org.apache.tika.mime.MediaType
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.AbstractParser
import org.apache.tika.sax.XHTMLContentHandler
import org.xml.sax.ContentHandler
import org.xml.sax.SAXException


//https://www.vogella.com/tutorials/JGit/article.html
object GitCommit {

  def main(args: Array[String]): Unit = {
//    val builder = new FileRepositoryBuilder
//    val repository = builder
//      .setGitDir(new File("/tmp/git/repo/.git"))
//      .readEnvironment
//      .findGitDir // scan environment GIT_* variables
//      .build // scan up the file system tree

//    val cloneCommand = new CloneCommand()
//      .setURI("https://github.com/fpinscala/fpinscala.git")

    import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
    val credentialsProvider = new UsernamePasswordCredentialsProvider("", "")

    val git =
    Git.cloneRepository()
      .setURI("https://github.com/fpinscala/fpinscala.git")
      .setDirectory(new File("./tmp/mongo"))
      .setCredentialsProvider(credentialsProvider)
      .call();

    val repository = git.getRepository

    val walk = new RevWalk(repository)
    val head = repository.resolve(Constants.HEAD)
    val commit = walk.parseCommit(head)

    val treeWalk = new TreeWalk(repository)
    treeWalk.addTree(commit.getTree)
    treeWalk.setRecursive(true)
    val tika = new Tika()
    while ( {
      treeWalk.next
    }) {
      if(treeWalk.getFileMode(0).equals(FileMode.REGULAR_FILE) && Files.exists(Paths.get(treeWalk.getPathString))) {
//        val fileType = FilenameUtils.getExtension(treeWalk.getPathString)
//        println("File type: " + fileType);


        System.out.println("File type: " + tika.detect(new File(treeWalk.getPathString)))

      }
//      val blameResult = git.blame.setFilePath(treeWalk.getPathString).call
//      for (i <- 0 until blameResult.getResultContents.size) {
//        val authorIdent = blameResult.getSourceAuthor(i)
//        println("Line " + (i + 1) + " was last modified by " + authorIdent.getName + " on " + authorIdent.getWhen)
//      }
    }

    walk.dispose
    FileUtils.deleteDirectory(repository.getDirectory.getParentFile)

  }

}
