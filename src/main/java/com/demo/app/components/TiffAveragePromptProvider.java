package com.demo.app.components;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class TiffAveragePromptProvider  implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("tiff-demo:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
