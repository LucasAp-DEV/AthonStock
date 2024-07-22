package com.system.athon_stock.domain.product;

import java.time.LocalDate;

public record UpdatePriceProduct(Float price, Float lucro, LocalDate date) {
}
