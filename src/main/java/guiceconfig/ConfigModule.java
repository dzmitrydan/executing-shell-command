package guiceconfig;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.List;

/**
 * ConfigModule defines objects related to framework configuration
 *
 */
public class ConfigModule extends AbstractModule {

    private List<String> propertyFiles;

    public ConfigModule(List<String> files) {
        propertyFiles = files;
    }

    @Override
    protected void configure() {
        final ConfigLoader.Provider configLoader = new ConfigLoader.Provider(propertyFiles, "");
        final ConfigLoader loader = configLoader.get();
        loader.getProperties().forEach((k, v) -> {
            bindConstant().annotatedWith(Names.named("properties:" + k)).to(v);
        });
        bind(ConfigLoader.class).toInstance(loader);
    }
}
