package com.siatel.scripting.framework;

import com.entity.UserImp;
import com.entity.GroupImp;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * SiatelFactory mimics the Gargantua Factory for Rhino scripting.
 * It allows creating concrete implementations of Entities based on prefixes.
 */
public class SiatelFactory {

    public SiatelFactory() {
    }

    /**
     * JS call: var obj = SiatelFactory.newObject(idOrPref)
     */
    public Object newObject(String idOrPref) {
        if (idOrPref == null)
            return null;

        String prefix = idOrPref.substring(0, Math.min(idOrPref.length(), 4)).toUpperCase();

        switch (prefix) {
            case "USER":
                return new UserImp(idOrPref, "");
            case "GROU":
                return new GroupImp(idOrPref, "");
            default:
                System.out.println("[SiatelFactory] Prefix inconnu : " + prefix);
                return null;
        }
    }

    /**
     * JS call: var obj = SiatelFactory.newLabeledObject(prefix, label)
     */
    public Object newLabeledObject(String prefix, String label) {
        if (prefix == null)
            return null;

        String upperPrefix = prefix.toUpperCase();

        if (upperPrefix.startsWith("USER")) {
            return new UserImp(prefix + "_" + System.currentTimeMillis(), label);
        } else if (upperPrefix.startsWith("GROU")) {
            return new GroupImp(prefix + "_" + System.currentTimeMillis(), label);
        }

        return null;
    }
}
