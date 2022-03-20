package happy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class FormController {

    @GetMapping("/form")
    public String test01(String password,String email){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String test02(String password, String email, MultipartFile header,MultipartFile[] photos) throws IOException {
        //相当于request.parameter();拿到上传文件名
        if(! header.isEmpty()) {
            String value = header.getOriginalFilename();
            System.out.println(value);
            header.transferTo(new File("G:\\springboot\\"+value));
        }
        if(photos.length > 0){
            for(MultipartFile m1: photos){
                if(! m1.isEmpty()){
                    String value = m1.getOriginalFilename();
                    m1.transferTo(new File("G:\\springboot\\"+value));
                }
            }
        }

        log.info("header={}",header.getSize(),"photos={}",photos.length);
        return "main";



    }




}
