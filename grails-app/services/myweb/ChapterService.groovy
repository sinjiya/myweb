package myweb

import grails.gorm.services.Service

@Service(Chapter)
interface ChapterService {

    Chapter get(Serializable id)

    List<Chapter> list(Map args)

    Long count()

    void delete(Serializable id)

    Chapter save(Chapter chapter)

}