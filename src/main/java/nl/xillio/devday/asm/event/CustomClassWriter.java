package nl.xillio.devday.asm.event;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class CustomClassWriter {
    static String className = "java.lang.Integer";
    static String cloneableInterface = "java/lang/Cloneable";
    private ClassReader reader;
    private ClassWriter writer;

    public CustomClassWriter() throws IOException {
        reader = new ClassReader(className);
        writer = new ClassWriter(reader, 0);
    }


}
