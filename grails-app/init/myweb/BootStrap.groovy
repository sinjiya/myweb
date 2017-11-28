package myweb

import grails.util.Holders
import grails.util.Environment

class BootStrap {
    // service injection
    def setupService
    def appName
    def init = { servletContext ->
        appName = Holders.grailsApplication.config.getProperty('info.app.name')
        log.info("system - Bootstrapping for ${appName}.")
        log.info("system - Environment set to ${Environment.current}" )
        def version = Holders.grailsApplication.metadata.getApplicationVersion()
        log.info("system - App version: ${version}.")
        //log.info("system - GRAILS_HOME: ${System.getenv('GRAILS_HOME')}.")
        def me1 = Holders.grailsApplication.config.getProperty('dbuser')
        log.info("system - WHO-------------: ${me1}.")

        if(myweb.Chapter.count() == 0) {
            log.info("system - No Chapter found.  Start importing from /resources.")
            setupService.importChapters()
        }
    }
    def destroy = {
        log.info("system -  ${appName} Shutting down.")
    }
}
