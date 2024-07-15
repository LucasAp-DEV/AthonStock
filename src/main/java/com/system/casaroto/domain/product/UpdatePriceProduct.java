package com.system.casaroto.domain.product;

import java.time.LocalDate;

public record UpdatePriceProduct(Float price, Float priceSale, LocalDate date) {
}
