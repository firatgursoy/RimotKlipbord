package com.gnosis.rimotklipbord.RimotKlipbord;

import io.vertx.core.Vertx;

import java.util.logging.Logger;

public class RimotServer {
    Logger logger = Logger.getLogger("RimotServer");
    private static int SERVER_PORT = 666;
    ClipboardOwnerImpl clipboardOwnerImpl;

    RimotServer() throws Exception {
        start();
    }

    public void start() throws Exception {
        Vertx.vertx().createNetServer().connectHandler(netSocket -> {
            logger.info("Connected: " + netSocket.remoteAddress().host());
            netSocket.handler(buffer -> {
                String message = buffer.toString();
                logger.info("Message from " + netSocket.remoteAddress().host() + " [" + message + "]");
                clipboardOwnerImpl = new ClipboardOwnerImpl();
                clipboardOwnerImpl.setClipboardContents(message);
            });
        }).listen(SERVER_PORT, netServerAsyncResult -> {
            if (netServerAsyncResult.succeeded()) {
                logger.info("Listening: " + SERVER_PORT);
            }
        });
    }
}
