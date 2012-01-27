/**
 * 
 */
package com.monstersoftwarellc.springneo4jtemplate.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.monstersoftwarellc.springneo4jtemplate.validation.FieldMatchValidator;

/**
 * Validation annotation to validate that 2 fields have the same value.
 * An array of fields and their matching confirmation fields can be supplied.
 *
 * Example, compare 1 pair of fields:
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
 * 
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@ReportAsSingleViolation
@Documented
public @interface FieldMatch {
	
    String message() default "{FieldMatch.field}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return fields to match
     */
    String[] fieldsToMatch();
    
    /**
     * @return
     */
    String fieldToMatchOn();
}
