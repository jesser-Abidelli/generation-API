package generationMS.annotation;

import com.ioevent.starter.annotations.IOEvent;
import com.squareup.javapoet.AnnotationSpec;

public class IOEventAnnotationBuilder {

    public AnnotationSpec.Builder builder;

    public IOEventAnnotationBuilder(){
        this.builder = AnnotationSpec.builder(IOEvent.class);
    }
}
