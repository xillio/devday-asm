package nl.xillio.devday.asm.event;

import nl.xillio.devday.asm.AsmConfiguration;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ImplementingMethodVisitor extends MethodVisitor {
    public ImplementingMethodVisitor(MethodVisitor methodVisitor) {
        super(AsmConfiguration.languageLevel, methodVisitor);
    }

    @Override
    public void visitCode() {
        Label label0 = new Label();
        mv.visitLabel(label0);
        mv.visitLineNumber(17, label0);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Synthetic log line!");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        Label label1 = new Label();
        mv.visitLabel(label1);
        mv.visitLineNumber(18, label1);
        mv.visitInsn(RETURN);
        Label label2 = new Label();
        mv.visitLabel(label2);
        mv.visitLocalVariable("this", "Lnl/xillio/devday/asm/event/ImplementingMethodVisitor;", null, label0, label2, 0);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }
}
