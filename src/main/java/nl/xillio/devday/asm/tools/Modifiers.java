package nl.xillio.devday.asm.tools;

import org.objectweb.asm.Opcodes;

import java.util.StringJoiner;

public class Modifiers {
    public static String convertModifierBitmapToString(int bitmap) {
        StringJoiner j = new StringJoiner(" ").setEmptyValue("(package-private)");

        for (int remaining = bitmap, bit; remaining != 0; remaining -= bit) {
            bit = Integer.lowestOneBit(remaining);
            switch (bit) {
                case Opcodes.ACC_PUBLIC:
                    j.add("public");
                    break;
                case Opcodes.ACC_PRIVATE:
                    j.add("private");
                    break;
                case Opcodes.ACC_PROTECTED:
                    j.add("protected");
                    break;
                case Opcodes.ACC_STATIC:
                    j.add("static");
                    break;
                case Opcodes.ACC_FINAL:
                    j.add("final");
                    break;
                case Opcodes.ACC_SYNCHRONIZED:
                    j.add("synchronzied");
                    break;
                case Opcodes.ACC_BRIDGE:
                    j.add("(bridge)");
                    break;
                case Opcodes.ACC_VARARGS:
                    j.add("(varargs)");
                    break;
                case Opcodes.ACC_NATIVE:
                    j.add("native");
                    break;
                case Opcodes.ACC_ABSTRACT:
                    j.add("abstract");
                    break;
                case Opcodes.ACC_STRICT:
                    j.add("strictfp");
                    break;
                case Opcodes.ACC_SYNTHETIC:
                    j.add("synthetic");
                    break;
            }
        }

        return j.toString();
    }
}
