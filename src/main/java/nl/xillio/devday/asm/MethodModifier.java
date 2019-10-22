package nl.xillio.devday.asm;

import nl.xillio.devday.asm.event.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import static org.objectweb.asm.ClassReader.SKIP_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;

public class MethodModifier extends ClassLoader {
    private static final String className = "nl.xillio.devday.asm.stabulary.SimpleClass";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader loader = new MethodModifier();
        Class<?> targetClass = loader.loadClass(className);

        Object x = targetClass.newInstance();

        targetClass.getDeclaredMethod("autoImplementMethodPlease").invoke(x);
    }

    @Override
    public synchronized Class<?> loadClass(String fqn) throws ClassNotFoundException {
        if (!className.equals(fqn)) {
            return super.loadClass(fqn);
        }

        InputStream sourceStream = readClass(fqn);
        ClassWriter classWriter;

        try {
            classWriter = modifyClass(sourceStream);
        } catch (IOException e) {
            throw new ClassNotFoundException("Could not modify the bytecode", e);
        }

        byte[] classBytes = classWriter.toByteArray();

        try {
            FileOutputStream fileOut = new FileOutputStream("/home/xillio/xxx.class");

            fileOut.write(classBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(fqn, classBytes, 0, classBytes.length);
    }

    private InputStream readClass(String fqn) {
        String sourcePath = fqn.replace('.', '/') + ".class";
        InputStream sourceStream = getResourceAsStream(sourcePath);

        return sourceStream;
    }

    private ClassWriter modifyClass(InputStream sourceStream) throws IOException {
        ClassReader classReader = new ClassReader(sourceStream);
        ClassWriter classWriter = new ClassWriter(COMPUTE_FRAMES);

        ClassVisitor classVisitor = new ClassAdapter(classWriter);

        // We can skip visiting the frames because we will regenerate them in the class writer
        classReader.accept(classVisitor, SKIP_FRAMES);

        return classWriter;
    }
}
