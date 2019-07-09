package nl.xillio.devday.asm.event;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class FieldAdderAdapter extends ClassVisitor {
    private String fieldName;
    private String fieldDefault;
    private int access = org.objectweb.asm.Opcodes.ACC_PUBLIC;
    private boolean isFieldPresent;

    public FieldAdderAdapter(String fieldName, int fieldAccess, ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
        this.fieldName = fieldName;
        this.access = fieldAccess;
    }
}
