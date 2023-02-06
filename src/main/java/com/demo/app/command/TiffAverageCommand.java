package com.demo.app.command;

import com.demo.app.components.InputReader;
import com.demo.app.services.TiffAverageCalculationService;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.io.IOException;

@ShellComponent
public class TiffAverageCommand {
    private final InputReader inputReader;
    private final TiffAverageCalculationService tiffAverageCalculationService;

    TiffAverageCommand(InputReader inputReader,
                       TiffAverageCalculationService tiffAverageCalculationService) {
        this.inputReader = inputReader;
        this.tiffAverageCalculationService = tiffAverageCalculationService;
    }
    @ShellMethod("Calculates the average over all the frames in a tiff file provided and generates a single output averaged frame")
    public void averageTiff() throws IOException {
        String inputTiffPath = null;
        do {
             inputTiffPath = inputReader.prompt("Enter input tiff file absolute path");
             if (!StringUtils.hasText(inputTiffPath)){
                 System.err.println("Input tiff file path is mandatory.");
             }
        } while (!StringUtils.hasText(inputTiffPath));

        String outputTiffPath = null;
        do {
            outputTiffPath = inputReader.prompt("Enter output tiff file absolute path to generate average calculated tiff image");
            if (!StringUtils.hasText(outputTiffPath)){
                System.err.println("Output tiff file path is mandatory to generate average image.");
            }
        } while (!StringUtils.hasText(outputTiffPath));

        tiffAverageCalculationService.calculateAverage(inputTiffPath, outputTiffPath);
    }

}
