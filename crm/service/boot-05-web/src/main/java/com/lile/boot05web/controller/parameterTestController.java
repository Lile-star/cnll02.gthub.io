package com.lile.boot05web.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class parameterTestController {

    @GetMapping("/car/{id}/owner/{name}")
    public Map<String,Object> getCar(@PathVariable Integer id,
                                     @PathVariable String name,
                                     @PathVariable Map pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("height") Double height,
                                     @RequestParam Map pp){
        Map<String,Object> map=new HashMap<>();
//        map.put("id", id);
//        map.put("name", name);
//        map.put("pv", pv);
//        map.put("userAgent", userAgent);
//        map.put("header", header);
        map.put("age", age);
        map.put("height", height);
        map.put("pp",pp);
return map;
    }

    @PostMapping("/body")
    public Map<String,Object> getBody(@RequestBody String x1){
        Map map=new HashMap();
        map.put("x1", x1);
        return map;
    }

    @GetMapping("/cars/{path}")
    public Map carShell(@MatrixVariable("low")Integer low,
                        @MatrixVariable("brand")List<String> brand,
                        @PathVariable("path") String path){
        Map<String,Object>map=new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

}
