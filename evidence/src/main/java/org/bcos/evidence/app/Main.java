package org.bcos.evidence.app;

import java.util.List;

import org.bcos.evidence.sample.EvidenceData;
import org.bcos.web3j.abi.datatypes.Address;


public class Main {
	
	private static String LOGO = "\n" 
            + "命令输入参考如下！ \n"
            + "工厂合约部署：./evidence deploy keyStoreFileName keyStorePassword keyPassword  \n"
            + "创建新证据：./evidence new  keyStoreFileName keyStorePassword keyPassword deployAddress evidence_id evidence_hash  \n"
            + "发送签名：./evidence send keyStoreFileName keyStorePassword keyPassword newEvidenceAddress evidence_hash \n"
            + "获取证据：./evidence get keyStoreFileName keyStorePassword keyPassword newEvidenceAddress \n"
            + "证据和签名校验：./evidence verify keyStoreFileName keyStorePassword keyPassword newEvidenceAddress \n"
            + "获取公钥：./evidence getPublicKey keyStoreFileName keyStorePassword keyPassword  \n";

	public static void main(String[] args) throws Exception {
		BcosApp app = new BcosApp();
		Address address=null;
		Address newEvidenceAddress=null;
		boolean configure = app.loadConfig();
		if(args.length<4)
		{
			System.out.println("输入参数最小为4");
			System.exit(0);
		}
		if (!configure) {
			System.err.println("error in load configure, init failed !!!");
			System.exit(0);
		}
		System.out.println(LOGO);
		switch (args[0]) {
			//deploy
		 	case "deploy":
		 		//此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
		 		address=app.deployContract(args[1],args[2],args[3]);
		 		System.out.println("deploy factoryContract success, address: " + address.toString());
		 		break;
		 	//newEvidence
		 	case "new":
		 		//参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword,传入的参数需要部署的工厂合约地址,evidence_id、evidence_hash
		 		newEvidenceAddress=app.newEvidence(args[1],args[2],args[3],args[4],args[5],args[6]);
		 		System.out.println("newEvidence success, newEvidenceAddress: " + newEvidenceAddress.toString());
		 		break;
		 	//sendSignatureToBlockChain
		 	case "send":
		 		//1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newEvidenceAddress
		 		//通过证据地址获取证据信息
		 		EvidenceData evidenceData2 = app.getEvidence(args[1],args[2],args[3],args[4]);
		 		boolean falg = app.sendSignatureToBlockChain(args,evidenceData2.getEvidenceHash());
		 		if(falg){
		 			System.out.println("sendSignatureToBlockChain success！"+falg);
		 		}else{
		 			System.out.println("sendSignatureToBlockChain failed！"+falg);
		 		}
		 		break;
		 	//getEvidence
		 	case "get":	
		 		//传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newEvidence返回地址
		 		EvidenceData evidenceData = app.getEvidence(args[1],args[2],args[3],args[4]);
		 		System.out.println("the evidenceID: " + evidenceData.getEvidenceID());
		 		System.out.println("the evidenceHash: " + evidenceData.getEvidenceHash());
		 		List<String> signatureslist=evidenceData.getSignatures();
		 		for(int i=0;i<signatureslist.size();i++){
		 			String signatures=signatureslist.get(i);
		 			System.out.println("the signature[" + i + "]: "+signatures);
		 		}
		 		List<String> publicKeyslist=evidenceData.getPublicKeys();
		 		for(int i=0;i<publicKeyslist.size();i++){
		 			String publicKey=publicKeyslist.get(i);
		 			System.out.println("the publicKey[" + i + "]: "+publicKey);
		 		}
		 		break;
		 	case "verify":
		 		//传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newEvidence返回地址
		 		EvidenceData evidenceData1 = app.getEvidence(args[1],args[2],args[3],args[4]);
		 		boolean flag=app.verifyEvidence(evidenceData1);
		 		if(flag){
		 			System.out.println("verifyEvidence success:"+flag);
		 		}else{
		 			System.out.println("verifyEvidence failed:"+flag);
		 		}
		 		break;
		 	case "getPublicKey":
		 		String publicKey=app.getPublicKey(args[1], args[2], args[3]);
		 		System.out.println("publicKey:"+publicKey);
		 		break;
		 	default:
                 break;
		}
		System.exit(0);
	}
	
}
