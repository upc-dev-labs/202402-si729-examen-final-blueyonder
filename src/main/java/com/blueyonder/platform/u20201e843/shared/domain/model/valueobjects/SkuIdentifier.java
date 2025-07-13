package com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Value Object representing an embedded SKU identifier with UUID format validation.
 * <p>
 * Ensures that any SKU identifier used in the domain is a valid, non-empty UUID string.
 * Stored as an embeddable type in JPA entities.
 * </p>
 *
 * @param skuIdentifier UUID string representing the SKU.
 *
 * @author Author
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
