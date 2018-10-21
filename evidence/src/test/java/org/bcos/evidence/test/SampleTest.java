package org.bcos.evidence.test;

import org.bcos.evidence.app.BcosApp;
import org.bcos.evidence.util.Tools;
import org.bcos.evidence.web3j.EvidenceSignersData;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

public class SampleTest {

    private static String LOGO = "\n"
            + "命令输入参考如下！ \n"
            + "工厂合约部署：./evidence deploy keyStoreFileName keyStorePassword keyPassword  \n"
            + "创建新证据：./evidence new  keyStoreFileName keyStorePassword keyPassword deployAddress evidence_id evidence_hash  \n"
            + "发送签名：./evidence send keyStoreFileName keyStorePassword keyPassword newEvidenceAddress evidence_hash \n"
            + "获取证据：./evidence get keyStoreFileName keyStorePassword keyPassword newEvidenceAddress \n"
            + "证据和签名校验：./evidence verify keyStoreFileName keyStorePassword keyPassword newEvidenceAddress \n"
            + "获取公钥：./evidence getPublicKey keyStoreFileName keyStorePassword keyPassword  \n";

    public  static String keyStoreFileName = "user.jks";
    public  static String keyStorePassword = "123456";
    public  static String keyPassword = "123456";
     public String contractAddress ;

    @Test
    public void DeployTest() throws Exception {

        BcosApp app = new BcosApp();
        Address address = null;
        Address newEvidenceAddress = null;
        boolean configure = app.loadConfig();
        //此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
        address = app.deployContract(keyStoreFileName, keyStorePassword, keyPassword);
        contractAddress = address.toString();
        assertNotNull(address.toString());

    }

    @Test
    public void newEvidentTest() throws Exception {
        BcosApp app = new BcosApp();
        Address address = null;
        Address newEvidenceAddress = null;
        boolean configure = app.loadConfig();
        //此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
        address = app.newEvidence(keyStoreFileName, keyStorePassword, keyPassword,contractAddress,"id-1","hash-1");
        System.out.println("deploy factoryContract success, address: " + address.toString());

    }

}
