package guiceconfig;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.google.common.io.Resources.*;

/**
 * Config loader is responsible for loading configuration parameters
 */
@Slf4j
public class ConfigLoader {

    private final Properties properties;

    ConfigLoader(Properties properties) {
        this.properties = properties;
    }

    /**
     * Returns property value for given key
     *
     * @param key Property name
     * @return Property value
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * Returns property value for given key or default value if there is no value for the key found
     *
     * @param key          Property name
     * @param defaultValue Default value
     * @return Property value
     */
    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    /**
     * Returns all loaded properties in pairs of (key, value)
     *
     * @return All properties stored
     */
    public Map<String, String> getProperties() {
        return this.properties.entrySet()
                .stream()
                .collect(Collectors.toMap(obj -> obj.getKey().toString(),
                        obj -> obj.getValue().toString()
                ));
    }

    /**
     * GetParameter differs from {@link #getProperty(String)}
     * While {@link #getProperty(String)} just reads property value by key,
     * this method processes parameter value (not a key). Parameters taken from
     * Cucumber scenarios may be in two forms: 'just-parameter-value' and 'properties:property-key'
     * In first case we just return back parameter value, for the second case
     * we parse property-key and execute {@link #getProperty(String)} for it
     *
     * @param property Parameter value or property key in form 'properties:property-key'
     * @return parameter value
     */
    public String getParameter(String property) throws IOException {
        if (property.startsWith("properties:")) {
            return getProperty(StringUtils.substringAfter(property, "properties:"));
        }
        if (property.startsWith("resource:")) {
            final String resource = StringUtils.substringAfter(property, "resource:");
            final String content = asCharSource(getResource(resource), Charsets.UTF_8).read();
            return content;
        }
        return property;
    }

    /**
     * Provider just reads the configuration
     * It is possible to override default property values (defined in *.properties files via
     * environment variables or Java system properties. For instance, for property 'my.parameter',
     * environment variable 'MY_PARAMETER' will be checked
     * <br/>
     * Properties will be read in following order, so that latest value is used:
     * 1. *properties files
     * 2. Java system properties (e.g. set up via cmd)
     * 3. Environment variables of execution agent
     */
    public static class Provider implements javax.inject.Provider<ConfigLoader> {
        private static final String CFG_PATH = "guiceconfig";

        private final List<String> files;
        private final String env;

        public Provider(List<String> files, String env) {
            this.files = files;
            this.env = env;
        }

        @Override
        public ConfigLoader get() {
            Properties p = new Properties();

            for (String file : this.files) {
                String filePath = env.isEmpty() ? file : CFG_PATH + "/" + env;
                log.info("Loading configuration file: {}", filePath);
                ByteSource source = asByteSource(getResource(filePath));
                try (InputStream inputStream = source.openStream()) {
                    p.load(inputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            overrideDefaultValues(p);
            return new ConfigLoader(p);
        }

        /**
         * Overrides existing property values with System properties
         * System properties may be set via command line for maven executions
         *
         * @param properties properties map to override
         */
        private void overrideDefaultValues(Properties properties) {
            for (String key : properties.stringPropertyNames()) {
                String envKey = key.replaceAll("\\.", "_").toUpperCase();
                Optional.ofNullable(
                        StringUtils.defaultString(System.getenv(envKey), System.getProperty(key)))
                        .ifPresent(val -> {
                            log.debug("Overriding {} with {}", key, val);
                            properties.put(key, val);
                        });
            }
        }
    }
}
