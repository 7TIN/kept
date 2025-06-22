package com.dev.kept.util;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

// import java.beans.BeanInfo;
// import java.beans.IntrospectionException;
// import java.beans.Introspector;
// // import java.beans.*;
// import java.lang.reflect.*;
// import java.util.Arrays;

public class UpdateUtil {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanWrapper srcWrapper = new BeanWrapperImpl(source);
        BeanWrapper trgWrapper = new BeanWrapperImpl(target);

        for (PropertyDescriptor pd : srcWrapper.getPropertyDescriptors()) {
            String propName = pd.getName();
            Object value = srcWrapper.getPropertyValue(propName);

            if (value != null && trgWrapper.isWritableProperty(propName)) {
                trgWrapper.setPropertyValue(propName, value);
            }
        }
    }
}
