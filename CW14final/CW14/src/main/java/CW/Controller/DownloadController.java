package CW.Controller;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadController {

    private final Path statsFilePath = Paths.get("src\\main\\resources\\csvfiles\\");

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getFile(
            @RequestParam(value = "filename",
                    required = true) String filename
    ) throws FileNotFoundException {

        File file = new File(statsFilePath.toString() + "\\" + filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                         .contentType(MediaType.APPLICATION_OCTET_STREAM)
                         .contentLength(file.length())
                         .body(resource);
    }
}
