package com.gnosis.rimotklipbord.RimotKlipbord;

import io.vertx.core.Verticle;

import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GNO on 14.02.2018.
 */
public class RimotKlipbord {
    private static Logger logger = Logger.getLogger("RimotServer");

    private static void logInvalidStartParametersMessage() {
        logger.log(Level.WARNING, "Invalid start parameters. \nEg. java -jar RimotKlipbord.jar c 127.0.0.1 #for client\n    java -jar RimotKlipbord.jar s #for server");
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            logInvalidStartParametersMessage();
        } else if (args.length == 1) {
            String arg1 = args[0];
            if (arg1.equals("s")) {
                try {
                    RimotServer rimotServer = new RimotServer();
                } catch (Exception e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            } else {
                logInvalidStartParametersMessage();
            }
        } else if (args.length == 2) {
            String arg1 = args[0];
            String host = args[1];
            if (arg1.equals("c")) {
                try {
                    RimotClient rimotClient = new RimotClient(host);
                } catch (Exception e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            } else {
                logInvalidStartParametersMessage();
            }
        }
    }
}
