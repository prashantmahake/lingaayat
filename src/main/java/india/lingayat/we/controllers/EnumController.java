package india.lingayat.we.controllers;

import india.lingayat.we.models.enums.BloodGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnumController {

    @GetMapping("/enumValues")
    @PreAuthorize("hasRole('USER')")
    public Map<String, String> getEnumValues(@RequestParam String enumName) {

//       switch (enumName){
//           case "BloodGroup":
//               Map<String, String> result = new HashMap<>();
//               for(BloodGroup b : BloodGroup.values()){
//                   result.put(b.toString(), b.getStringValue());
//               }
//               return result;
//       }

        return null;
    }
}