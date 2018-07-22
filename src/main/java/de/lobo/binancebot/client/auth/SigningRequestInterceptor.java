package de.lobo.binancebot.client.auth;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * adds binance related header and signature to request payload for endpoints requiring authentication
 */
@Component
public class SigningRequestInterceptor implements RequestInterceptor {
    private final static Logger log = LoggerFactory.getLogger(SigningRequestInterceptor.class);

    @Value("${apiKey}")
    private String apiKey;

    @Value("${secretKey}")
    private String secret;

    @Value("${recvWindow}")
    private long recvWindow;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        StringBuilder body = new StringBuilder(new String(requestTemplate.body()));

        addBinanceApiKeyHeader(requestTemplate);
        addAdditionalParams(body);
        addSignature(body);

        requestTemplate.body(body.toString());
    }

    private void addSignature(StringBuilder body) {
        String signature = signPayload(body.toString().getBytes(Charsets.UTF_8));
        body.append(String.format("&signature=%s", signature));
        log.debug("signing request with signature: {}");
    }

    private void addBinanceApiKeyHeader(RequestTemplate requestTemplate) {
        requestTemplate.header("X-MBX-APIKEY", apiKey);
    }

    private void addAdditionalParams(StringBuilder body) {
        body.append(String.format("&recvWindow=%d&timestamp=%d",
                recvWindow, System.currentTimeMillis()));
    }

    private String signPayload(byte[] payload) {
        return Hashing.hmacSha256(secret.getBytes(Charsets.UTF_8)).hashBytes(payload).toString();
    }
}
