# binance-bot
simple analyzing bot, checking coin-price-variations for a specified observingPeriod; querying binance-api for data

# status
* in dev
* no persistent storage, yet
* calcs based on values, fetched in `observingPeriodInMinutes` time range
* calcs & values will be reset after `observingPeriodInMinutes`
* no order handling yet
* no authorization on binance api access required, yet
* currently only logs variation results to console
  * as info, if margin not exceeded
  * as warn, if margin exceeded

# plannings
* add simple order handlings, based on alerts
* introduce persistent storage for longer time framed observations
* notification-mails (low prio)
* implement usage of authorized binance api for order handling 

# usage requirements 
* installed `java-jdk (version 8+)`
* `maven` (for build)

# build
* `mvn clean install`

# usage

* minimal (defaults to BTC/USDT)

    `java -jar binance-bot-<version>.jar`

* define symbol (scheme: BTCUSDT; ETHUSDT...)

    `java -Dsymbol=LTCUSDT -jar binance-bot-<version>.jar`

* you may specify currently few more options, same like symbol (s.a.)
  * `observingPeriodInMinutes` (default: 1h)
  * `fetchIntervalInSeconds` (default: 2s)
  * `alertMargin` (default: 0.02 = 2%)
