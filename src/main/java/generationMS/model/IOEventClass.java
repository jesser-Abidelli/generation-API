package generationMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IOEventClass {
    private String className;
    private String flowName;
    private String generalTopic;
    private List<IOEventModel> ioEventMethods;
}
