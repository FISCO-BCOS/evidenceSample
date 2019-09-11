package org.bcos.evidence.sample;

import java.util.List;

public class EvidenceData {
    private List<String> signatures;//证据签名列表
    private String evidenceHash;//证据hash
    private String evidenceID;//证据ID
    private String evidenceInfo;//证据说明信息
    private List<String> publicKeys;//证据生效需要的公钥列表

    public EvidenceData(List<String> signatures, String evidenceHash, String evidenceID, String evidenceInfo, List<String> publicKeys) {
        this.signatures = signatures;
        this.evidenceHash = evidenceHash;
        this.evidenceInfo = evidenceInfo;
        this.evidenceID = evidenceID;
        this.publicKeys = publicKeys;
    }
    public EvidenceData() {

    }

    public String getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(String evidenceID) {
        this.evidenceID = evidenceID;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }

    public String getEvidenceHash() {
        return evidenceHash;
    }

    public void setEvidenceHash(String evidenceHash) {
        this.evidenceHash = evidenceHash;
    }

    public String getEvidenceInfo() {
        return evidenceInfo;
    }

    public void setEvidenceInfo(String evidenceInfo) {
        this.evidenceInfo = evidenceInfo;
    }

    public List<String> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(List<String> publicKeys) {
        this.publicKeys = publicKeys;
    }
}
