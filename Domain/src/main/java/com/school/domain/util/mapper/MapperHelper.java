package com.school.domain.util.mapper;

import java.util.Optional;
import org.mapstruct.Named;

public interface MapperHelper {

  /**
   * Generic method to manage optional of different kind of data. It has to ve call just with an
   * Object value to be wrapped.
   *
   * <p>
   *
   * @param value Value to be managed
   * @return Value wrapped as {@link Optional#ofNullable(Object)}
   * @param <T> Generic class type to be managed.
   */
  default <T> Optional<T> toOptional(final T value) {
    return Optional.ofNullable(value);
  }

  /**
   * Generic method to manage optional of different kind of data. It has to ve call just with an
   * Object value to unwrap it if is present or else a null value will be returned.
   *
   * <p>
   *
   * @param value {@link Optional} value to unwrap
   * @return Value unwrapped or else null if it is not present
   * @param <T> Generic type of value to be maged.
   */
  default <T> T fromOptional(final Optional<T> value) {
    return value.orElse(null);
  }

  /**
   * Handler implementing a type {@link Integer} value to use the {@link #toOptional(Object)} to
   * wrap the Integer value received as method's parameter
   *
   * <p>
   *
   * @param value {@link String} value to be wrapped as {@link Optional#ofNullable(Object)}
   * @return {@link Optional#ofNullable(Object)} wrapped Integer value received.
   */
  @Named("toOptionalHandler")
  default Optional<String> toOptionalHandler(final String value) {
    return toOptional(value);
  }

  /**
   * Handler implementing a type {@link String} value to use the {@link #fromOptional(Optional)} to
   * unwrap the Optional Integer value received as method's parameter
   *
   * <p>
   *
   * @param value {@link Optional} Integer value to unwrap
   * @return {@link String} Integer unwrapped value if present, or else null value.
   */
  @Named("fromOptionalHandler")
  default String fromOptionalHandler(final Optional<String> value) {
    return value != null ? value.orElse(null) : null;
  }
}
