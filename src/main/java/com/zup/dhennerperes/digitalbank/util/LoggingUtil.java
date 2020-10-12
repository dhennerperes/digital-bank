package com.zup.dhennerperes.digitalbank.util;

public class LoggingUtil {

    public static String getOkLoggingMessage(String classAuthor, String methodAuthor, String... parameterValues) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String requesterUsername = (String) auth.getPrincipal();

        String result = "DareDevil : " + classAuthor + "." + methodAuthor;
        String resultSuffix = "(-:-) : getOkLoggingMessage wrong number of parameters";
        if (parameterValues.length % 2 == 0) {
            StringBuilder meatString = new StringBuilder();
            for (int i = 0; i < parameterValues.length - 1; i += 2) {
                meatString.append(parameterValues[i]).append(": ").append(parameterValues[i + 1]);
                if (i < parameterValues.length - 2) {
                    meatString.append(", ");
                }
            }
            if (meatString.toString().equals("")) meatString = new StringBuilder("-:-");
            resultSuffix = "(" + meatString + ") : Ok";
        }
        return result + resultSuffix;
    }

}
