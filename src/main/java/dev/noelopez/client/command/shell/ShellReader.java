package dev.noelopez.client.command.shell;

import org.jline.reader.LineReader;
import org.springframework.util.StringUtils;

import java.util.List;

public class ShellReader {
    private LineReader lineReader;

    public ShellReader(LineReader lineReader) {
        this.lineReader = lineReader;
    }
    public String readLine(String displayText) {
        return lineReader.readLine(displayText + ": ");
    }

    public String readLineRequired(String displayText) {
        String value;

        do {
            value = lineReader.readLine(displayText + ": ");

            if (value.isEmpty())
                lineReader.getTerminal().writer().println("Value is mandatory. Please enter a value.");
        } while (value.isEmpty());

        return value;
    }

    public String readLineOptions(String displayText, List<String> options) {
        String value;

        do {
            value = lineReader.readLine(displayText +" "+ options + ": ");

            if (!options.contains(value))
                lineReader.getTerminal().writer().println("Please select a value from "+ options);
        } while (!options.contains(value));

        return value;
    }
}
