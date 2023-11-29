package generationMS.annotation;

import com.ioevent.starter.annotations.OutputEvent;
import com.squareup.javapoet.AnnotationSpec;

public class OutputEventAnnotationBuilder {

    public AnnotationSpec.Builder builder;

    public OutputEventAnnotationBuilder(){
        this.builder = AnnotationSpec.builder(OutputEvent.class);
    }
}
