package org.springframework.samples.petclinic.util;

import org.springframework.lang.Nullable;

/**
 * @@@EFFECTIVE@@@ ITEM 4
 */
public class Strings {

    public static boolean hasLength(@Nullable String str) {
        return str != null && !str.isEmpty();
    }

    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            int beginIndex = 0;

            int endIndex;
            for(endIndex = str.length() - 1; beginIndex <= endIndex && Character.isWhitespace(str.charAt(beginIndex)); ++beginIndex) {
                ;
            }

            while(endIndex > beginIndex && Character.isWhitespace(str.charAt(endIndex))) {
                --endIndex;
            }

            return str.substring(beginIndex, endIndex + 1);
        }
    }

}
