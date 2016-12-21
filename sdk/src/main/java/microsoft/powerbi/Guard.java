package microsoft.powerbi;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class Guard {
    
    public static void ValidateString(String value, String paramName) {

        if (value == null) {
            throw new ArgumentNullException(paramName);
        }

        if (StringUtils.isBlank(value)) {
            throw new ArgumentException(paramName);
        }
    }

    public static void ValidateGuid(UUID value, String paramName) {

        if (value == null) {
            throw new ArgumentException(paramName);
        }
    }

    public static void ValidateObjectNotNull(Object value, String paramName) {
        
        if (value == null) {
            throw new ArgumentNullException(paramName);
        }
    }
}