package uk.co.smitek.springbootmodulitheventtestbug;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventProcessingTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner();

    @Test
    void eventCallsUseCaseFor_EventListener() {

        SomeUseCase someUseCase = mock(SomeUseCase.class);

        runner.withBean(SomeEventListener.class, someUseCase)
                .run(context -> context.publishEvent(new SomeEvent()));

        verify(someUseCase).executeFromEvent();

    }

    @Test
    void eventCallsUseCaseFor_ApplicationModuleListener() {

        SomeUseCase someUseCase = mock(SomeUseCase.class);

        runner.withBean(SomeEventListener.class, someUseCase)
                .run(context -> context.publishEvent(new SomeEvent()));

        verify(someUseCase).executeFromModuleEvent();

    }

    public interface SomeUseCase {
        void executeFromEvent();
        void executeFromModuleEvent();
    }

    @Component
    public static class SomeEventListener {

        private final SomeUseCase effect;

        public SomeEventListener(SomeUseCase effect) {
            this.effect = effect;
        }


        @EventListener
        public void eventListen(SomeEvent event) {
            effect.executeFromEvent();
        }

        @ApplicationModuleListener
        public void moduleEventListen(SomeEvent event) {
            effect.executeFromModuleEvent();
        }

    }

    public static class SomeEvent {}

}
