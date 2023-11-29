package generationMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IOEventModel {
    private String id;
    private String name;
    private String applicationName;
    private String flowName;
    private String generalTopic;
    private String className;
    private String methodName;
    private String type;
    private String topic;
    private String returnType;
    private List<IOLink> inputTopics;
    private List<IOLink> outputTopics;
}
