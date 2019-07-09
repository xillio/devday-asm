package nl.xillio.devday.asm.stabulary;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AnnotationSpecimen
public class OuterSpecimen<@TypeAnnotationSpecimen T extends OuterSpecimen<@TypeAnnotationSpecimen T>> implements @TypeAnnotationSpecimen Serializable, @TypeAnnotationSpecimen MarkerInterfaceSpecimen {
    private static final int outerInt = 33;
    private List<Map<String, String>> outerField = Collections.emptyList();

    public int publicOuterMethod() {
        return 0;
    }

    private int privateOuterMethod() {
        return 1;
    }

    private class InnerSpecimen {
        public int publicInnerMethod() {
            return 2;
        }
    }
}
