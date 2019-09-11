package org.bcos.evidence.sample;

import java.util.concurrent.ConcurrentHashMap;

public class PublicAddressConf {
    private ConcurrentHashMap<String, String> allPublicAddress;

    public ConcurrentHashMap<String, String> getAllPublicAddress() {
        return allPublicAddress;
    }

    public void setAllPublicAddress(ConcurrentHashMap<String, String> allPublicAddress) {
        this.allPublicAddress = allPublicAddress;
    }


}
