package rest;

import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author maxwell
 */
public enum Environment {

    QA("http://jsonplaceholder.typicode.com"),
    QAI("http://jsonplaceholder.typicode.com"),
    LOCAL(null) {
        @Override
        public String getHost() {
            String host = System.getenv().get("cucumber.host");
            if (Objects.isNull(host)) {
                throw new IllegalStateException("Invalid cucumber.host value for LOCAL environment, use -Dcucumber.host argument to set it when using this environment.");
            }
            return host;
        }
    };

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Environment.class);

    private final String host;

    Environment(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String createUrl(String url) {
        return getHost() + (url.startsWith("/") ? url : "/" + url);
    }

    private static int count;

    public static Environment get() {
        String env = System.getenv().get("cucumber.env");
        if (Objects.isNull(env)) {
            env = "QA";
        }
        logger.info("setting default environment to {}", env);
        return Environment.valueOf(env.toUpperCase());
    }
}
