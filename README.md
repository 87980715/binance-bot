# binance-bot
simple analyzing bot, checking coin-price-variations for a specified observingPeriod; querying binance for data

# status 
under dev
* currently only logs results
** as info, if margin not exceeded
** as warn, if margin exceeded

# plannings
* add simple order handlings, based on alerts

# usage

* minimal (default to BTC/USDT)

    `java -jar binance-bot-<version>.jar`

* define symbol (scheme: BTCUSDT; ETHUSDT...)

    `java -Dsymbol=LTCUSDT -jar binance-bot-<version>.jar`

* define more options, same like symbol (s.a.)
** observingPeriodInMinutes (default: 1h)
** fetchIntervalInSeconds (default: 2s)
** alertMargin (default: 0.02 = 2%)


