package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;

public class ProductIdTransporterService {
    private static NitriteId productId;

    public static NitriteId getProductId() {
        return productId;
    }

    public static void setProductId(NitriteId productId) {
        ProductIdTransporterService.productId = productId;
    }
}
