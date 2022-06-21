package com.athanasiosk.FoodFinder.Owner;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {

    private final OwnerRepo ownerRepo;

    OwnerController(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    // GET all owners
    @GetMapping("/api/v1/owners")
    List<Owner> all() {
        return ownerRepo.findAll();
    }

    // POST Add a new Owner
    @PostMapping("/api/v1/owners")
    Owner newOwner(@RequestBody Owner newOwner) {
        return ownerRepo.save(newOwner);
    }

    // GET Find a single Owner by the Id
    @GetMapping("/api/v1/owners/{id}")
    Owner oneOwner(@PathVariable Long id) {
        return ownerRepo.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(id));
    }

    // PUT Replace a Owner by the Id
    @PutMapping("/api/v1/owners/{id}")
    Owner replaceOwner(@RequestBody Owner newOwner, @PathVariable Long id) {
        return ownerRepo.findById(id)
                .map(owner -> {
                    owner.setUsername(newOwner.getUsername());
                    owner.setEmail(newOwner.getEmail());
                    owner.setPassword(newOwner.getPassword());
                    return ownerRepo.save(owner);
                })
                .orElseGet(() -> {
                    newOwner.setId(id);
                    return ownerRepo.save(newOwner);
                });
    }

    // DELETE Delete an Owner by Id
    @DeleteMapping("/api/v1/owners/{id}")
    void deleteOwner(@PathVariable Long id) {
        ownerRepo.deleteById(id);
    }
}
