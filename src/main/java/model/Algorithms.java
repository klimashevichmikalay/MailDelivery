package model;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mikalay
 */
public class Algorithms {

    public Vector<String> setTemplates(MessageInfo tableHeader, Vector<MessageInfo> messages, String text) {

        Vector<String> res = new Vector<String>();
        for (int i = 0; i < messages.size(); i++) {

            if (!messages.get(i).isSizeGood()) {
                res.add(null);
                continue;
            }
            res.add(replaceAllVar(text, tableHeader, messages.get(i)));
        }
        return res;
    }

    String replaceAllVar(String text, MessageInfo tableHeader, MessageInfo rowTable) {
        for (int i = 0; i < tableHeader.getSize(); i++) {
            text = replaceVarTo(text, tableHeader.getAt(i), rowTable.getAt(i));
        }
        return text;
    }

    String replaceVarTo(String text, String var, String replaceTo) {
        Pattern pattern = Pattern.compile("<\\s*var\\s*>\\s*" + var + "\\s*<\\s*/\\s*var\\s*>");
        Matcher m = pattern.matcher(text);
        return (m.replaceAll(replaceTo));
    }
}
