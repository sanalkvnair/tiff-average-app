package com.demo.app.components;

import org.jline.reader.LineReader;

public class InputReader {
    private final LineReader lineReader;

    public InputReader(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    public String prompt(String prompt) {
        return lineReader.readLine(prompt + ": ");
    }
}
