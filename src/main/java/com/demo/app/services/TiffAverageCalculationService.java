package com.demo.app.services;

import java.io.IOException;

public interface TiffAverageCalculationService {
    void calculateAverage(String inputTiffPath, String outputTiffPath) throws IOException;
}
