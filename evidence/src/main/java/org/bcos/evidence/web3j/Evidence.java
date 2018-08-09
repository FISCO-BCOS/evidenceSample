package org.bcos.evidence.web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.bcos.channel.client.TransactionSucCallback;
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
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class Evidence extends Contract {
    private static String BINARY = "606060405234156200000d57fe5b604051620022bd380380620022bd833981016040528080518201919060200180518201919060200180518201919060200180519060200190919080519060200190919080519060200190919080519060200190919080519060200190919050505b81600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000ce816200066264010000000002620017b7176401000000009004565b1562000444578760009080519060200190620000ec92919062000748565b5086600190805190602001906200010592919062000748565b5085600290805190602001906200011e92919062000748565b5060038054806001018281620001359190620007cf565b91600052602060002090602091828204019190065b87909190916101000a81548160ff021916908360ff16021790555050600480548060010182816200017c91906200080c565b916000526020600020900160005b869091909150906000191690555060058054806001018281620001ae91906200080c565b916000526020600020900160005b859091909150906000191690555060068054806001018281620001e091906200083b565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507f6001b9d5afd61e6053e8a73e30790ef8240d919a754055049131521927fbe21188888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b8181518152602001915080519060200190808383600083146200030c575b8051825260208311156200030c57602082019150602081019050602083039250620002e6565b505050905090810190601f168015620003395780820380516001836020036101000a031916815260200191505b5084810383528a81815181526020019150805190602001908083836000831462000384575b80518252602083111562000384576020820191506020810190506020830392506200035e565b505050905090810190601f168015620003b15780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360008314620003fc575b805182526020831115620003fc57602082019150602081019050602083039250620003d6565b505050905090810190601f168015620004295780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a162000653565b7f45cb885dcdccd3bece3cb78b963aec501f1cf9756fd93584f0643c7a9533431088888888888888604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b81815181526020019150805190602001908083836000831462000520575b8051825260208311156200052057602082019150602081019050602083039250620004fa565b505050905090810190601f1680156200054d5780820380516001836020036101000a031916815260200191505b5084810383528a81815181526020019150805190602001908083836000831462000598575b805182526020831115620005985760208201915060208101905060208303925062000572565b505050905090810190601f168015620005c55780820380516001836020036101000a031916815260200191505b5084810382528981815181526020019150805190602001908083836000831462000610575b8051825260208311156200061057602082019150602081019050602083039250620005ea565b505050905090810190601f1680156200063d5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a15b5b5050505050505050620008ba565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15156200072757fe5b6102c65a03f115156200073657fe5b5050506040518051905090505b919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200078b57805160ff1916838001178555620007bc565b82800160010185558215620007bc579182015b82811115620007bb5782518255916020019190600101906200079e565b5b509050620007cb91906200086a565b5090565b8154818355818115116200080757601f016020900481601f016020900483600052602060002091820191016200080691906200086a565b5b505050565b815481835581811511620008365781836000526020600020918201910162000835919062000892565b5b505050565b81548183558181151162000865578183600052602060002091820191016200086491906200086a565b5b505050565b6200088f91905b808211156200088b57600081600090555060010162000871565b5090565b90565b620008b791905b80821115620008b357600081600090555060010162000899565b5090565b90565b6119f380620008ca6000396000f30060606040523615610076576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633b52ebd01461007857806348f85bce146100ca578063596f21f81461011f57806394cf795e14610404578063c7eaf9b414610479578063dc58ab1114610512575bfe5b341561008057fe5b610088610560565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156100d257fe5b610105600480803560ff169060200190919080356000191690602001909190803560001916906020019091905050610586565b604051808215151515815260200191505060405180910390f35b341561012757fe5b61012f610fd2565b604051808060200180602001806020018060200180602001806020018060200188810388528f818151815260200191508051906020019080838360008314610196575b80518252602083111561019657602082019150602081019050602083039250610172565b505050905090810190601f1680156101c25780820380516001836020036101000a031916815260200191505b5088810387528e81815181526020019150805190602001908083836000831461020a575b80518252602083111561020a576020820191506020810190506020830392506101e6565b505050905090810190601f1680156102365780820380516001836020036101000a031916815260200191505b5088810386528d81815181526020019150805190602001908083836000831461027e575b80518252602083111561027e5760208201915060208101905060208303925061025a565b505050905090810190601f1680156102aa5780820380516001836020036101000a031916815260200191505b5088810385528c8181518152602001915080519060200190602002808383600083146102f5575b8051825260208311156102f5576020820191506020810190506020830392506102d1565b50505090500188810384528b818151815260200191508051906020019060200280838360008314610345575b80518252602083111561034557602082019150602081019050602083039250610321565b50505090500188810383528a818151815260200191508051906020019060200280838360008314610395575b80518252602083111561039557602082019150602081019050602083039250610371565b5050509050018881038252898181518152602001915080519060200190602002808383600083146103e5575b8051825260208311156103e5576020820191506020810190506020830392506103c1565b5050509050019e50505050505050505050505050505060405180910390f35b341561040c57fe5b610414611513565b6040518080602001828103825283818151815260200191508051906020019060200280838360008314610466575b80518252602083111561046657602082019150602081019050602083039250610442565b5050509050019250505060405180910390f35b341561048157fe5b61048961170e565b60405180806020018281038252838181518152602001915080519060200190808383600083146104d8575b8051825260208311156104d8576020820191506020810190506020830392506104b4565b505050905090810190601f1680156105045780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561051a57fe5b610546600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506117b7565b604051808215151515815260200191505060405180910390f35b600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006000600090505b600680549050811015610a70576006818154811015156105ab57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610a62578460ff1660038281548110151561061e57fe5b90600052602060002090602091828204019190065b9054906101000a900460ff1660ff161480156106745750836000191660048281548110151561065e57fe5b906000526020600020900160005b505460001916145b80156106a55750826000191660058281548110151561068f57fe5b906000526020600020900160005b505460001916145b156108b1577fcb265a1c827beb2fd9947f2a8d4725c8918f266faf695a26a53ac662e42a2f3f600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a81815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561078d5780601f106107625761010080835404028352916020019161078d565b820191906000526020600020905b81548152906001019060200180831161077057829003601f168201915b50508481038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156108105780601f106107e557610100808354040283529160200191610810565b820191906000526020600020905b8154815290600101906020018083116107f357829003601f168201915b50508481038252888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156108935780601f1061086857610100808354040283529160200191610893565b820191906000526020600020905b81548152906001019060200180831161087657829003601f168201915b5050995050505050505050505060405180910390a160019150610fca565b7f05e85d72e83e8d2c8c877a19dd3a15c60415f315dc6d176b21537216537d275760006002878787336040518080602001806020018760ff1660ff168152602001866000191660001916815260200185600019166000191681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156109c15780601f10610996576101008083540402835291602001916109c1565b820191906000526020600020905b8154815290600101906020018083116109a457829003601f168201915b5050838103825288818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610a445780601f10610a1957610100808354040283529160200191610a44565b820191906000526020600020905b815481529060010190602001808311610a2757829003601f168201915b50509850505050505050505060405180910390a160009150610fca565b5b5b808060010191505061058f565b610a79336117b7565b15610d8e5760038054806001018281610a92919061189b565b91600052602060002090602091828204019190065b87909190916101000a81548160ff021916908360ff1602179055505060048054806001018281610ad791906118d5565b916000526020600020900160005b869091909150906000191690555060058054806001018281610b0791906118d5565b916000526020600020900160005b859091909150906000191690555060068054806001018281610b379190611901565b916000526020600020900160005b33909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507fbf474e795141390215f4f179557402a28c562b860f7b74dce4a3c0e0604cd97e600060016002888888604051808060200180602001806020018760ff1660ff1681526020018660001916600019168152602001856000191660001916815260200184810384528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c6a5780601f10610c3f57610100808354040283529160200191610c6a565b820191906000526020600020905b815481529060010190602001808311610c4d57829003601f168201915b5050848103835289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610ced5780601f10610cc257610100808354040283529160200191610ced565b820191906000526020600020905b815481529060010190602001808311610cd057829003601f168201915b5050848103825288818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610d705780601f10610d4557610100808354040283529160200191610d70565b820191906000526020600020905b815481529060010190602001808311610d5357829003601f168201915b5050995050505050505050505060405180910390a160019150610fca565b7fc585b66a303b685f03874907af9278712998ea1acfed37bcb4277da02cddb8b460006001600288888833604051808060200180602001806020018860ff1660ff168152602001876000191660001916815260200186600019166000191681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184810384528b818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610ea45780601f10610e7957610100808354040283529160200191610ea4565b820191906000526020600020905b815481529060010190602001808311610e8757829003601f168201915b505084810383528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610f275780601f10610efc57610100808354040283529160200191610f27565b820191906000526020600020905b815481529060010190602001808311610f0a57829003601f168201915b5050848103825289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610faa5780601f10610f7f57610100808354040283529160200191610faa565b820191906000526020600020905b815481529060010190602001808311610f8d57829003601f168201915b50509a505050505050505050505060405180910390a160009150610fca565b5b509392505050565b610fda61192d565b610fe261192d565b610fea61192d565b610ff2611941565b610ffa611955565b611002611955565b61100a611969565b6000611014611969565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156110a457fe5b6102c65a03f115156110b257fe5b505050604051805190509250826040518059106110cc5750595b908082528060200260200182016040525b509150600090505b828110156111f357600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561118357fe5b6102c65a03f1151561119157fe5b5050506040518051905082828151811015156111a957fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b80806001019150506110e5565b60006001600260036004600587868054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112955780601f1061126a57610100808354040283529160200191611295565b820191906000526020600020905b81548152906001019060200180831161127857829003601f168201915b50505050509650858054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113315780601f1061130657610100808354040283529160200191611331565b820191906000526020600020905b81548152906001019060200180831161131457829003601f168201915b50505050509550848054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113cd5780601f106113a2576101008083540402835291602001916113cd565b820191906000526020600020905b8154815290600101906020018083116113b057829003601f168201915b505050505094508380548060200260200160405190810160405280929190818152602001828054801561144557602002820191906000526020600020906000905b82829054906101000a900460ff1660ff168152602001906001019060208260000104928301926001038202915080841161140e5790505b505050505093508280548060200260200160405190810160405280929190818152602001828054801561149b57602002820191906000526020600020905b81546000191681526020019060010190808311611483575b50505050509250818054806020026020016040519081016040528092919081815260200182805480156114f157602002820191906000526020600020905b815460001916815260200190600101908083116114d9575b5050505050915099509950995099509950995099505b50505090919293949596565b61151b611969565b6000611525611969565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fa69efbd6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156115b557fe5b6102c65a03f115156115c357fe5b505050604051805190509250826040518059106115dd5750595b908082528060200260200182016040525b509150600090505b8281101561170457600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ffefe4e826000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561169457fe5b6102c65a03f115156116a257fe5b5050506040518051905082828151811015156116ba57fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b80806001019150506115f6565b8193505b50505090565b61171661192d565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117ac5780601f10611781576101008083540402835291602001916117ac565b820191906000526020600020905b81548152906001019060200180831161178f57829003601f168201915b505050505090505b90565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166363a9c3d7836000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561187b57fe5b6102c65a03f1151561188957fe5b5050506040518051905090505b919050565b8154818355818115116118d057601f016020900481601f016020900483600052602060002091820191016118cf919061197d565b5b505050565b8154818355818115116118fc578183600052602060002091820191016118fb91906119a2565b5b505050565b81548183558181151161192857818360005260206000209182019101611927919061197d565b5b505050565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b61199f91905b8082111561199b576000816000905550600101611983565b5090565b90565b6119c491905b808211156119c05760008160009055506001016119a8565b5090565b905600a165627a7a7230582005363ebceeee0c9e61ec1a7c34da88349f205a9c9b284827ebf5d7bc381e141d0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"signersAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"v\",\"type\":\"uint8\"},{\"name\":\"r\",\"type\":\"bytes32\"},{\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"addSignatures\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getEvidence\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint8[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getSigners\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getEvidenceInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"CallVerify\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[{\"name\":\"evi\",\"type\":\"string\"},{\"name\":\"info\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"v\",\"type\":\"uint8\"},{\"name\":\"r\",\"type\":\"bytes32\"},{\"name\":\"s\",\"type\":\"bytes32\"},{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"sender\",\"type\":\"address\"}],\"payable\":false,\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"addSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"newSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"errorNewSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"errorAddSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"addRepeatSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"v\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"r\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"s\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"errorRepeatSignaturesEvent\",\"type\":\"event\"}]";

    private Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<AddSignaturesEventEventResponse> getAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public static List<NewSignaturesEventEventResponse> getNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public static List<ErrorNewSignaturesEventEventResponse> getErrorNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public static List<ErrorAddSignaturesEventEventResponse> getErrorAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public static List<AddRepeatSignaturesEventEventResponse> getAddRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public static List<ErrorRepeatSignaturesEventEventResponse> getErrorRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
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

    public void addSignatures(Uint8 v, Bytes32 r, Bytes32 s, TransactionSucCallback callback) {
        Function function = new Function("addSignatures", Arrays.<Type>asList(v, r, s), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
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
        return new Evidence(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static Evidence loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static Evidence loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
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
