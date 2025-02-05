package com.bender;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AiService aiService;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AiService aiService) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
//        String prompt = """
//                Based on programming tech stack %s that %s has given
//                Provide a full learning path and recommendations for this person.
//                """.formatted(softwareEngineer.getTechStack(), softwareEngineer.getName());
//        String chatRes = aiService.chat(prompt);
//        softwareEngineer.setRecommendations(chatRes); TODO API is paid
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException("SoftwareEngineer for " + id + " not found"));
    }

    public void deleteSoftwareEngineerById(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        softwareEngineerRepository.deleteById(id);
    }

    public void updateSoftwareEngineerById(SoftwareEngineer softwareEngineer, Integer id) {
        SoftwareEngineer softwareEngineerFromDb = softwareEngineerRepository.findById(id).orElse(null);

        if (softwareEngineer.getName() != null) {
            softwareEngineerFromDb.setName(softwareEngineer.getName());
        }

        if (softwareEngineer.getTechStack() != null) {
            softwareEngineerFromDb.setTechStack(softwareEngineer.getTechStack());
        }

        softwareEngineerRepository.save(softwareEngineerFromDb);
    }
}
