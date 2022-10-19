import spock.lang.Specification

class HelloWorldTestSpecification extends Specification {
    def "Should be a simple assertion"() {
        expect:
        1 == 1
    }

}
