package org.pitest.cucumber;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cucumber.api.junit.Cucumber;
import cucumber.runner.EventBus;
import cucumber.runner.Runner;
import cucumber.runner.RunnerSupplier;
import gherkin.events.PickleEvent;
import gherkin.pickles.Pickle;
import io.cucumber.core.options.RuntimeOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitest.testapi.Description;
import org.pitest.testapi.ResultCollector;

@ExtendWith(MockitoExtension.class)
public class ScenarioTestUnitTest {

    Pickle pickle = new Pickle(null, null, emptyList(), null, emptyList());

    PickleEvent scenario = new PickleEvent(null, pickle);

    EventBus eventBus = mock(EventBus.class);

    Runner runner = new Runner(eventBus, emptyList(), RuntimeOptions.defaultOptions());

    RunnerSupplier runnerSupplier = () -> {
        return runner;
    };

    @Mock
    private ResultCollector resultCollector;

    @Test
    public void should_run_scenario_and_call_collector_when_ran() {
        // given
        ScenarioTestUnit testUnit = new ScenarioTestUnit(new Description("", HideFromJUnit.Concombre.class), scenario, runnerSupplier, eventBus);

        // when
        testUnit.execute(resultCollector);

        // then
        verify(eventBus, times(2)).send(any());
        verify(resultCollector, times(1)).notifyStart(any());
    }

    private static class HideFromJUnit {

        @RunWith(Cucumber.class)
        private static class Concombre {
        }

    }
}
