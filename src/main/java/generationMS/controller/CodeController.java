package generationMS.controller;

import generationMS.model.IOEventModel;
import generationMS.service.SnippetGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ioevent/studio/generation/api")
public class CodeController {
    @Autowired
    SnippetGenerationService snippetGenerationService;

    @GetMapping("/snippet")
    String generateSnippet(@RequestBody IOEventModel ioEventModel){
        return snippetGenerationService.generateModelSnippet(ioEventModel);
    }

    @GetMapping("/ping")
    public Date ping(){
        return new Date();
    }

}
