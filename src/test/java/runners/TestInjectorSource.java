package runners;

import com.google.inject.Injector;
import com.netflix.governator.InjectorBuilder;
import com.netflix.governator.ShutdownHookModule;
import guiceconfig.ConfigModule;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import java.util.Arrays;

/**
 * TestInjectorSource specifies modules needed for scenario execution
 * Canonical path to this file needs to be defined in 'cucumber.properties' file
 */
public class TestInjectorSource implements InjectorSource {

    @Override
    public Injector getInjector() {
        return InjectorBuilder.fromModules(
                /*
                 * Registers Governator's shutdown hook in order to execute
                 * {@link javax.annotation.PreDestroy} hooks
                 * @see {@link https://github.com/Netflix/governator/wiki}
                 */
                new ShutdownHookModule(),

                // registers default Cucumber's scenario scope
                CucumberModules.createScenarioModule(),
                new ConfigModule(Arrays.asList("test.properties"))
        ).createInjector();
    }
}
