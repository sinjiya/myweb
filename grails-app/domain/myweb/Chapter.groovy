package myweb

class Chapter {
    Integer seqNumber
    String title
    String content
    Date lastUpdated

    static mapping = {
        content type: 'text'
    }

    static constraints = {
        seqNumber(nullable:false)
        title(nullable:false)
        content(nullable:true)
    }

}
