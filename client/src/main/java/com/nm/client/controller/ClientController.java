package com.nm.client.controller;

import com.nm.client.beans.PatientBean;
import com.nm.client.exception.PatientBadRequestException;
import com.nm.client.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {
    private final PatientProxy patientProxy;

    public ClientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }


    @RequestMapping("/")
    public String accueil(Model model) {

        return "Accueil";
    }

    /*
    @GetMapping("/{firstName}/{lastName}")
    public String get(@PathVariable String firstName, @PathVariable String lastName, Model model) {
        PatientBean patient = patientProxy.get(firstName, lastName);

        model.addAttribute("patient", patient);

        return "Accueil";
    };
*/

    @GetMapping("/{firstName}/{lastName}")
    public String get(@PathVariable String firstName, @PathVariable String lastName, Model model) {
        try {
            PatientBean patient = patientProxy.get(firstName, lastName);
            model.addAttribute("patient", patient);
        }
        catch (PatientBadRequestException e) {

        }

        /*
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line = reader.readLine();

				while (line != null) {
					symptomsList.add(line);
					line = reader.readLine();
				}

			} catch (IOException e) {
				System.err.println("Error while reading file " + e.getMessage() + " caused by " + e.getCause());
			}


        PatientBean patient = new PatientBean();
        patient.setIdPatient(1);
        patient.setFirstName("Test");
        patient.setBirthDate("1955-10-01");
         */

        finally {
            return "Accueil";
        }
    };

    //? duplicat°code ? Même "/" que accueil ? / Att° put uniq t address and tel (dans thyme leaf et test). Tester non nul
    @PutMapping("/")
    public String put(@RequestBody PatientBean patientBean, Model model) {
        patientProxy.update(patientBean);
        try {
            PatientBean patient = patientProxy.get(patientBean.getFirstName(), patientBean.getLastName());
            model.addAttribute("patient", patient);
        }
        catch (PatientBadRequestException e) {

        }
        finally {
            return "Accueil";
        }
    }




}
