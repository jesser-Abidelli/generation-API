package generationMS.annotation;

import com.ioevent.starter.annotations.IOFlow;
import com.squareup.javapoet.AnnotationSpec;

public class IOFlowAnnotationBuilder {

    public AnnotationSpec.Builder builder;

    public IOFlowAnnotationBuilder(){
        this.builder = AnnotationSpec.builder(IOFlow.class);
    }
}
