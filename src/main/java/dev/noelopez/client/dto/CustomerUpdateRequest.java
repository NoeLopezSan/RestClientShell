package dev.noelopez.client.dto;

import java.time.LocalDate;

public record CustomerUpdateRequest(String name, String email, LocalDate dateOfBirth, String info, boolean vip) {
}
