package com.gnosis.rimotklipbord.RimotKlipbord;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Created by GNO on 14.02.2018.
 */
public class ClipboardOwnerImpl implements ClipboardOwner {
    public void setClipboardContents(String aString) {
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }

    public String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =
            (contents != null) &&
                contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
