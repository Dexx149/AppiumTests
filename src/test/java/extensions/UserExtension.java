package extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import models.UserData;

public class UserExtension implements ParameterResolver {

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserType {
        Type value() default Type.VALID;

        enum Type {
            VALID, INVALID
        }

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        return parameterContext.getParameter().getType().equals(UserData.class)
                && parameterContext.getParameter().isAnnotationPresent(UserType.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        UserType annotation = parameterContext.getParameter().getAnnotation(UserType.class);
        UserType.Type userType = annotation.value();

        return switch (userType) {
            case VALID -> UserData.validUser();
            case INVALID -> UserData.invalidUser();
        };
    }
}
