package dev.noelopez.client.dto;

public record CustomerRequest(CustomerStatus status, CustomerPersonInfo personInfo, CustomerDetailsInfo detailsInfo) {
}
