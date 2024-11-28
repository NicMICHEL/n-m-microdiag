package com.nm.client.proxies;

import com.nm.client.beans.PatientBean;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "patient", url = "localhost:9001")
public interface PatientProxy {

    @GetMapping("/patientFile/{firstName}/{lastName}")
    PatientBean get(@PathVariable String firstName, @PathVariable String lastName);

    // fonctionne donne 201 et uri mais pas JSON updated
    @PutMapping("/patientFile")
     ResponseEntity<PatientBean> update(@Valid @RequestBody PatientBean patientBean);

}
