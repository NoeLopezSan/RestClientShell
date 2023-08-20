package dev.noelopez.client.dto;

public record CustomerResponse(long id, String status, CustomerPersonInfo personInfo, CustomerDetailsInfo detailsInfo) {}

