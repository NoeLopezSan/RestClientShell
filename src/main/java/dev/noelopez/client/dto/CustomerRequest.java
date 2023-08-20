package dev.noelopez.client.dto;

import java.time.LocalDate;

public record CustomerRequest(CustomerStatus status, Boolean vip) {

}
