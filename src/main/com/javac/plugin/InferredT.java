package com.javac.plugin;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER, ElementType.TYPE}) 
public @interface InferredT {
  
}
