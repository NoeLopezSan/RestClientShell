package dev.noelopez.client;

import dev.noelopez.client.command.CustomerCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
//@CommandScan
@EnableCommand(CustomerCommands.class)
//@EnableCommand({CustomerCommands.class, AuthenticationCommands.class})
public class CustomerShellApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerShellApplication.class, args);
	}
}
