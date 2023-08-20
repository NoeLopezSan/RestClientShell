package dev.noelopez.client.command.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString(
                "customer-shell>>",
                AttributedStyle.BOLD.background(AttributedStyle.YELLOW));
    }
}
