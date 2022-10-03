package com.ros.inventory.Repository;

import com.ros.inventory.entities.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttachmentsRepository extends JpaRepository<Attachments, UUID> {


}
