package generationMS.service;

import com.squareup.javapoet.*;
import generationMS.annotation.IOEventAnnotationBuilder;
import generationMS.annotation.IOFlowAnnotationBuilder;
import generationMS.annotation.InputEventAnnotationBuilder;
import generationMS.annotation.OutputEventAnnotationBuilder;
import generationMS.model.IOEventModel;
import generationMS.model.IOLink;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;

@Service
public class SnippetGenerationServiceImpl implements SnippetGenerationService{

    public String generateModelSnippet(IOEventModel ioEventModel){

        IOEventAnnotationBuilder ioEventAnnotationBuilder = new IOEventAnnotationBuilder();

        AnnotationSpec.Builder builder = ioEventAnnotationBuilder.builder;

        //adding ioevent annotation key
        builder.addMember("key","$S",ioEventModel.getName());


        CodeBlock.Builder inputEventArrayBuilder = CodeBlock.builder().add("{\n");
        for(IOLink ioLink : ioEventModel.getInputTopics()){

            InputEventAnnotationBuilder inputFlowAnnotationBuilder = new InputEventAnnotationBuilder();
            AnnotationSpec.Builder inputEventBuilder = inputFlowAnnotationBuilder.builder;

            AnnotationSpec annotationSpec = inputEventBuilder.addMember("key","$S",ioLink.getKey())
                    .addMember("topic","$S",ioLink.getTopic()).build();

            inputEventArrayBuilder.add("$L ,",annotationSpec);
        }
        inputEventArrayBuilder.add("\n}");

        CodeBlock.Builder outputEventArrayBuilder = CodeBlock.builder().add("{\n");
        for(IOLink ioLink : ioEventModel.getOutputTopics()){

            OutputEventAnnotationBuilder outputEventAnnotationBuilder = new OutputEventAnnotationBuilder();
            AnnotationSpec.Builder outputEventBuilder = outputEventAnnotationBuilder.builder;

            AnnotationSpec annotationSpec = outputEventBuilder.addMember("key","$S",ioLink.getKey())
                    .addMember("topic","$S",ioLink.getTopic()).build();

            outputEventArrayBuilder.add("$L ,",annotationSpec);
        }
        outputEventArrayBuilder.add("\n}");

        //adding inputEvent Annotations
        builder.addMember("input",inputEventArrayBuilder.build());

        //adding outputEvent Annotations
        builder.addMember("output",outputEventArrayBuilder.build());

        MethodSpec ioeventMethod = MethodSpec
                .methodBuilder(ioEventModel.getMethodName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(builder.build())
                .returns(ioEventModel.getReturnType().getClass())
                .build();

        IOFlowAnnotationBuilder ioFlowAnnotationBuilder = new IOFlowAnnotationBuilder();
        AnnotationSpec.Builder iOFlowannotation = ioFlowAnnotationBuilder.builder;
        iOFlowannotation.addMember("name","$S",ioEventModel.getFlowName())
                .addMember("topic","$S",ioEventModel.getGeneralTopic());

        TypeSpec test = TypeSpec.classBuilder(ioEventModel.getClassName())
                .addAnnotation(iOFlowannotation.build())
                .addMethod(ioeventMethod).build();

        JavaFile javaFile = JavaFile.builder("com.example.test",test)
                .build();

        
        return javaFile.toString();
    }

}
