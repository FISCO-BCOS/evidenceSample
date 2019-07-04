<center> <h1>区块链存证案例使用说明</h1> </center>

**全新适配FISCO-BCOS 2.0.0版本，如果使用FISCO-BCOS 1.2或1.3版本请用[v1.0.0版本](https://github.com/FISCO-BCOS/evidenceSample/releases/tag/v1.0.0)。**

# 一、 背景介绍
* FISCO-BCOS区块链存证是聚焦于企业级应用服务的区块链技术平台，从电子数据的全生命周期介入，实现区块链存证、取证、维权、核证，让司法机构参与到业务过程中，实时见证，为后续的证据核实、纠纷解决、裁决送达提供了可信、可追溯、可证明的技术保障。适用场景：金融行业网络信贷、消费金融、理财等，重点解决可信和司法认可。 <br><br>
* 区块链存证示例是基于FISCO-BCOS区块链开发的应用案例。示例使用智能合约对存证进行管理，使用分层的智能合约结构： <br>
   1）工厂合约（EvidenceSignersData.sol），由存证各方事前约定，存储存证生效条件，并管理存证的生成。 <br>
    2）存证合约（Evidence.sol），由工厂合约生成，存储存证id，hash和各方签名（每张存证一个合约）。 <br>
   两层智能合约的设计，可以使系统获得更好的扩展性。示例使用三个角色（用户、存证机构、仲裁机构）来说明一个典型的电子存证场景。关键业务为证据上链，多方签署，链上取证。 <br>
* 本存证样例展示的业务流程主要是：1、收集区块链上机构公钥信息（用户的公钥信息，存证机构的公钥信息，仲裁机构的公钥信息），部署工厂合约。2、用户发送签名存证信息，并发送存证请求。3、存证机构收到存证请求，取出存证内容校验（样例中没有展示），并签名确认。4、仲裁机构收到存证请求，取出存证内容校验（样例中没有展示），并签名确认。5、取证
* 本文档旨在帮助开发者快速入门区块链存证应用开发。

# 二、存证案例运行环境搭建

1.  本文档使用4个区块链节点来模拟区块链环境，请参考 [安装文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/release-2.0/docs/installation.html) 完成FISCO BCOS区块链的搭建和控制台的下载工作，本教程中的操作假设在该文档搭建的环境下进行。
2.  更新签名机构公私钥（**示例演示可以直接使用sample提供公私钥，jks文件**），公钥在applicationContext.xml文件中配置，私钥需替换/evidence/src/main/resources下的.jks文件，配置和生成公私钥参照下一节中角色公钥配置说明。
3.  SDK证书
```
# 进入~目录
# 拷贝节点证书到项目的资源目录
$ cd ~
$ cp fisco/nodes/127.0.0.1/sdk/* evidence/src/main/resources    
```
4. applicationContext关于节点的配置请参考：下面的存证案例配置文件说明。

# 三、 存证案例配置文件说明

```
evidence/src/main/resources/applicationContext.xml文件配置说明
```

1、角色公钥配置说明：

	<bean id="addressConf" class="org.bcos.evidence.sample.PublicAddressConf">
		<property name="allPublicAddress">
			<map>
				<entry key="User" value="0x33674063c4618f4773fac75dc2f07e55f6f391ce">
				</entry>
				<entry key="Arbitrator" value="0x6bc952a2e4db9c0c86a368d83e9df0c6ab481102">
				</entry>
				<entry key="Depositor" value="0x5a6c7ccf9efa702f4e8888ff7e8a3310abcf8c51">
				</entry>
			</map>
		</property>
	</bean>

配置解释：

* 配置中key=User代表用户角色，对应的私钥文件为user.jks；key=Arbitrator代表仲裁机构，对应的私钥文件为arbitrator.jks；key=Depositor代表存证机构，对应的私钥文件为depositor.jks。

* 按照上面的key-value的格式写入3个角色所对应的公钥，在/evidence/src/main/resources文件夹下放入角色所对应的私钥。
  私钥是由linux系统下java JDK/bin中的keytool工具生成的（生成命令如下），私钥生成后，可以通过私钥调用接口生成需要的公钥，具体操作可参照<a name="host_node" id="host_node">4.6. 获取公钥</a>

        keytool -genkeypair -alias ec -keyalg EC -keysize 256 -sigalg SHA256withECDSA  -validity 365 -storetype JKS -
        keystore user.jks -storepass 123456

2、区块链节点信息配置，[SDK配置具体请参考](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/sdk/sdk.html)。：

```
<bean id="groupChannelConnectionsConfig" class="org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig">
    <property name="allChannelConnections">
        <list>
            <bean id="group1"  class="org.fisco.bcos.channel.handler.ChannelConnections">
                <property name="groupId" value="1" />
                <property name="connectionsStr"><!-- 配置群组区块链节点列表-->
                    <list>
                        <value>127.0.0.1:20200</value>
                    </list>
                </property>
            </bean>
        </list>
    </property>
</bean>

<bean id="channelService" class="org.fisco.bcos.channel.client.Service" depends-on="groupChannelConnectionsConfig">
    <property name="groupId" value="1" /><!-- 配置群组ID -->
    <property name="orgID" value="fisco" /><!-- 配置机构名称 -->
    <property name="allChannelConnections" ref="groupChannelConnectionsConfig"></property>
</bean>
```
# 四、存证案例工具包使用说明

本节提供使用示例工具包，以便开发者能够快速熟悉存证应用。在工具包中，bin文件下为执行脚本，conf文件夹下为工具包配置文件，lib文件下为存证案例依赖包,contracts中存放合约源码（合约java代码生成可以参照[4.7](https://fisco-bcos-documentation.readthedocs.io/zh_CN/release-2.0/docs/tutorial/sdk_application.html#id7)）

#### 存证案例工具包环境初始化

* 存证工具包可以通过存证客户端gradle build生成；或者直接下载，下载链接https://github.com/FISCO-BCOS/evidenceSample/tree/master/evidence_toolkit
* 下载完成之后建议对bin文件夹下的文件执行chmod命令。
* 根据实际需求更新公私钥（需要3组），公钥以key-value的形式在applicationContext.xml中配置，私钥更新需要替换conf文件下的.jks私钥文件。若无特殊需求可以不用更新公私钥,直接使用默认配置即可。
* 替换工具包证书
```
# 进入~目录
# 拷贝节点证书
$ cd ~
$ cp fisco/nodes/127.0.0.1/sdk/* evidence_toolkit/conf
```
* applicationContext.xml配置文件，请参考（上面第三节： 存证案例配置文件说明）

####  存证案例工具包一键脚本步骤
若想查看完整的执行过程，可执行存证工具包bin文件下runEvidence.sh脚本，runEvidence.sh为存证的一键默认执行脚本，脚本中将存证sample工具包的执行命令进行封装。

####  存证案例工具包分步操作步骤


版本兼容说明: 

evidence 目前适配的 FISCO-BCOS 版本是 v2.0.0-rc2, 新版本正在紧张适配中。

1、工厂合约部署

进入到bin文件下，输入以下命令：

```
./evidence deploy keyStoreFileName keyStorePassword keyPassword
例子：./evidence deploy user.jks '123456' '123456'
```

参数说明：

* 在上面的命令中需要传入4个参数，第一个参数固定不可修改。后面三个参数依赖conf中的私钥的文件(直接输入文件名，并非路径)。keyStoreFileName：私钥文件名;keyStorePassword、keyPassword分别为证书生成时输入的keyStore密码和key密码。

控制台显示结果：

    deploy factoryContract success, address: deployAddress.（部署工厂合约返回地址）
2、用户创建新证据

进入到bin文件下，在控制台输入命令：

```
./evidence new  keyStoreFileName keyStorePassword keyPassword deployAddress evidence_id evidence_hash
例子：./evidence new user.jks '123456' '123456' '0x19e2f046f4fc6a02d732a3ffda6480c34214f57f' '1' '0xeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee'
```

参数说明：

* 命令中第一个参数不可修改，deployAddress为工厂合约部署之后返回的地址，evidence_id和evidence_hash分别为证据ID和证据的hash值，长度无限制。证据的hash可以是合同的hash值，还款记录的hash等。计算方法可以是md5值，sha1，sha3等。

控制台显示结果：

    newEvidence success, newEvidenceAddress: newEvidenceAddress（创建证据返回地址）	
3、发送签名上链

进入到bin文件下，在控制台输入命令：


     ./evidence send keyStoreFileName keyStorePassword keyPassword newEvidenceAddress
    
     例子：./evidence send arbitrator.jks '123456' '123456' '0x4437863afe7c9adce4e658c95666feaab1d996a2' 
          ./evidence send depositor.jks '123456' '123456' '0x4437863afe7c9adce4e658c95666feaab1d996a2' 


参数说明：

* 注意：此命令需要执行两次，在newEvidence步骤时，用户已经签名，在这个步骤需要存证机构和仲裁机构双方签名，所以在执行这个命令时传入的keyStoreFileName、keyStorePassword、keyPassword分别为两个机构的私钥及密码。
* 在这个步骤中同样需要用到evidence_hash值，程序中通过newEvidenceAddress查询证据的信息，然后获取evidence_hash值。
* 命令中第一个参数不可修改，newEvidenceAddress为创建证据区块链返回的地址。

控制台显示结果：

    sendSignatureToBlockChain success：true
4、获取证据

进入到bin文件下，在控制台输入命令：

```
./evidence get keyStoreFileName keyStorePassword keyPassword newEvidenceAddress
例子：./evidence get user.jks '123456' '123456' '0x4437863afe7c9adce4e658c95666feaab1d996a2'
```

参数说明：

* 命令中第一个参数不可修改，在这个命令中依赖conf中的私钥文件和deploy操作相同，newEvidenceAddress为创建证据后区块链返回的地址。

控制台显示结果：

    the evidenceID:evidence_id(创建证据时传入的evidence_id值)
    the evidenceHash：evidence_hash( 创建证据时传入的evidence_hash值)
    the signature[0-2]：（打印3个角色的签名）
    the publicKey[0-2]：（打印3个角色的公钥）

5、校验证据和签名

进入到bin文件下，在控制台输入命令：

```
./evidence verify keyStoreFileName keyStorePassword keyPassword newEvidenceAddress
例子：./evidence verify user.jks '123456' '123456' '0x4437863afe7c9adce4e658c95666feaab1d996a2'
```

参数说明：

* 命令中第一个参数不可修改，在这个命令中依赖conf中的私钥文件和deploy操作相同，newEvidenceAddress为创建证据后区块链返回的地址。

控制台结果显示：

    若校验通过显示：verifyEvidence success：true
    若校验失败显示：verifyEvidence failed:false


6、获取公钥
进入到bin文件下，在控制台输入命令：

```
./evidence getPublicKey keyStoreFileName keyStorePassword keyPassword 
例子：./evidence getPublicKey user.jks '123456' '123456'
```

参数说明：

* 命令中第一个参数不可修改，通过读取对应的私钥文件，获取公钥。

控制台结果显示：

    publicKey：公钥

7、合约编译及java Wrap代码生成

* 参考[合约转换](https://fisco-bcos-documentation.readthedocs.io/zh_CN/release-2.0/docs/tutorial/sdk_application.html#id7)

# 五、存证客户端使用

存证客户端用java编写，可将工程导入Eclipse做二次开发。二次开发工程URL为：https://github.com/FISCO-BCOS/evidenceSample/tree/master/evidence。其入口为org.bcos.evidence.app.Main类，客户端中对合约的调用主要包括：web3j的初始化，合约对象部署，载入已经部署的合约，创建证据，发送签名数据，获取证据信息，以及证据校验。区块链应用程序实际是通过web3j生成的java Wrapper类(详细介绍参看4.7合约编译及java Wrap代码生成)，通过jsonRPC调用和FISCO-BCOS客户端节点通信，再由客户端返回jsonRPC请求响应。

1、web3j初始化 

注意：客户端调用智能合约首先必须初始化web3j，初始化关键代码如下：


        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        web3j = Web3j.build(channelEthereumService);


2、工厂合约部署

使用初始化的web3j对象来部署智能合约，如果工厂合约部署成功，Future对象会返回合约调用对象（即合约地址），合约部署关键代码如下：


```
EvidenceSignersData evidenceSignersData = EvidenceSignersData.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimited), arrayList).send();
```

3、载入已经部署的合约


合约部署成功后，可以获取到已经部署的合约地址：evidenceSignersData.getContractAddress()；使用已经部署合约地址，初始化的web3j对象，可以载入智能合约调用对象。

```
EvidenceSignersData evidenceSignersData = EvidenceSignersData.load(address.toString(), web3j,  credentials, new StaticGasProvider(gasPrice, gasLimited));
```

注意：部署后可以直接使用返回的智能合约对象，而无需再load载入！

4、创建新证据


发送交易通过直接调用已经部署或载入的智能合约调用对象执行合约对应接口即可，交易执行成功后将返回Receipt，Receipt包含交易hash和其他信息。

```
Receipt receipt = evidenceSignersData.newEvidence(evidence_hash, evidence_id,evidence_id, BigInteger.valueOf(signatureData.getV()),signatureData.getR(),signatureData.getS()).sendAsync().get();
```

注意：调用此方法需要传入参数，参数为string[]，长度为3即可。传入的三个参数分别模拟对应evidence_id(证据ID)、evidence_hash(证据hash值)、sign_data（签名数据），客户端将传入的三个参数生成一个完成的证据存入区块链中。

5、发送签名数据上链

在用户角色调用newEvidence（）接口创建一个新的证据之后，存证机构和签名机构分别需要对证据进行签名，并且将签名的数据发送到区块链中进行存储，关键代码如下：

```
Evidence evidence = Evidence.load(address, web3j, credentials,  gasPrice, gasLimited);
TransactionReceipt receipt = evidence.addSignatures(BigInteger.valueOf(signature.getV()),
                            signature.getR(),
                            signature.getS()).sendAsync().get();
```

6、获取证据

创建证据一个，区块链会返回一个交易地址，使用已经部署或载入的智能合约对象调用getEvidence()接口传入交易返回的地址，可返回一个list<Type>,对list<Type>进行解析可以得到一个evidence对象。

```
Evidence evidence = Evidence.load(newEvidenceAddres, web3j, credentials,  gasPrice, gasLimited);
Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>> result2 = evidence.getEvidence().send();
```

7、校验证据和签名

通过调用步骤6中的接口可以获取到证据的完整信息，并且附带有3个角色对证据的签名信息，通过校验公钥是否相同来确定签名的信息是否准确。关键代码如下：

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

#  六、[web3sdk的使用方式](https://github.com/FISCO-BCOS/web3sdk)


如果您觉得本文不错，欢迎[戳这里](https://github.com/FISCO-BCOS/FISCO-BCOS)给FISCO BCOS打star:star:。
