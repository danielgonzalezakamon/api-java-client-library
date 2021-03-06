package com.akamon.api.client.service.validation;

import com.akamon.api.client.error.ServiceInvocationException;
import com.akamon.api.client.service.error.BadParameterTypeException;
import com.akamon.api.client.service.error.ParameterInvalidRegexpFormatException;
import com.akamon.api.client.service.error.ParameterRequiredException;
import com.akamon.api.client.service.error.ParameterTooLongException;
import com.akamon.api.client.service.error.ParameterTooShortException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service parameter validator utility 
 * @author Miguel Angel Garcia
 */
public class ServiceParameterValidator {
    
    private String serviceCode = null;    
    
    private String type = "";
    private boolean required = false;
    private List regexp = null;
    private Integer minLength = null;
    private Integer maxLength = null;
    
    private SimpleDateFormat sdf = null;
        
    /**
     * Builds the vaidator
     * @param serviceCode 
     */
    public ServiceParameterValidator(String serviceCode){
        this.serviceCode = serviceCode;
    }
    
    /**
     * @return the serviceCode
     */
    public String getServiceCode(String serviceCode) {
        return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
     
    /**
     * Performs the validation
     * @param parameter Parameter name
     * @param value Parameter value
     * @param parameterDefinition Parameter definition
     * @throws ServiceInvocationException 
     */
    public  void validate(String parameter, Object value, HashMap<String,Object> parameterDefinition) throws ServiceInvocationException{   
        
        try {
           validateWork(parameter, value, parameterDefinition);
           //if (true)  throw new BadParameterServiceInvocationException(this.serviceCode, parameter, value);
           //else throw new Exception("lalalla");
        }
        catch(ServiceInvocationException sie){
            throw sie;
        }
        catch (Exception e){
            throw new ServiceInvocationException(serviceCode, e);
        }       
    }
        
    /**
     * Validates (real work)
     * @param parameter Parameter name
     * @param value Parameter value
     * @param parameterDefinition
     * @throws Exception Parameter definition
     */
    public void validateWork(String parameter, Object value, HashMap<String,Object> parameterDefinition) throws Exception {
        initValidationData(parameterDefinition);
        
        if ( (value != null) && (!validateType(value)) ){
            throw new BadParameterTypeException(this.serviceCode, parameter, value, value.getClass().getName());
        }
        
        if ((this.required) && ((value == null) || (value.toString().trim().equals(""))) ){
            throw new ParameterRequiredException(this.serviceCode, parameter);
        }
        
        if ((value != null) && (this.regexp != null)){
            validateAllRegexp(this.regexp, value.toString(), parameter, value);                        
        }
        
        if ((value != null) && (this.minLength != null) && (value.toString().length() < this.minLength)){
            throw new ParameterTooShortException(this.serviceCode, parameter, value);
        }
        
        if ((value != null) && (this.maxLength != null) && (value.toString().length() > this.maxLength)){
            throw new ParameterTooLongException(this.serviceCode, parameter, value);
        }
        
    }
    
    /**
     * Initializes the validation data
     * @param parameterDefinition
     */
    private void initValidationData(HashMap<String,Object> parameterDefinition){
        this.type = "string";
        this.required = false;
        this.regexp = null;
        this.minLength = null;
        this.maxLength = null;
        
        if ( parameterDefinition.containsKey("type") ){
            this.type = parameterDefinition.get("type").toString();
        }
        
        if ( parameterDefinition.containsKey("required") ){
            this.required = (Boolean) parameterDefinition.get("required");
        } 
        
        if ( parameterDefinition.containsKey("regexp") ){
            Object o = parameterDefinition.get("regexp");            
            if (o instanceof List){
                this.regexp = new java.util.ArrayList<String>();
                for (Object oRegexp : (List ) o){
                    this.regexp.add(phpRegexpToJavaRegexp(oRegexp.toString()));
                }                
            }
            else {
                this.regexp = new java.util.ArrayList();
                this.regexp.add(phpRegexpToJavaRegexp(o.toString()));
            }                          
        }
        
        if ( parameterDefinition.containsKey("minlen") ){
            this.minLength = (Integer) parameterDefinition.get("minlen");
        }
        
        if ( parameterDefinition.containsKey("maxlen") ){
            this.maxLength = (Integer) parameterDefinition.get("maxlen");
        }                        
    }
    
    /**
     * Converts the regular expression into a valid java format
     * @param phpRegexp Regular expression
     * @return Java compatible regular expression
     */
    private String phpRegexpToJavaRegexp(String phpRegexp){        
        String jregexp = phpRegexp;
        
        if ( (phpRegexp != null) && (phpRegexp.length() > 1) && (phpRegexp.charAt(0) == '/') && (phpRegexp.charAt(phpRegexp.length() - 1) == '/') ){
            jregexp = phpRegexp.substring(1, phpRegexp.length() - 1);
        }
        
        return jregexp;
    }
    
    /**
     * Validates the parameter value's type
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateType(Object data){
        boolean valid = false;
        
        if(type.equals("boolean")) valid = validateTypeBoolean(data); 
        else if(type.equals("integer")) valid = validateTypeInteger(data); 
        else if(type.equals("float")) valid = validateTypeFloat(data);
        else if(type.equals("double")) valid = validateTypeDouble(data);
        else if(type.equals("string")) valid = true;
        else if(type.equals("array")) valid = validateTypeArray(data);
        else if(type.equals("object")) valid = true;
        else if(type.equals("email")) valid = validateTypeEmail(data);
        else if(type.equals("datetime")) validateTypeDatetime(data);
                
        return valid;
    }
    
    /**
     * Boolean parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeBoolean(Object data){
        return data instanceof Boolean;
    }
    
    /**
     * Integer parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeInteger(Object data){
        boolean valid = false;

        valid = data instanceof Boolean;

        if (!valid) {
            try {
                Integer.parseInt(data.toString());
                valid = true;
            } catch (NumberFormatException nfe) {
            }
        }

        return valid;
    }
    
    /**
     * FLoat parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeFloat(Object data){
        boolean valid = data instanceof Float;

        if (!valid) {
            try {
                Float.parseFloat(data.toString());
                valid = true;
            } catch (NumberFormatException nfe) {}
        }

        return valid;
    }
    
    /**
     * Double parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeDouble(Object data){
        boolean valid = data instanceof Double;

        if ((!valid) && (validateTypeFloat(data))){
            valid = true;
        }
        else if (!valid) {
            try {
                Float.parseFloat(data.toString());
                valid = true;
            } catch (NumberFormatException nfe) {}
        }

        return valid;
    }
    
    /**
     * Array parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeArray(Object data){
        return ((data instanceof Object[]) || (data instanceof java.util.List) || (data instanceof java.util.HashMap));
    }
    
    /**
     * Email parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeEmail(Object data){                
        return validateRegexp(java.util.regex.Pattern.compile(".+@.+\\.[a-z]+"), data.toString());
    }
    
    /**
     * Validates that a parameter pass all the regular expressions
     * @param regexps
     * @param expression
     * @param parameter
     * @param value
     * @throws ParameterInvalidRegexpFormatException 
     */
    private void validateAllRegexp(List<String> regexps, String expression, String parameter, Object value) throws ParameterInvalidRegexpFormatException{        
        for (String regexp : regexps){
            Pattern p = java.util.regex.Pattern.compile(regexp);
                if ( ! validateRegexp(p, expression) ){
                    throw new ParameterInvalidRegexpFormatException(this.serviceCode, parameter, value, regexp);
                }
        }                
    }
    
    /**
     * Validates the expression againsta regular expression
     * @param p Pattern to check
     * @param expression Expression to validate
     * @return True if is valid
     */
    private boolean validateRegexp(Pattern p, String expression){
        java.util.regex.Matcher m = p.matcher(expression);
        return m.matches();
    }
    
    /**
     * Date time  parameter data type validation
     * @param data Data to validate
     * @return True if is valid
     */
    private boolean validateTypeDatetime(Object data){
        boolean valid = data instanceof java.util.Date;
        
        if (!valid){
            try {
                getDateFormater().parse(data.toString());
                valid = true;
            } catch (ParseException ex) {}
        }
        
        return valid;
    }
    
    /**
     * Gets the date formatter
     * @return Api date formater
     */
    private SimpleDateFormat getDateFormater(){
        if (this.sdf == null){
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        
        return this.sdf;
    }            
}
