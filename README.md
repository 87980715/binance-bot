# binance-bot
simple binance bot, checking coin-price-movements or trading based on TA (MACD) 

# status
* in dev
* no persistent storage, yet
* offers:
  * simple logging of prices
  * trading based on TA (MACD)
  * market orders only yet

# plannings
* introduce persistent storage (h2) 

# usage requirements 
* installed `java-jdk (version 8+)`
* `maven` (for build)

# build
* `mvn clean install`

# usage

* minimal (defaults to BTC/USDT) - only logging

    `java -jar binance-bot-<version>.jar`

* define symbol

    `java -Dsymbol=LTCUSDT -Dmode=trademacd -Dquantity -jar binance-bot-<version>.jar`

* you may specify currently few more options, for trading you need to provide your respective binance api keys
  * `symbol` (eg. BTCUSDT, ETHUSDT) (currently only USDT pairings supported)
  * `quantity` #(coins) (required for trade)
  * `apiKey` only required for trade mode
  * `secretKey` only required for trade mode
  * `mode` default: `<empty>`, trademacd 
  * `recvWindow` validity length for a order request to binance
