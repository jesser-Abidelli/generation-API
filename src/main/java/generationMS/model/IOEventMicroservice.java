package generationMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IOEventMicroservice {
    private String name;
    private List<IOEventClass> ioEventClasses;
}
