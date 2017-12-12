package org.bcos.evidence.web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.bcos.web3j.abi.EventEncoder;
import org.bcos.web3j.abi.EventValues;
import org.bcos.web3j.abi.FunctionEncoder;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Bool;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Event;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.bcos.web3j.protocol.core.methods.response.Log;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class Evidence extends Contract {
    private static final String BINARY = "606060405234156200001057600080fd5b6040516200222938038062002229833981016040528080518201919060200180518201919060200180518201919060200180519060200190919080519060200190919080519060200190919080519060200190919080519060200190919050505b81600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000d181620006116401000000000262001768176401000000009004565b156200041d578760009080519060200190620000ef929190620006fd565b50866001908051906020019062000108929190620006fd565b50856002908051906020019062000121929190620006fd565b506003805480600101828162000138919062000784565b91600052602060002090602091828204019190065b87909190916101000a81548160ff021916908360ff16021790555050600480548060010182816200017f9190620007c1565b916000526020600020900160005b869091909150906000191690555060058054806001018281620001b19190620007c1565b916000526020600020900160005b859091909150906000191690555060068054806001018281620001e39190620007f0565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507f6001b9d5afd61e6053e8a73e30790ef8240d919a754055049131521927fbe21188888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818151815260200191508051906020019080838360005b83811015620003005780820151818401525b602081019050620002e2565b50505050905090810190601f1680156200032e5780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b838110156200036a5780820151818401525b6020810190506200034c565b50505050905090810190601f168015620003985780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620003d45780820151818401525b602081019050620003b6565b50505050905090810190601f168015620004025780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a162000602565b7f45cb885dcdccd3bece3cb78b963aec501f1cf9756fd93584f0643c7a9533431088888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818151815260200191508051906020019080838360005b83811015620004ea5780820151818401525b602081019050620004cc565b50505050905090810190601f168015620005185780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b83811015620005545780820151818401525b60208101905062000536565b50505050905090810190601f168015620005825780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620005be5780820151818401525b602081019050620005a0565b50505050905090810190601f168015620005ec5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a15b5b50505050505050506200086f565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515620006d957600080fd5b6102c65a03f11515620006eb57600080fd5b5050506040518051905090505b919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200074057805160ff191683800117855562000771565b8280016001018555821562000771579182015b828111156200077057825182559160200191906001019062000753565b5b5090506200078091906200081f565b5090565b815481835581811511620007bc57601f016020900481601f01602090048360005260206000209182019101620007bb91906200081f565b5b505050565b815481835581811511620007eb57818360005260206000209182019101620007ea919062000847565b5b505050565b8154818355818115116200081a578183600052602060002091820191016200081991906200081f565b5b505050565b6200084491905b808211156200084057600081600090555060010162000826565b5090565b90565b6200086c91905b80821115620008685760008160009055506001016200084e565b5090565b90565b6119aa806200087f6000396000f30060606040523615610076576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633b52ebd01461007b57806348f85bce146100d0578063596f21f81461012857806394cf795e146103b5578063c7eaf9b414610420578063dc58ab11146104af575b600080fd5b341561008657600080fd5b61008e610500565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156100db57600080fd5b61010e600480803560ff169060200190919080356000191690602001909190803560001916906020019091905050610526565b604051808215151515815260200191505060405180910390f35b341561013357600080fd5b61013b610f71565b604051808060200180602001806020018060200180602001806020018060200188810388528f818151815260200191508051906020019080838360005b838110156101945780820151818401525b602081019050610178565b50505050905090810190601f1680156101c15780820380516001836020036101000a031916815260200191505b5088810387528e818151815260200191508051906020019080838360005b838110156101fb5780820151818401525b6020810190506101df565b50505050905090810190601f1680156102285780820380516001836020036101000a031916815260200191505b5088810386528d818151815260200191508051906020019080838360005b838110156102625780820151818401525b602081019050610246565b50505050905090810190601f16801561028f5780820380516001836020036101000a031916815260200191505b5088810385528c818151815260200191508051906020019060200280838360005b838110156102cc5780820151818401525b6020810190506102b0565b5050505090500188810384528b818151815260200191508051906020019060200280838360005b8381101561030f5780820151818401525b6020810190506102f3565b5050505090500188810383528a818151815260200191508051906020019060200280838360005b838110156103525780820151818401525b602081019050610336565b50505050905001888103825289818151815260200191508051906020019060200280838360005b838110156103955780820151818401525b602081019050610379565b505050509050019e50505050505050505050505050505060405180910390f35b34156103c057600080fd5b6103c86114bb565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561040c5780820151818401525b6020810190506103f0565b505050509050019250505060405180910390f35b341561042b57600080fd5b6104336116bf565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156104745780820151818401525b602081019050610458565b50505050905090810190601f1680156104a15780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104ba57600080fd5b6104e6600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611768565b604051808215151515815260200191505060405180910390f35b600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080600090505b600680549050811015610a0f5760068181548110151561054a57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610a01578460ff166003828154811015156105bd57fe5b90600052602060002090602091828204019190065b9054906101000a900460ff1660ff16148015610613575083600019166004828154811015156105fd57fe5b906000526020600020900160005b505460001916145b80156106445750826000191660058281548110151561062e57fe5b906000526020600020900160005b505460001916145b15610850577fcb265a1c827beb2fd9947f2a8d4725c8918f266faf695a26a53ac662e42a2f3f600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a81815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561072c5780601f106107015761010080835404028352916020019161072c565b820191906000526020600020905b81548152906001019060200180831161070f57829003601f168201915b50508481038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156107af5780601f10610784576101008083540402835291602001916107af565b820191906000526020600020905b81548152906001019060200180831161079257829003601f168201915b50508481038252888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156108325780601f1061080757610100808354040283529160200191610832565b820191906000526020600020905b81548152906001019060200180831161081557829003601f168201915b5050995050505050505050505060405180910390a160019150610f69565b7f05e85d72e83e8d2c8c877a19dd3a15c60415f315dc6d176b21537216537d275760006002878787336040518080602001806020018760ff1660ff168152602001866000191660001916815260200185600019166000191681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156109605780601f1061093557610100808354040283529160200191610960565b820191906000526020600020905b81548152906001019060200180831161094357829003601f168201915b50508381038252888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156109e35780601f106109b8576101008083540402835291602001916109e3565b820191906000526020600020905b8154815290600101906020018083116109c657829003601f168201915b50509850505050505050505060405180910390a160009150610f69565b5b5b808060010191505061052e565b610a1833611768565b15610d2d5760038054806001018281610a319190611852565b91600052602060002090602091828204019190065b87909190916101000a81548160ff021916908360ff1602179055505060048054806001018281610a76919061188c565b916000526020600020900160005b869091909150906000191690555060058054806001018281610aa6919061188c565b916000526020600020900160005b859091909150906000191690555060068054806001018281610ad691906118b8565b916000526020600020900160005b33909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507fbf474e795141390215f4f179557402a28c562b860f7b74dce4a3c0e0604cd97e600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c095780601f10610bde57610100808354040283529160200191610c09565b820191906000526020600020905b815481529060010190602001808311610bec57829003601f168201915b5050848103835289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c8c5780601f10610c6157610100808354040283529160200191610c8c565b820191906000526020600020905b815481529060010190602001808311610c6f57829003601f168201915b5050848103825288818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610d0f5780601f10610ce457610100808354040283529160200191610d0f565b820191906000526020600020905b815481529060010190602001808311610cf257829003601f168201915b5050995050505050505050505060405180910390a160019150610f69565b7fc585b66a303b685f03874907af9278712998ea1acfed37bcb4277da02cddb8b460006001600288888833604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610e435780601f10610e1857610100808354040283529160200191610e43565b820191906000526020600020905b815481529060010190602001808311610e2657829003601f168201915b505084810383528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610ec65780601f10610e9b57610100808354040283529160200191610ec6565b820191906000526020600020905b815481529060010190602001808311610ea957829003601f168201915b5050848103825289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610f495780601f10610f1e57610100808354040283529160200191610f49565b820191906000526020600020905b815481529060010190602001808311610f2c57829003601f168201915b50509a505050505050505050505060405180910390a160009150610f69565b5b509392505050565b610f796118e4565b610f816118e4565b610f896118e4565b610f916118f8565b610f9961190c565b610fa161190c565b610fa9611920565b6000610fb3611920565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561104357600080fd5b6102c65a03f1151561105457600080fd5b5050506040518051905092508260405180591061106e5750595b908082528060200260200182016040525b509150600090505b8281101561119b57600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561112857600080fd5b6102c65a03f1151561113957600080fd5b50505060405180519050828281518110151561115157fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b8080600101915050611087565b60006001600260036004600587868054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561123d5780601f106112125761010080835404028352916020019161123d565b820191906000526020600020905b81548152906001019060200180831161122057829003601f168201915b50505050509650858054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112d95780601f106112ae576101008083540402835291602001916112d9565b820191906000526020600020905b8154815290600101906020018083116112bc57829003601f168201915b50505050509550848054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113755780601f1061134a57610100808354040283529160200191611375565b820191906000526020600020905b81548152906001019060200180831161135857829003601f168201915b50505050509450838054806020026020016040519081016040528092919081815260200182805480156113ed57602002820191906000526020600020906000905b82829054906101000a900460ff1660ff16815260200190600101906020826000010492830192600103820291508084116113b65790505b505050505093508280548060200260200160405190810160405280929190818152602001828054801561144357602002820191906000526020600020905b8154600019168152602001906001019080831161142b575b505050505092508180548060200260200160405190810160405280929190818152602001828054801561149957602002820191906000526020600020905b81546000191681526020019060010190808311611481575b5050505050915099509950995099509950995099505b50505090919293949596565b6114c3611920565b60006114cd611920565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561155d57600080fd5b6102c65a03f1151561156e57600080fd5b505050604051805190509250826040518059106115885750595b908082528060200260200182016040525b509150600090505b828110156116b557600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561164257600080fd5b6102c65a03f1151561165357600080fd5b50505060405180519050828281518110151561166b57fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b80806001019150506115a1565b8193505b50505090565b6116c76118e4565b60018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561175d5780601f106117325761010080835404028352916020019161175d565b820191906000526020600020905b81548152906001019060200180831161174057829003601f168201915b505050505090505b90565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561182f57600080fd5b6102c65a03f1151561184057600080fd5b5050506040518051905090505b919050565b81548183558181151161188757601f016020900481601f016020900483600052602060002091820191016118869190611934565b5b505050565b8154818355818115116118b3578183600052602060002091820191016118b29190611959565b5b505050565b8154818355818115116118df578183600052602060002091820191016118de9190611934565b5b505050565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b61195691905b8082111561195257600081600090555060010161193a565b5090565b90565b61197b91905b8082111561197757600081600090555060010161195f565b5090565b905600a165627a7a7230582023e7bbee2604ea940c45aebe6b7012e695fa4f9120daf120e0686108fbe39a8b0029";

    private Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<AddSignaturesEventEventResponse> getAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("addSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AddSignaturesEventEventResponse> responses = new ArrayList<AddSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddSignaturesEventEventResponse> addSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("addSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddSignaturesEventEventResponse>() {
            @Override
            public AddSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
                return typedResponse;
            }
        });
    }

    public List<NewSignaturesEventEventResponse> getNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("newSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NewSignaturesEventEventResponse> responses = new ArrayList<NewSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
            typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewSignaturesEventEventResponse> newSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("newSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewSignaturesEventEventResponse>() {
            @Override
            public NewSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
                typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
                return typedResponse;
            }
        });
    }

    public List<ErrorNewSignaturesEventEventResponse> getErrorNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorNewSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorNewSignaturesEventEventResponse> responses = new ArrayList<ErrorNewSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
            typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorNewSignaturesEventEventResponse> errorNewSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorNewSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorNewSignaturesEventEventResponse>() {
            @Override
            public ErrorNewSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
                typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
                return typedResponse;
            }
        });
    }

    public List<ErrorAddSignaturesEventEventResponse> getErrorAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorAddSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorAddSignaturesEventEventResponse> responses = new ArrayList<ErrorAddSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
            typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorAddSignaturesEventEventResponse> errorAddSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorAddSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorAddSignaturesEventEventResponse>() {
            @Override
            public ErrorAddSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
                typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(6);
                return typedResponse;
            }
        });
    }

    public List<AddRepeatSignaturesEventEventResponse> getAddRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("addRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AddRepeatSignaturesEventEventResponse> responses = new ArrayList<AddRepeatSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddRepeatSignaturesEventEventResponse> addRepeatSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("addRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddRepeatSignaturesEventEventResponse>() {
            @Override
            public AddRepeatSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.info = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(3);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(5);
                return typedResponse;
            }
        });
    }

    public List<ErrorRepeatSignaturesEventEventResponse> getErrorRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorRepeatSignaturesEventEventResponse> responses = new ArrayList<ErrorRepeatSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(3);
            typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(4);
            typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorRepeatSignaturesEventEventResponse> errorRepeatSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorRepeatSignaturesEventEventResponse>() {
            @Override
            public ErrorRepeatSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(1);
                typedResponse.v = (Uint8) eventValues.getNonIndexedValues().get(2);
                typedResponse.r = (Bytes32) eventValues.getNonIndexedValues().get(3);
                typedResponse.s = (Bytes32) eventValues.getNonIndexedValues().get(4);
                typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(5);
                return typedResponse;
            }
        });
    }

    public Future<Address> signersAddr() {
        Function function = new Function("signersAddr", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addSignatures(Uint8 v, Bytes32 r, Bytes32 s) {
        Function function = new Function("addSignatures", Arrays.<Type>asList(v, r, s), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> getEvidence() {
        Function function = new Function("getEvidence", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<DynamicArray<Address>> getSigners() {
        Function function = new Function("getSigners", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getEvidenceInfo() {
        Function function = new Function("getEvidenceInfo", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> CallVerify(Address addr) {
        Function function = new Function("CallVerify", 
                Arrays.<Type>asList(addr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Evidence> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String evi, Utf8String info, Utf8String id, Uint8 v, Bytes32 r, Bytes32 s, Address addr, Address sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(evi, info, id, v, r, s, addr, sender));
        return deployAsync(Evidence.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String evi, Utf8String info, Utf8String id, Uint8 v, Bytes32 r, Bytes32 s, Address addr, Address sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(evi, info, id, v, r, s, addr, sender));
        return deployAsync(Evidence.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AddSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String info;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;
    }

    public static class NewSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String info;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;

        public Address addr;
    }

    public static class ErrorNewSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String info;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;

        public Address addr;
    }

    public static class ErrorAddSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String info;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;

        public Address addr;
    }

    public static class AddRepeatSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String info;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;
    }

    public static class ErrorRepeatSignaturesEventEventResponse {
        public Utf8String evi;

        public Utf8String id;

        public Uint8 v;

        public Bytes32 r;

        public Bytes32 s;

        public Address addr;
    }
}
