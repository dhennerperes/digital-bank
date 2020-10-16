package com.zup.dhennerperes.digitalbank.exception;

import com.zup.dhennerperes.digitalbank.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class ApplicationException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public ApplicationException(PropertiesConfig propertiesConfig) {
        ApplicationException.propertiesConfig = propertiesConfig;
    }

    /**
     * Returns new RuntimeException based on template and args
     *
     * @param messageTemplate
     * @param args
     * @return
     */
    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, String id, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id);
        return throwException(exceptionType, messageTemplate, args);
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType, messageTemplate and args
     *
     * @param entityType
     * @param exceptionType
     * @param messageTemplate
     * @param args
     * @return
     */
    public static RuntimeException throwExceptionWithTemplate(EntityType entityType, ExceptionType exceptionType, String messageTemplate, String... args) {
        return throwException(exceptionType, messageTemplate, args);
    }

    /**
     * Returns new RuntimeException based on template and args
     *
     * @param messageTemplate
     * @param args
     * @return
     */
    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        } else if (ExceptionType.NOT_PERMISSION.equals(exceptionType)) {
            return new NotPermissionException(format(messageTemplate, args));
        } else if (ExceptionType.ENTITY_INVALID.equals(exceptionType)) {
            return new EntityInvalidException(format(messageTemplate, args));
        } else if (ExceptionType.FORBIDDEN.equals(exceptionType)) {
            return new ForbiddenException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), args);
        }
        return String.format(template, args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -6972518443557218155L;

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        private static final long serialVersionUID = -4812481910640824407L;

        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class NotPermissionException extends RuntimeException {
        private static final long serialVersionUID = 7869772173394867085L;

        public NotPermissionException(String message) {
            super(message);
        }
    }

    public static class EntityInvalidException extends RuntimeException {
        private static final long serialVersionUID = -5783530194263503429L;

        public EntityInvalidException(String message) {
            super(message);
        }
    }

    public static class ForbiddenException extends RuntimeException {
        private static final long serialVersionUID = 596193626963762480L;

        public ForbiddenException(String message) {
            super(message);
        }
    }

}
