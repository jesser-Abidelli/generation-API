package generationMS.annotation;

import com.ioevent.starter.annotations.InputEvent;
import com.squareup.javapoet.AnnotationSpec;

public class InputEventAnnotationBuilder {

    public AnnotationSpec.Builder builder;

    public InputEventAnnotationBuilder(){
        this.builder = AnnotationSpec.builder(InputEvent.class);
    }
}
