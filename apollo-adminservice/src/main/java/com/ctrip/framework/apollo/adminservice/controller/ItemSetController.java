package com.ctrip.framework.apollo.adminservice.controller;

import com.ctrip.framework.apollo.adminservice.aop.PreAcquireNamespaceLock;
import com.ctrip.framework.apollo.biz.service.ItemSetService;
import com.ctrip.framework.apollo.common.dto.ItemChangeSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Item 集合 Controller
 */
@RestController
public class ItemSetController {

    @Autowired
    private ItemSetService itemSetService;

    @PreAcquireNamespaceLock
    @RequestMapping(path = "/apps/{appId}/clusters/{clusterName}/namespaces/{namespaceName}/itemset", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@PathVariable String appId, @PathVariable String clusterName,
                                       @PathVariable String namespaceName, @RequestBody ItemChangeSets changeSet) {
        // 批量更新 Namespace 下的 Item 们
        itemSetService.updateSet(appId, clusterName, namespaceName, changeSet);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
