package dev.noelopez.client.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CustomerRequest(String name, String email, LocalDate dateOfBirth,String info, boolean vip) {
}
