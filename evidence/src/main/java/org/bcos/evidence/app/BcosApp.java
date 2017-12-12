package org.bcos.evidence.app;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.*;
import org.bcos.channel.client.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.bcos.evidence.sample.EvidenceData;
import org.bcos.evidence.sample.PublicAddressConf;
import org.bcos.evidence.util.Tools;
import org.bcos.evidence.web3j.Evidence;
import org.bcos.evidence.web3j.EvidenceSignersData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public class BcosApp {
	
	private EvidenceSignersData evidenceSignersData;
	private Web3j web3j;
	ApplicationContext context;
	
	public static BigInteger gasPrice = new BigInteger("99999999999");
	public static BigInteger gasLimited = new BigInteger("9999999999999");
	public static BigInteger initialValue = new BigInteger("0");
	
	public BcosApp() {
		evidenceSignersData =null;
		web3j = null;
		context=null;
	}
	
	//loadConfig
	public boolean loadConfig() throws Exception{
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
        service.run();
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        web3j = Web3j.build(channelEthereumService);
        boolean flag=false;
        if(web3j!=null){
        	flag=true;
        }
        return flag;
	}
	
	//deploy
	public Address deployContract(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception {
		if (web3j == null )
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
	    Service service = context.getBean(Service.class);
	    service.run();
	    PublicAddressConf conf = context.getBean(PublicAddressConf.class);
	    Thread.sleep(3000);
		
        ConcurrentHashMap<String, String> addressConf = conf.getAllPublicAddress();
        ArrayList<Address> arrayList = addressConf.values().stream().map(Address::new).collect(Collectors.toCollection(ArrayList::new));
        DynamicArray<Address> evidenceSigners = new DynamicArray<Address>(arrayList);
        try {
            evidenceSignersData = EvidenceSignersData.deploy(web3j, credentials, gasPrice, gasLimited, initialValue,evidenceSigners).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Address(evidenceSignersData.getContractAddress());
		        
	}
	
	//newEvidence
	public Address newEvidence(String keyStoreFileName,String keyStorePassword, String keyPassword,String address,String evidenceId,String evidenceHash) throws Exception {
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
		if (web3j == null)
			return null;
		
		if (address != null) {
			evidenceSignersData = EvidenceSignersData.load(address.toString(), web3j,  credentials, gasPrice, gasLimited);
		}
		String evidence_id=evidenceId;
		String evidence_hash=evidenceHash;
		//通过hash和key算出一个用户机构签名数据
		Sign.SignatureData data = Sign.signMessage(evidence_hash.getBytes(), credentials.getEcKeyPair());
		String sign_data=Tools.signatureDataToString(data);
		TransactionReceipt receipt = null;
		try {
			Sign.SignatureData signatureData = Tools.stringToSignatureData(sign_data);
			System.out.println("正在执行！请稍等！");
			Thread.sleep(3000);
			receipt = evidenceSignersData.newEvidence(new Utf8String(evidence_hash),new Utf8String(evidence_id),new Utf8String(evidence_id),new Uint8(signatureData.getV()),new Bytes32(signatureData.getR()),new Bytes32(signatureData.getS())).get();
			List<EvidenceSignersData.NewEvidenceEventEventResponse> newEvidenceList = evidenceSignersData.getNewEvidenceEventEvents(receipt);
			if (newEvidenceList.size() > 0) {
	               return newEvidenceList.get(0).addr;
	        } else {
	               return null;
	        }
		} catch (InterruptedException | ExecutionException e) {
			throw e;
		}
		
	}
	
	//sendSignatureToBlockChain
	//1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newEvidenceAddress 5.evidence_hash
	public boolean sendSignatureToBlockChain(String[] args,String evidence_hash) throws Exception{
		Credentials credentials=loadkey(args[1],args[2],args[3]);
	    Evidence evidence = Evidence.load(args[4], web3j, credentials,  gasPrice, gasLimited);
	    Sign.SignatureData data = Sign.signMessage(evidence_hash.getBytes(), credentials.getEcKeyPair());
		boolean flag=false;
		try {
				String signatureString=Tools.signatureDataToString(data);
				Sign.SignatureData signature = Tools.stringToSignatureData(signatureString);
	            String recoverAddress = verifySignedMessage(evidence_hash,signatureString);
	            if(!credentials.getAddress().equals(recoverAddress))
	            {
	                throw new SignatureException();
	            }
	            System.out.println("开始发送！请稍等！");
	            Thread.sleep(3000);
	            TransactionReceipt receipt = evidence.addSignatures(new Uint8(signature.getV()),
	                    new Bytes32(signature.getR()),
	                    new Bytes32(signature.getS())).get();
	            List<Evidence.AddSignaturesEventEventResponse> addList = evidence.getAddSignaturesEventEvents(receipt);
	            List<Evidence.AddRepeatSignaturesEventEventResponse> addList2 = evidence.getAddRepeatSignaturesEventEvents(receipt);

	            if (addList.size() > 0 || addList2.size() >0)
	            {
	            	flag=true;	
	            }
	        } catch (InterruptedException e) {
	            throw e;
	        } catch (ExecutionException e) {
	            throw e;
	     }
		return flag;
	}
	
	
	//getEvidence
	public EvidenceData getEvidence(String keyStoreFileName,String keyStorePassword, String keyPassword,String transactionHash) throws Exception{
		if (web3j == null )
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
		Evidence evidence = Evidence.load(transactionHash, web3j, credentials,  gasPrice, gasLimited);
		EvidenceData evidenceData = new EvidenceData();
		try {
			Thread.sleep(3000);
			List<Type> result2 = evidence.getEvidence().get();
            if (result2.size() >= 6) {
                evidenceData.setEvidenceHash(((Utf8String) result2.get(0)).getValue());
                evidenceData.setEvidenceInfo(((Utf8String) result2.get(1)).getValue());
                evidenceData.setEvidenceID(((Utf8String) result2.get(2)).getValue());
                List<Uint8> vlist = ((DynamicArray<Uint8>) result2.get(3)).getValue();
                List<Bytes32> rlist = ((DynamicArray<Bytes32>) result2.get(4)).getValue();
                List<Bytes32> slist = ((DynamicArray<Bytes32>) result2.get(5)).getValue();
                ArrayList<String> signatureList = new ArrayList<String>();
                for (int i = 0; i < vlist.size(); i++) {
                    Sign.SignatureData signature = new Sign.SignatureData(
                            ((vlist.get(i).getValue())).byteValue(),
                            (rlist.get(i)).getValue(),
                            (slist.get(i)).getValue());
                    signatureList.add(Tools.signatureDataToString(signature));
                }
                evidenceData.setSignatures(signatureList);
                List<Address> addresses = ((DynamicArray<Address>) result2.get(6)).getValue();
                ArrayList<String> addressesList = new ArrayList<String>();
                for (int i = 0; i < addresses.size(); i++) {
                    String str = addresses.get(i).toString();
                    addressesList.add(str);
                }
                evidenceData.setPublicKeys(addressesList);
            }
		}  catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e;
        }
		return evidenceData;
	}
	
	//verifyEvidence
	public boolean verifyEvidence(EvidenceData data) throws SignatureException {
		 ArrayList<String> addressList = new ArrayList<>();
	        for (String str : data.getSignatures()) {
	            try {
	                addressList.add(verifySignedMessage(data.getEvidenceHash(), str));
	            } catch (SignatureException e) {
	                throw e;
	            }
	        }
	        for (String addr : data.getPublicKeys()) {
	            boolean flag = false;
	            for (String str : addressList) {
	                if (str.equals(addr)) {
	                    flag = true;
	                    break;
	                }
	            }
	            if (!flag) {
	                return false;
	            }
	        }
	        return true;
	}
	
	
    public String verifySignedMessage(String message, String signatureData) throws SignatureException {
        Sign.SignatureData signatureData1 = Tools.stringToSignatureData(signatureData);
        try {
            return "0x" + Keys.getAddress(Sign.signedMessageToKey(message.getBytes(), signatureData1));
        } catch (SignatureException e) {
            throw e;
        }
    }
    
    public Credentials loadkey(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception{
    	InputStream ksInputStream = null;
    	try {
    		 KeyStore ks = KeyStore.getInstance("JKS");
    		 ksInputStream =  BcosApp.class.getClassLoader().getResourceAsStream(keyStoreFileName);
    		 ks.load(ksInputStream, keyStorePassword.toCharArray());
    		 Key key = ks.getKey("ec", keyPassword.toCharArray());
    		 ECKeyPair keyPair = ECKeyPair.create(((ECPrivateKey) key).getS());
    		 Credentials credentials = Credentials.create(keyPair);	
    		 if(credentials!=null){
    		    return credentials;
    		 }else{
    			 System.out.println("秘钥参数输入有误！");
    		 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ksInputStream.close();
		}
	    return null;
    }
    
    public String getPublicKey(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception{
    	Credentials credentials=loadkey(keyStoreFileName, keyStorePassword, keyPassword);
    	return credentials.getAddress();
    }
	
}
