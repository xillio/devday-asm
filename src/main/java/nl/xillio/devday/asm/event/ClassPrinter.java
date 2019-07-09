package nl.xillio.devday.asm.event;

import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

import static nl.xillio.devday.asm.tools.Modifiers.convertModifierBitmapToString;
import static nl.xillio.devday.asm.tools.TypeReferences.convertTypeReferenceSort;
import static org.objectweb.asm.Opcodes.ASM7;

public class ClassPrinter extends ClassVisitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPrinter.class);

    public ClassPrinter() {
        super(ASM7);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        LOGGER.info(convertModifierBitmapToString(access) + " class " + name + " extends " + superName + " implements " + Arrays.stream(interfaces).collect(Collectors.joining(", ")));
    }

    @Override
    public void visitSource(String source, String debug) {
        LOGGER.info("Source: " + source);
        LOGGER.info("Debug " + debug);
    }

    // not invoked: JVM9
    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        LOGGER.info("Module: " + name + " - " + convertModifierBitmapToString(access));

        return super.visitModule(name, access, version);
    }

    // not invoked: JVM 11
    @Override
    public void visitNestHost(String nestHost) {
        LOGGER.info("Nest host: " + nestHost);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        LOGGER.info("Outer Class: " + owner + "." + name + "." + descriptor);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        LOGGER.info("Inner class: name=" + name + ", outerName=" + outerName + ", innerName=" + innerName + ". Access: " + convertModifierBitmapToString(access));
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        LOGGER.info("Annotation: " + descriptor + ", visible=" + visible);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        TypeReference typeReference = new TypeReference(typeRef);

        LOGGER.info("Type Annotation " + convertTypeReferenceSort(typeReference.getSort()) + typeReference.getSuperTypeIndex() +
                ", path=" + typePath + ", desc=" + descriptor + ", visible=" + visible);

        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        LOGGER.info("Field: " + convertModifierBitmapToString(access) + " " + name + ", descriptor=" + descriptor + ", signature=" + signature + ", value=" + value);
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }
}
