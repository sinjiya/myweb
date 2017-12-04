package myweb

import javax.xml.parsers.*

import org.xml.sax.InputSource

class SetupService {

    def UnicodeBOMInputStream  //Inject service to detect possible UTF-8-with-BOM file import
    // We would only try to import ANSI or UTF-8 type text file

    def importChapters(String fileName="chapters.tsv") {

        def filePath = "resources/${fileName}"
        println new File(filePath).getText("UTF-8")
        FileInputStream fis = new FileInputStream(filePath)
        UnicodeBOMInputStream ubis = new UnicodeBOMInputStream(fis)
        println("Does ${filePath} contains BOM? " + ubis.getBOM())
        ubis.skipBOM()  // remove BOM if any
        BufferedReader bufferedreader = new BufferedReader( new InputStreamReader(ubis, "UTF-8" ) )

        //BufferedReader reader = new BufferedReader( new FileReader(filePath) )  // Work for ANSI file only
        //BufferedReader bufferedreader = new BufferedReader( new InputStreamReader(
        //        new FileInputStream(filePath), "UTF-8" ) )   // Works for UTF-8 without BOM  only

        def line = null
        def chapter
        def fields
        while ((line = bufferedreader.readLine()) != null) {
            println line
            fields = line.split("\t")
            if (fields[0].isInteger()) {
            chapter = new Chapter()
            println("ID: " + fields[0])
            chapter.seqNumber = fields[0].toInteger()
            chapter.title = fields[1]
            //chapter.content = fields.length == 3 ? fields[2] : null
            if (fields.length == 3) {
                if(new File("resources/chapter${fields[0]}.txt").exists ()){
                    chapter.content = new File("resources/chapter${fields[0]}.txt").getText("UTF-8")
                }
                println(fields[2])
                log.info(fields[2])
            }


            if (!chapter.hasErrors() && chapter.save()) {
                //log.info ("Created and saved ${chapter}.")
            } else {
                log.info("Failed to save: ${chapter}.")
            }

        } else {
            continue  //skip the loop
        }
        }
        log.info("Chapters imported from /${filePath}.")
    }

}
