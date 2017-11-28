package myweb

class SetupService {
    def importChapters(String fileName="chapters.tsv") {
        def filePath = "resources/${fileName}"
        BufferedReader reader = new BufferedReader( new FileReader(filePath) )
        def line = null
        def chapter
        def fields
        while ((line = reader.readLine()) != null) {
            fields = line.split("\t")

            chapter = new Chapter()
            chapter.seqNumber = fields[0].toInteger()
            chapter.title = fields[1]
            chapter.content = fields.length==3? fields[2] : null
            //if(fields.length==3) println(fields[2])
            //log.info(chapter.title)


            if(!chapter.hasErrors() && chapter.save()) {
                //log.info ("Created and saved ${chapter}.")
            }
            else {
                log.info ("Failed to save: ${chapter}.")
            }
        }
        log.info("Chapters imported from /${filePath}.")
    }

}
