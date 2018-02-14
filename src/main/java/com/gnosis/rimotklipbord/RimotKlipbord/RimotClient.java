package com.gnosis.rimotklipbord.RimotKlipbord;

import io.vertx.core.Vertx;
import io.vertx.core.net.SocketAddress;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by GNO on 14.02.2018.
 */
public class RimotClient {
    private String HOST;
    private final int HOST_PORT = 666;
    private ClipboardOwnerImpl clipboardOwnerImpl;
    Logger logger = Logger.getLogger("RimotClient");

    public RimotClient(String host) throws Exception {
        this.HOST = host;
        start();
    }

    public void start() throws Exception {
        Vertx.vertx()
            .createNetClient()
            .connect(SocketAddress.inetSocketAddress(HOST_PORT, HOST),
                netSocketAsyncResult -> {
                    if (netSocketAsyncResult.succeeded()) {
                        netSocketAsyncResult.result().write("HELLO MF!");
                        clipboardOwnerImpl = new ClipboardOwnerImpl();
                        Toolkit.getDefaultToolkit().getSystemClipboard().addFlavorListener(e -> {
                            String content = clipboardOwnerImpl.getClipboardContents();
                            System.out.println("ClipBoard UPDATED: " + content);
                            netSocketAsyncResult.result().write(content);
                        });
                        logger.warning("Connect success to " + HOST);
                    } else {
                        logger.warning("Connect failed to " + HOST);
                    }

                });

    }

}
