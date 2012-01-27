/**
 * 
 */
package com.monstersoftwarellc.springneo4jtemplate.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;

import com.monstersoftwarellc.springneo4jtemplate.annotations.FieldMatch;

/**
 * @author Nick(Work)
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private String[] fieldsToMatch;
	private String fieldToMatchOn;

    @Override
    public void initialize(final FieldMatch constraintAnnotation){
    	fieldsToMatch = constraintAnnotation.fieldsToMatch();
    	fieldToMatchOn = constraintAnnotation.fieldToMatchOn();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context){
        try{
        	boolean ret = true;
    		String val = (String) BeanUtils.getPropertyDescriptor(value.getClass(), fieldToMatchOn).getReadMethod().invoke(value);
        	for(String field : fieldsToMatch){ 
        		String valN = (String) BeanUtils.getPropertyDescriptor(value.getClass(), field).getReadMethod().invoke(value);
        		if(!((val == null && valN == null) || (valN != null && valN.equals(val)))){
        			ret = false;
        			break;
        		}
        	}
            return ret;
        } catch (final Exception ignore){
            // ignore
        }
        return false;
    }
}
