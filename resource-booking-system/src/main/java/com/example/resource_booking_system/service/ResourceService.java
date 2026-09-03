package com.example.resource_booking_system.service;

import com.example.resource_booking_system.dto.ResourceRequest;
import com.example.resource_booking_system.dto.ResourceResponse;
import com.example.resource_booking_system.entity.Resource;
import com.example.resource_booking_system.exception.ResourceNotFoundException;
import com.example.resource_booking_system.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public ResourceResponse createResource(ResourceRequest request) {

        Resource resource = new Resource();

        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setDescription(request.getDescription());
        resource.setAvailable(request.getAvailable());
        resource.setPrice(request.getPrice());

        Resource savedResource = resourceRepository.save(resource);

        return convertToResponse(savedResource);
    }

    public List<ResourceResponse> getAllResources() {

        return resourceRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ResourceResponse getResourceById(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Resource not found with id: " + id));

        return convertToResponse(resource);
    }

    public ResourceResponse updateResource(
            Long id,
            ResourceRequest request) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Resource not found with id: " + id));

        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setDescription(request.getDescription());
        resource.setAvailable(request.getAvailable());
        resource.setPrice(request.getPrice());

        Resource updatedResource = resourceRepository.save(resource);

        return convertToResponse(updatedResource);
    }

    public void deleteResource(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Resource not found with id: " + id));

        resourceRepository.delete(resource);
    }

    private ResourceResponse convertToResponse(Resource resource) {

        return new ResourceResponse(
                resource.getId(),
                resource.getName(),
                resource.getType(),
                resource.getDescription(),
                resource.isAvailable(),
                resource.getPrice()
        );
    }
}