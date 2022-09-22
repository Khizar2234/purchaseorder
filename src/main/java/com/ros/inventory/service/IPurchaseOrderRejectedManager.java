package com.ros.inventory.service;

import java.util.List;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.controller.dto.SiTeTransfersDto;

public interface IPurchaseOrderRejectedManager {
	List<RejectedDto>showByStatus() throws InventoryException;
}
