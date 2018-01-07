package com.fullsample.domain

import java.io.File

trait DirToolKit {

  val rootDir = new File("").getAbsolutePath + File.separator

  def uploadDir(): String = {
    val path = rootDir + "upload" + File.separator
    import com.fullsample.tool.implicits._
    val dir = path.toFile
    dir.exists() match {
      case true => dir.getAbsolutePath + File.separator
      case _ => {
        dir.mkdirs()
        uploadDir()
      }
    }
  }

}


