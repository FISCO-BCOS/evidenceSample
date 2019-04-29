package org.bcos.evidence.web3j;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Evidence extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b50604051620020ae380380620020ae833981018060405281019080805182019291906020018051820192919060200180518201929190602001805190602001909291908051906020019092919080519060200190929190805190602001909291908051906020019092919050505081600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000da8162000614640100000000026401000000009004565b1562000424578760009080519060200190620000f892919062000718565b5086600190805190602001906200011192919062000718565b5085600290805190602001906200012a92919062000718565b50600385908060018154018082558091505090600182039060005260206000209060209182820401919006909192909190916101000a81548160ff021916908360ff160217905550506004849080600181540180825580915050906001820390600052602060002001600090919290919091509060001916905550600583908060018154018082558091505090600182039060005260206000200160009091929091909150906000191690555060068190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507f6001b9d5afd61e6053e8a73e30790ef8240d919a754055049131521927fbe21188888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818151815260200191508051906020019080838360005b8381101562000309578082015181840152602081019050620002ec565b50505050905090810190601f168015620003375780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b838110156200037257808201518184015260208101905062000355565b50505050905090810190601f168015620003a05780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620003db578082015181840152602081019050620003be565b50505050905090810190601f168015620004095780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a162000606565b7f45cb885dcdccd3bece3cb78b963aec501f1cf9756fd93584f0643c7a9533431088888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818151815260200191508051906020019080838360005b83811015620004f0578082015181840152602081019050620004d3565b50505050905090810190601f1680156200051e5780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b83811015620005595780820151818401526020810190506200053c565b50505050905090810190601f168015620005875780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620005c2578082015181840152602081019050620005a5565b50505050905090810190601f168015620005f05780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a15b5050505050505050620007c7565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015620006d457600080fd5b505af1158015620006e9573d6000803e3d6000fd5b505050506040513d60208110156200070057600080fd5b81019080805190602001909291905050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200075b57805160ff19168380011785556200078c565b828001600101855582156200078c579182015b828111156200078b5782518255916020019190600101906200076e565b5b5090506200079b91906200079f565b5090565b620007c491905b80821115620007c0576000816000905550600101620007a6565b5090565b90565b6118d780620007d76000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633b52ebd01461007d57806348f85bce146100d4578063596f21f81461013857806394cf795e146103c0578063c7eaf9b41461042c578063dc58ab11146104bc575b600080fd5b34801561008957600080fd5b50610092610517565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100e057600080fd5b5061011e600480360381019080803560ff1690602001909291908035600019169060200190929190803560001916906020019092919050505061053d565b604051808215151515815260200191505060405180910390f35b34801561014457600080fd5b5061014d610f7d565b604051808060200180602001806020018060200180602001806020018060200188810388528f818151815260200191508051906020019080838360005b838110156101a557808201518184015260208101905061018a565b50505050905090810190601f1680156101d25780820380516001836020036101000a031916815260200191505b5088810387528e818151815260200191508051906020019080838360005b8381101561020b5780820151818401526020810190506101f0565b50505050905090810190601f1680156102385780820380516001836020036101000a031916815260200191505b5088810386528d818151815260200191508051906020019080838360005b83811015610271578082015181840152602081019050610256565b50505050905090810190601f16801561029e5780820380516001836020036101000a031916815260200191505b5088810385528c818151815260200191508051906020019060200280838360005b838110156102da5780820151818401526020810190506102bf565b5050505090500188810384528b818151815260200191508051906020019060200280838360005b8381101561031c578082015181840152602081019050610301565b5050505090500188810383528a818151815260200191508051906020019060200280838360005b8381101561035e578082015181840152602081019050610343565b50505050905001888103825289818151815260200191508051906020019060200280838360005b838110156103a0578082015181840152602081019050610385565b505050509050019e50505050505050505050505050505060405180910390f35b3480156103cc57600080fd5b506103d56114d2565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156104185780820151818401526020810190506103fd565b505050509050019250505060405180910390f35b34801561043857600080fd5b50610441611708565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610481578082015181840152602081019050610466565b50505050905090810190601f1680156104ae5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156104c857600080fd5b506104fd600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506117aa565b604051808215151515815260200191505060405180910390f35b600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080600090505b600680549050811015610a175760068181548110151561056157fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610a0a578460ff166003828154811015156105d257fe5b90600052602060002090602091828204019190069054906101000a900460ff1660ff161480156106225750836000191660048281548110151561061157fe5b906000526020600020015460001916145b801561064e5750826000191660058281548110151561063d57fe5b906000526020600020015460001916145b1561085a577fcb265a1c827beb2fd9947f2a8d4725c8918f266faf695a26a53ac662e42a2f3f600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a8181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156107365780601f1061070b57610100808354040283529160200191610736565b820191906000526020600020905b81548152906001019060200180831161071957829003601f168201915b50508481038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156107b95780601f1061078e576101008083540402835291602001916107b9565b820191906000526020600020905b81548152906001019060200180831161079c57829003601f168201915b505084810382528881815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561083c5780601f106108115761010080835404028352916020019161083c565b820191906000526020600020905b81548152906001019060200180831161081f57829003601f168201915b5050995050505050505050505060405180910390a160019150610f75565b7f05e85d72e83e8d2c8c877a19dd3a15c60415f315dc6d176b21537216537d275760006002878787336040518080602001806020018760ff1660ff168152602001866000191660001916815260200185600019166000191681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528981815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561096a5780601f1061093f5761010080835404028352916020019161096a565b820191906000526020600020905b81548152906001019060200180831161094d57829003601f168201915b50508381038252888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156109ed5780601f106109c2576101008083540402835291602001916109ed565b820191906000526020600020905b8154815290600101906020018083116109d057829003601f168201915b50509850505050505050505060405180910390a160009150610f75565b8080600101915050610545565b610a20336117aa565b15610d3e57600385908060018154018082558091505090600182039060005260206000209060209182820401919006909192909190916101000a81548160ff021916908360ff160217905550506004849080600181540180825580915050906001820390600052602060002001600090919290919091509060001916905550600583908060018154018082558091505090600182039060005260206000200160009091929091909150906000191690555060063390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507fbf474e795141390215f4f179557402a28c562b860f7b74dce4a3c0e0604cd97e600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c1a5780601f10610bef57610100808354040283529160200191610c1a565b820191906000526020600020905b815481529060010190602001808311610bfd57829003601f168201915b5050848103835289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c9d5780601f10610c7257610100808354040283529160200191610c9d565b820191906000526020600020905b815481529060010190602001808311610c8057829003601f168201915b5050848103825288818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610d205780601f10610cf557610100808354040283529160200191610d20565b820191906000526020600020905b815481529060010190602001808311610d0357829003601f168201915b5050995050505050505050505060405180910390a160019150610f75565b7fc585b66a303b685f03874907af9278712998ea1acfed37bcb4277da02cddb8b460006001600288888833604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610e545780601f10610e2957610100808354040283529160200191610e54565b820191906000526020600020905b815481529060010190602001808311610e3757829003601f168201915b505084810383528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610ed75780601f10610eac57610100808354040283529160200191610ed7565b820191906000526020600020905b815481529060010190602001808311610eba57829003601f168201915b5050848103825289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610f5a5780601f10610f2f57610100808354040283529160200191610f5a565b820191906000526020600020905b815481529060010190602001808311610f3d57829003601f168201915b50509a505050505050505050505060405180910390a1600091505b509392505050565b6060806060806060806060600060606000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561101457600080fd5b505af1158015611028573d6000803e3d6000fd5b505050506040513d602081101561103e57600080fd5b81019080805190602001909291905050509250826040519080825280602002602001820160405280156110805781602001602082028038833980820191505090505b509150600090505b828110156111b357600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561112157600080fd5b505af1158015611135573d6000803e3d6000fd5b505050506040513d602081101561114b57600080fd5b8101908080519060200190929190505050828281518110151561116a57fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508080600101915050611088565b60006001600260036004600587868054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112555780601f1061122a57610100808354040283529160200191611255565b820191906000526020600020905b81548152906001019060200180831161123857829003601f168201915b50505050509650858054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112f15780601f106112c6576101008083540402835291602001916112f1565b820191906000526020600020905b8154815290600101906020018083116112d457829003601f168201915b50505050509550848054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561138d5780601f106113625761010080835404028352916020019161138d565b820191906000526020600020905b81548152906001019060200180831161137057829003601f168201915b505050505094508380548060200260200160405190810160405280929190818152602001828054801561140557602002820191906000526020600020906000905b82829054906101000a900460ff1660ff16815260200190600101906020826000010492830192600103820291508084116113ce5790505b505050505093508280548060200260200160405190810160405280929190818152602001828054801561145b57602002820191906000526020600020905b81546000191681526020019060010190808311611443575b50505050509250818054806020026020016040519081016040528092919081815260200182805480156114b157602002820191906000526020600020905b81546000191681526020019060010190808311611499575b50505050509150995099509950995099509950995050505090919293949596565b6060600060606000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561156057600080fd5b505af1158015611574573d6000803e3d6000fd5b505050506040513d602081101561158a57600080fd5b81019080805190602001909291905050509250826040519080825280602002602001820160405280156115cc5781602001602082028038833980820191505090505b509150600090505b828110156116ff57600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561166d57600080fd5b505af1158015611681573d6000803e3d6000fd5b505050506040513d602081101561169757600080fd5b810190808051906020019092919050505082828151811015156116b657fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505080806001019150506115d4565b81935050505090565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117a05780601f10611775576101008083540402835291602001916117a0565b820191906000526020600020905b81548152906001019060200180831161178357829003601f168201915b5050505050905090565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561186957600080fd5b505af115801561187d573d6000803e3d6000fd5b505050506040513d602081101561189357600080fd5b810190808051906020019092919050505090509190505600a165627a7a72305820e1f62ecd9a805ca7433377e7477b21708ad545989a532021e4705e6470f2f8ab0029";

    public static final String FUNC_SIGNERSADDR = "signersAddr";

    public static final String FUNC_ADDSIGNATURES = "addSignatures";

    public static final String FUNC_GETEVIDENCE = "getEvidence";

    public static final String FUNC_GETSIGNERS = "getSigners";

    public static final String FUNC_GETEVIDENCEINFO = "getEvidenceInfo";

    public static final String FUNC_CALLVERIFY = "CallVerify";

    public static final Event ADDSIGNATURESEVENT_EVENT = new Event("addSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event NEWSIGNATURESEVENT_EVENT = new Event("newSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ERRORNEWSIGNATURESEVENT_EVENT = new Event("errorNewSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ERRORADDSIGNATURESEVENT_EVENT = new Event("errorAddSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ADDREPEATSIGNATURESEVENT_EVENT = new Event("addRepeatSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event ERRORREPEATSIGNATURESEVENT_EVENT = new Event("errorRepeatSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Evidence(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> signersAddr() {
        final Function function = new Function(FUNC_SIGNERSADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addSignatures(BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addSignatures(BigInteger v, byte[] r, byte[] s, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>> getEvidence() {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>>(
                new Callable<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>>() {
                    @Override
                    public Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                convertToNative((List<Uint8>) results.get(3).getValue()), 
                                convertToNative((List<Bytes32>) results.get(4).getValue()), 
                                convertToNative((List<Bytes32>) results.get(5).getValue()), 
                                convertToNative((List<Address>) results.get(6).getValue()));
                    }
                });
    }

    public RemoteCall<List> getSigners() {
        final Function function = new Function(FUNC_GETSIGNERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> getEvidenceInfo() {
        final Function function = new Function(FUNC_GETEVIDENCEINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> CallVerify(String addr) {
        final Function function = new Function(FUNC_CALLVERIFY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<AddSignaturesEventEventResponse> getAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<AddSignaturesEventEventResponse> responses = new ArrayList<AddSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddSignaturesEventEventResponse> addSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, AddSignaturesEventEventResponse>() {
            @Override
            public AddSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDSIGNATURESEVENT_EVENT, log);
                AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddSignaturesEventEventResponse> addSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDSIGNATURESEVENT_EVENT));
        return addSignaturesEventEventFlowable(filter);
    }

    public List<NewSignaturesEventEventResponse> getNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<NewSignaturesEventEventResponse> responses = new ArrayList<NewSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewSignaturesEventEventResponse> newSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, NewSignaturesEventEventResponse>() {
            @Override
            public NewSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWSIGNATURESEVENT_EVENT, log);
                NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewSignaturesEventEventResponse> newSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWSIGNATURESEVENT_EVENT));
        return newSignaturesEventEventFlowable(filter);
    }

    public List<ErrorNewSignaturesEventEventResponse> getErrorNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERRORNEWSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<ErrorNewSignaturesEventEventResponse> responses = new ArrayList<ErrorNewSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ErrorNewSignaturesEventEventResponse> errorNewSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ErrorNewSignaturesEventEventResponse>() {
            @Override
            public ErrorNewSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERRORNEWSIGNATURESEVENT_EVENT, log);
                ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ErrorNewSignaturesEventEventResponse> errorNewSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERRORNEWSIGNATURESEVENT_EVENT));
        return errorNewSignaturesEventEventFlowable(filter);
    }

    public List<ErrorAddSignaturesEventEventResponse> getErrorAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERRORADDSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<ErrorAddSignaturesEventEventResponse> responses = new ArrayList<ErrorAddSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ErrorAddSignaturesEventEventResponse> errorAddSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ErrorAddSignaturesEventEventResponse>() {
            @Override
            public ErrorAddSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERRORADDSIGNATURESEVENT_EVENT, log);
                ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ErrorAddSignaturesEventEventResponse> errorAddSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERRORADDSIGNATURESEVENT_EVENT));
        return errorAddSignaturesEventEventFlowable(filter);
    }

    public List<AddRepeatSignaturesEventEventResponse> getAddRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDREPEATSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<AddRepeatSignaturesEventEventResponse> responses = new ArrayList<AddRepeatSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddRepeatSignaturesEventEventResponse> addRepeatSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, AddRepeatSignaturesEventEventResponse>() {
            @Override
            public AddRepeatSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDREPEATSIGNATURESEVENT_EVENT, log);
                AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddRepeatSignaturesEventEventResponse> addRepeatSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDREPEATSIGNATURESEVENT_EVENT));
        return addRepeatSignaturesEventEventFlowable(filter);
    }

    public List<ErrorRepeatSignaturesEventEventResponse> getErrorRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERRORREPEATSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<ErrorRepeatSignaturesEventEventResponse> responses = new ArrayList<ErrorRepeatSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ErrorRepeatSignaturesEventEventResponse> errorRepeatSignaturesEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ErrorRepeatSignaturesEventEventResponse>() {
            @Override
            public ErrorRepeatSignaturesEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERRORREPEATSIGNATURESEVENT_EVENT, log);
                ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
                typedResponse.log = log;
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ErrorRepeatSignaturesEventEventResponse> errorRepeatSignaturesEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERRORREPEATSIGNATURESEVENT_EVENT));
        return errorRepeatSignaturesEventEventFlowable(filter);
    }

    @Deprecated
    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Evidence(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Evidence(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Evidence> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class AddSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;
    }

    public static class NewSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class ErrorNewSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class ErrorAddSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class AddRepeatSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;
    }

    public static class ErrorRepeatSignaturesEventEventResponse {
        public Log log;

        public String evi;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }
}
