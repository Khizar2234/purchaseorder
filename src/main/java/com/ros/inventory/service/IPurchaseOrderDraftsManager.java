package com.ros.inventory.service;

import com.ros.inventory.controller.dto.DraftsDto;

import java.util.List;

public interface IPurchaseOrderDraftsManager {
    public List<DraftsDto> getDrafts();
}
