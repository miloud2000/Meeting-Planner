package meeting.palnner.controller;
import meeting.palnner.entity.Equipment;
import meeting.palnner.serviceInterface.EquipmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {
	@Autowired
    EquipmentInterface equipmentService;
	

    
    @PostMapping("/addequipment")
    public String addEquipment(@RequestBody Equipment equipment){
    	equipmentService.addEquipment(equipment);
         return "Equipment added successfully";
    }

}
