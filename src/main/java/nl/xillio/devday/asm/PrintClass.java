package nl.xillio.devday.asm;

import nl.xillio.devday.asm.event.ClassPrinter;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class PrintClass {
    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("nl.xillio.devday.asm.stabulary.OuterSpecimen");
        cr.accept(cp, 0);
    }
}
