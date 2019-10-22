package nl.xillio.devday.asm.event;

import nl.xillio.devday.asm.AsmConfiguration;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class ClassAdapter extends ClassVisitor {
    public ClassAdapter(ClassWriter classWriter) {
        super(AsmConfiguration.languageLevel, classWriter);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.contains("autoImplement")) {
            MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
            return new ImplementingMethodVisitor(methodVisitor);
        } else {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }
}
