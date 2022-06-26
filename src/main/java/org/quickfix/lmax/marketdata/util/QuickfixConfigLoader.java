package org.quickfix.lmax.marketdata.util;

import java.time.LocalDateTime;

public class QuickfixConfigLoader {

    public static void main(String[] args) {
        String javaVersion = Runtime.version().toString();
        String time = LocalDateTime.now().toString();
        System.out.println("********\nBuild Time: " + time
                + "\nJava Version: " + javaVersion + "\n********");
    }

}
