//package dev.noelopez.client.command.config;
//
//import org.jline.utils.AttributedString;
//import org.jline.utils.AttributedStyle;
//import org.springframework.shell.jline.PromptProvider;
//import org.springframework.shell.result.CommandNotFoundMessageProvider;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomPromptProvider implements PromptProvider, CommandNotFoundMessageProvider {
//    @Override
//    public AttributedString getPrompt() {
//        return new AttributedString(
//                "customer-shell>>",
//                AttributedStyle.BOLD.background(AttributedStyle.YELLOW));
//    }
//
//    @Override
//    public String apply(ProviderContext providerContext) {
//        return String.format("The command '%s' you entered was not found. Use help to view the list of available commands",providerContext.text()) ;
//    }
//}
