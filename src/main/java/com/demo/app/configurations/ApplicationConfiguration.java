package com.demo.app.configurations;

import com.demo.app.components.InputReader;
import com.demo.app.services.TiffAverageCalculationService;
import com.demo.app.services.TiffAverageCalculationServiceImpl;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public TiffAverageCalculationService getTiffAverageCalculationService() {
        return new TiffAverageCalculationServiceImpl();
    }
    @Bean
    public InputReader inputReader(@Lazy LineReader lineReader) {
        return new InputReader(lineReader);
    }
}
