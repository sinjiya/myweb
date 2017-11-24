package myweb

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChapterServiceSpec extends Specification {

    ChapterService chapterService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Chapter(...).save(flush: true, failOnError: true)
        //new Chapter(...).save(flush: true, failOnError: true)
        //Chapter chapter = new Chapter(...).save(flush: true, failOnError: true)
        //new Chapter(...).save(flush: true, failOnError: true)
        //new Chapter(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //chapter.id
    }

    void "test get"() {
        setupData()

        expect:
        chapterService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Chapter> chapterList = chapterService.list(max: 2, offset: 2)

        then:
        chapterList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        chapterService.count() == 5
    }

    void "test delete"() {
        Long chapterId = setupData()

        expect:
        chapterService.count() == 5

        when:
        chapterService.delete(chapterId)
        sessionFactory.currentSession.flush()

        then:
        chapterService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Chapter chapter = new Chapter()
        chapterService.save(chapter)

        then:
        chapter.id != null
    }
}
