package com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Embedded SKU identifier ensuring valid UUID format.
 *
 * @param skuIdentifier UUID string
 */
@Embeddable
public record SkuIdentifier(String skuIdentifier) {

    public SkuIdentifier {
        if (skuIdentifier == null || skuIdentifier.isBlank()) {
            throw new IllegalArgumentException("SKU identifier cannot be null or empty");
        }

        try {
            UUID.fromString(skuIdentifier);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("SKU identifier must be a valid UUID");
        }
    }
}
