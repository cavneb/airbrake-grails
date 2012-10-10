# Airbrake Plugin for Grails

This is the notifier plugin for integrating apps with [Airbrake](http://airbrake.io).

When an uncaught exception occurs, Airbrake will POST the relevant data to the Airbrake server specified in your environment.

## Installation

Add the following to your `BuildConfig.groovy`

```
compile ":airbrake:0.7.2"
```

## Compatability

This plugin is compatible with Grails version 2.0 or greater.

## Configuration

Once the plugin is installed, you only need to add a few lines of code into the `Config.groovy` file, all within the log4j map:

```groovy

log4j = {
  // Example of changing the log pattern for the default console appender:
  appenders {
    def airbrakeAppender = new grails.plugins.airbrake.AirbrakeAppender (
      name: 'airbrake',
      api_key: 'API_KEY',
      filtered_keys: ['password']
    )
    ...
  }

  root {
    debug 'stdout', 'airbrake' // This can be added to any log level, not only 'debug'
  }
}
```
By default, the environment name used in Airbrake will match that of the current Grails environment. To change this default, set the env property of the AirbrakeAppender (above).

```groovy
  def airbrakeAppender = new grails.plugins.airbrake.AirbrakeAppender(
    name: 'airbrake',
    api_key: 'API_KEY',
    filtered_keys: ['password'],
    env: grails.util.Environment.current.name[0..0] // Airbrake env name changed from default value of Development/Test/Production to D/T/P
  )
``` 

## Testing

To test the Airbrake exception notification, an exception must be thrown in your application when running. You can visit the following url to trigger a test exception:

```
http://localhost:8080/airbrakeTest/throwException
```

Now log into Airbrake.io and you should see your exception listed.

## TODO

* Update plugin to work with Grails version 2.0+

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

# Kudos

Most of the source code has been written by Phuong LeCong ([https://github.com/plecong/grails-airbrake](https://github.com/plecong/grails-airbrake)).
