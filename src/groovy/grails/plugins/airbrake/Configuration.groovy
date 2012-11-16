package grails.plugins.airbrake

import grails.util.Environment

class Configuration {
    String notifierName = AirbrakeNotifier.NOTIFIER_NAME
    String notifierUrl = AirbrakeNotifier.NOTIFIER_URL
    String notifierVersion = AirbrakeNotifier.NOTIFIER_VERSION
    String env = Environment.current.name
    String apiKey
    List<String> filteredKeys = []
    boolean secure = false
    boolean enabled = true
    String host = AirbrakeNotifier.AIRBRAKE_HOST
    Integer port
    boolean includeEventsWithoutExceptions = false
    Integer threads = 5

    Configuration(Map options) {
        // reimplement the map constructor so we can set the port afterwards
        options.each { k,v -> if (this.hasProperty(k)) { this."$k" = v} }
        port = port ?: (secure ? 443 : 80)
    }

    Map merge(Map options) {
        def merge = properties
        merge.remove 'class'
        merge.remove 'metaClass'
        merge << options
        merge
    }

    String getScheme() {
        secure ? 'https' : 'http'
    }
}
